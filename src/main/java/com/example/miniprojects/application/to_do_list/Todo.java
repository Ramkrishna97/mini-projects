package com.example.miniprojects.application.to_do_list;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Todo {

    private Long id;
    private String task;
    private boolean completed;

    public Todo(long l, String task) {
        this.id =id;
        this.task=task;
    }
}