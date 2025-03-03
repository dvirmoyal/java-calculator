package com.calculator.service.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * Multiplies two numbers.
     *
     * @param a first number
     * @param b second number
     * @return the product of a and b
     */
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    /**
     * Divides the first number by the second number.
     *
     * @param a dividend
     * @param b divisor
     * @return the result of a / b
     * @throws ArithmeticException if b is zero
     */
    public Double divide(Double a, Double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}