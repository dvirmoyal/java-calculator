package com.calculator.service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTests {

    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    public void testMultiply() {
        // Arrange
        Double a = 5.0;
        Double b = 3.0;
        Double expected = 15.0;

        // Act
        Double result = calculatorService.multiply(a, b);

        // Assert
        assertEquals(expected, result, "5.0 * 3.0 should equal 15.0");
    }

    @Test
    public void testMultiplyWithZero() {
        // Arrange
        Double a = 5.0;
        Double b = 0.0;
        Double expected = 0.0;

        // Act
        Double result = calculatorService.multiply(a, b);

        // Assert
        assertEquals(expected, result, "5.0 * 0.0 should equal 0.0");
    }

    @Test
    public void testMultiplyWithNegativeNumbers() {
        // Arrange
        Double a = -5.0;
        Double b = 3.0;
        Double expected = -15.0;

        // Act
        Double result = calculatorService.multiply(a, b);

        // Assert
        assertEquals(expected, result, "-5.0 * 3.0 should equal -15.0");
    }

    @Test
    public void testDivide() {
        // Arrange
        Double a = 10.0;
        Double b = 2.0;
        Double expected = 5.0;

        // Act
        Double result = calculatorService.divide(a, b);

        // Assert
        assertEquals(expected, result, "10.0 / 2.0 should equal 5.0");
    }

    @Test
    public void testDivideWithZeroDividend() {
        // Arrange
        Double a = 0.0;
        Double b = 5.0;
        Double expected = 0.0;

        // Act
        Double result = calculatorService.divide(a, b);

        // Assert
        assertEquals(expected, result, "0.0 / 5.0 should equal 0.0");
    }

    @Test
    public void testDivideByZero() {
        // Arrange
        Double a = 10.0;
        Double b = 0.0;

        // Act & Assert
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculatorService.divide(a, b);
        });

        String expectedMessage = "Division by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Exception message should contain '" + expectedMessage + "'");
    }
}