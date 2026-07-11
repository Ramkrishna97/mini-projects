package com.example.miniprojects;

import lombok.Data;
import lombok.RequiredArgsConstructor;


public record Project(
        String name,
        String description,
        String icon,
        String url
) {}
