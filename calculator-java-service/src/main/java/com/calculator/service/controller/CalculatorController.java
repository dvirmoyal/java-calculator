package com.calculator.service.controller;

import com.calculator.service.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // This is for development, adjust for production
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/multiply")
    public ResponseEntity<Map<String, Object>> multiply(@RequestBody Map<String, Double> request) {
        // Extract numbers from request
        Double a = request.get("a");
        Double b = request.get("b");

        // Validate input
        if (a == null || b == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Both 'a' and 'b' parameters are required");
            return ResponseEntity.badRequest().body(response);
        }

        // Process multiply operation
        Double result = calculatorService.multiply(a, b);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(@RequestBody Map<String, Double> request) {
        // Extract numbers from request
        Double a = request.get("a");
        Double b = request.get("b");

        // Validate input
        if (a == null || b == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Both 'a' and 'b' parameters are required");
            return ResponseEntity.badRequest().body(response);
        }

        // Check for division by zero
        if (b == 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Division by zero is not allowed");
            return ResponseEntity.badRequest().body(response);
        }

        // Process divide operation
        Double result = calculatorService.divide(a, b);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Calculator Java Service (Multiply and Divide)");
        return ResponseEntity.ok(response);
    }
}