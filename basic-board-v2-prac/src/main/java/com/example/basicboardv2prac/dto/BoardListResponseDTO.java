package com.example.basicboardv2prac.dto;

import com.example.basicboardv2prac.model.Article;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDTO {
    List<Article> articles;
    boolean last;
}
