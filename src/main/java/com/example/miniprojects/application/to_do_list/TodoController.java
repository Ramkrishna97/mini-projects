package com.example.miniprojects.application.to_do_list;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public String home(Model model) {

        model.addAttribute("todos", todos);
        model.addAttribute("total", todos.size());

        long completed = todos.stream()
                .filter(Todo::isCompleted)
                .count();

        model.addAttribute("completed", completed);

        return "todo";
    }

    @PostMapping("/add")
    public String add(@RequestParam String task) {

        if (task != null && !task.trim().isEmpty()) {
            Todo todo = new Todo(counter.incrementAndGet(), task);

            System.out.println("ID = " + todo.getId());

            todos.add(todo);
        }

        return "redirect:/todo";
    }

    @GetMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {

        todos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setCompleted(!t.isCompleted()));

        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        todos.removeIf(t -> t.getId().equals(id));

        return "redirect:/todo";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Todo todo = todos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        model.addAttribute("todo", todo);
        model.addAttribute("todos", todos);

        long completed = todos.stream()
                .filter(Todo::isCompleted)
                .count();

        model.addAttribute("total", todos.size());
        model.addAttribute("completed", completed);

        return "todo";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id,
                         @RequestParam String task) {

        todos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setTask(task));

        return "redirect:/todo";
    }

}

