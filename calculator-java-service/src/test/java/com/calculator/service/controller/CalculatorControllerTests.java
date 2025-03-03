package com.calculator.service.controller;

import com.calculator.service.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void testMultiply() throws Exception {
        // Arrange
        when(calculatorService.multiply(5.0, 3.0)).thenReturn(15.0);

        // Act & Assert
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 5.0, \"b\": 3.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15.0));
    }

    @Test
    public void testDivide() throws Exception {
        // Arrange
        when(calculatorService.divide(10.0, 2.0)).thenReturn(5.0);

        // Act & Assert
        mockMvc.perform(post("/api/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 10.0, \"b\": 2.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    public void testDivideByZero() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 10.0, \"b\": 0.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Division by zero is not allowed"));
    }

    @Test
    public void testMissingParameters() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 5.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void testHealthCheck() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}