package com.example.miniprojects.application.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculator")
public class CalculatorAppController {

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("result", "");
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator,
            Model model) {

        double result = 0;
        String errorMessage = null;

        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/":
                if (num2 == 0) {
                    errorMessage = "Error: Cannot divide by zero!";
                } else {
                    result = num1 / num2;
                }
                break;
            default: errorMessage = "Invalid Operator";
        }

        // Preserve input values in the UI for better UX
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operator", operator);

        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
        } else {
            model.addAttribute("result", result);
        }

        return "calculator";
    }
}

