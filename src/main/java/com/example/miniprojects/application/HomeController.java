package com.example.miniprojects.application;

import com.example.miniprojects.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Project> projects = List.of(

                new Project(
                        "Calculator",
                        "Basic Arithmetic Calculator",
                        "🧮",
                        "/calculator/"
                ),

                new Project(
                        "Tic Tac Toe",
                        "Play Tic Tac Toe",
                        "🎮",
                        "/tictactoe/"
                ),
                new Project(
                        "To Do List",
                        "To do with CRUD Operation",
                        "⌨",
                        "/todo"
                )

        );

        model.addAttribute("projects", projects);

        return "index";
    }
}
