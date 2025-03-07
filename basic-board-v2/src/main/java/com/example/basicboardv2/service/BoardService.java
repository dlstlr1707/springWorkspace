package com.example.basicboardv2.service;

import com.example.basicboardv2.mapper.BoardMapper;
import com.example.basicboardv2.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    @Transactional
    public void saveArticle(String userId, String title, String content, MultipartFile file){
        String path = null;
        if(!file.isEmpty()){
            path = fileService.fileUpload(file);
        }

        boardMapper.saveArticle(
                Article.builder()
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .filePath(path)
                        .build()
        );
    }
}
