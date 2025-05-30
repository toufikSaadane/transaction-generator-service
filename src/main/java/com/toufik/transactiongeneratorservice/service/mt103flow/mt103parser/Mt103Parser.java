package com.toufik.transactiongeneratorservice.service.mt103flow.mt103parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@Slf4j
public class Mt103Parser {

    private static final String MT103_DIRECTORY = "src/main/resources/mt103_files";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void parseMt103FilesToJson() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(MT103_DIRECTORY))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".sta"))
                    .forEach(this::parseAndConvertToJson);
        }
    }


    public String parseMt103FilesToJsonString() throws IOException {
        StringBuilder result = new StringBuilder();
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/mt103_files"))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".sta"))
                    .forEach(path -> {
                        try {
                            String content = Files.readString(path);
                            ObjectNode json = parseMt103Content(content);
                            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                            result.append(jsonString).append(System.lineSeparator());
                        } catch (IOException e) {
                            log.error("Error processing file {}: {}", path, e.getMessage());
                        }
                    });
        }
        return result.toString();
    }

    private void parseAndConvertToJson(Path filePath) {
        try {
            String content = Files.readString(filePath);
            ObjectNode json = parseMt103Content(content);

            // Create JSON file path
            String jsonFileName = filePath.getFileName().toString().replace(".sta", ".json");
            Path jsonFilePath = Paths.get(MT103_DIRECTORY, jsonFileName);

            // Write JSON to file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFilePath.toFile(), json);
            log.info("Converted {} to JSON: {}", filePath, jsonFilePath);

        } catch (IOException e) {
            log.error("Error parsing file {}: {}", filePath, e.getMessage());
        }
    }

    private ObjectNode parseMt103Content(String content) {
        ObjectNode json = objectMapper.createObjectNode();
        String[] blocks = content.split("\\n\\{");

        // Parse Block 1
        if (blocks.length > 0) {
            String block1 = blocks[0].substring(blocks[0].indexOf('{') + 1);
            json.put("block1", block1.replace("}", ""));
        }

        // Parse Block 2
        if (blocks.length > 1) {
            String block2 = blocks[1].substring(0, blocks[1].indexOf('}'));
            json.put("block2", block2);
        }

        // Parse Block 3
        if (blocks.length > 2) {
            String block3 = blocks[2].substring(0, blocks[2].indexOf('}'));
            json.put("block3", block3);
        }

        // Parse Block 4 (main content)
        if (blocks.length > 3) {
            String block4 = blocks[3].substring(blocks[3].indexOf('{') + 1);
            block4 = block4.substring(0, block4.indexOf("-}")).trim();

            ObjectNode block4Json = parseBlock4(block4);
            json.set("block4", block4Json);
        }

        // Parse Block 5
        if (blocks.length > 4) {
            String block5 = blocks[4].substring(blocks[4].indexOf('{') + 1);
            block5 = block5.replace("}}", "");
            json.put("block5", block5);
        }

        return json;
    }

    private ObjectNode parseBlock4(String block4Content) {
        ObjectNode block4Json = objectMapper.createObjectNode();
        String[] lines = block4Content.split("\\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            if (line.startsWith(":")) {
                int secondColon = line.indexOf(':', 1);
                if (secondColon > 0) {
                    String field = line.substring(1, secondColon);
                    String value = line.substring(secondColon + 1);
                    block4Json.put(field, value);
                }
            } else if (line.startsWith("/")) {
                // Handle beneficiary account (field 59)
                String[] parts = line.split("\\n");
                if (parts.length > 0) {
                    block4Json.put("59_account", parts[0].substring(1));
                    if (parts.length > 1) block4Json.put("59_name", parts[1]);
                    if (parts.length > 2) block4Json.put("59_address", parts[2]);
                }
            } else {
                // Continuation lines (like for field 59)
                // You might want to append this to the previous field
            }
        }

        return block4Json;
    }
}