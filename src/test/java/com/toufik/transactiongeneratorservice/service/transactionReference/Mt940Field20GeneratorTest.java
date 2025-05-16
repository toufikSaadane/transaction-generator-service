package com.toufik.transactiongeneratorservice.service.transactionReference;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;


@Service
public class Mt940Field20GeneratorTest {


    @Test
    void generateField20ProducesValidReference() {
        String reference = Mt940Field20Generator.generateField20();
        Assertions.assertNotNull(reference);
        Assertions.assertEquals(12, reference.length());
        Assertions.assertTrue(reference.startsWith("ohiuhiuh"));
    }
}