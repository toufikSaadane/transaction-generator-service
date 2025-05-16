package com.toufik.transactiongeneratorservice.service;

import com.toufik.transactiongeneratorservice.model.Mt940Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Mt940GeneratorTest {

    @Test
    void testGenerateMt940Content() {
        Mt940Data data = new Mt940Data();
        data.setTransactionReference("REFABCDEFGHI");
        data.setAccountNumber("DE1234567890");
        data.setStatementNumber("00001/001");
        data.setOpeningBalance("C231025EUR1000,00");
        data.setValueDate(new java.util.Date());
        data.setEntryDate(new java.util.Date());
        data.setDebitCredit("D");
        data.setAmount("150,00");
        data.setClosingBalance("C231025EUR850,00");
        data.setEref("INVOICE-12345");
        data.setMerchantName("MERCHANT XYZ");
        data.setRemittanceInfo("PAYMENT FOR SERVICES");

        String content = Mt940Generator.generateMt940Content(data);

        assertTrue(content.contains(":20:"), "Content should contain field :20:");
        assertTrue(content.contains(":25:"), "Content should contain field :25:");
        assertTrue(content.contains(":28C:"), "Content should contain field :28C:");
        assertTrue(content.contains(":60F:"), "Content should contain field :60F:");
        assertTrue(content.contains(":61:"), "Content should contain field :61:");
        assertTrue(content.contains(":86:"), "Content should contain field :86:");
        assertTrue(content.contains(":62F:"), "Content should contain field :62F:");
    }

    @Test
    void testWriteToFile(@TempDir Path tempDir) throws Exception {
        String content = "Test MT940 content";
        String filename = tempDir.resolve("testFile.sta").toString();

        Mt940Generator.writeToFile(content, filename);

        String fileContent = Files.readString(Path.of(filename));
        assertEquals(content, fileContent);
    }
}