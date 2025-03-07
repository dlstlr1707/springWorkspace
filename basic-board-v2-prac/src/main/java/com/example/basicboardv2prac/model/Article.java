package com.example.basicboardv2prac.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Article {
    private Long id;
    private String userId;
    private String title;
    private String content;
    private String filePath;
    private LocalDateTime created;
    private LocalDateTime updated;
}
