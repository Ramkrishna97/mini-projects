package com.example.miniprojects.application.to_do_list;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {

    private Long id;
    private String task;
    private boolean completed;

    public Todo() {
    }
    public Todo(Long id, String task) {
        this.id = id;
        this.task = task;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}