package com.example.basicboardv2prac.controller;

import com.example.basicboardv2prac.dto.BoardListResponseDTO;
import com.example.basicboardv2prac.model.Article;
import com.example.basicboardv2prac.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {
    private final BoardService boardService;

    @GetMapping
    public BoardListResponseDTO getBoards(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "size",defaultValue = "10") int size
    ) {
        List<Article> articles = boardService.getBoardArticles(page, size);

        int totalArticleCnt = boardService.getTotalArticleCnt();

        boolean last = (page * size) >= totalArticleCnt;

        return BoardListResponseDTO.builder()
                .articles(articles)
                .last(last)
                .build();
    }

    @PostMapping
    public void saveArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("hiddenUserId") String userId,
            @RequestParam("file") MultipartFile file
    ){
        boardService.saveArticle(userId,title,content,file);
    }
}
