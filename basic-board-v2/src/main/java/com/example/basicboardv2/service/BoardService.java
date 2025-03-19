package com.example.basicboardv2.service;

import com.example.basicboardv2.dto.BoardDeleteRequestDTO;
import com.example.basicboardv2.mapper.BoardMapper;
import com.example.basicboardv2.model.Article;
import com.example.basicboardv2.model.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    public List<Article> getBoardArticles(int page, int size){
        int offset = (page - 1) * size;
        return boardMapper.getBoardArticles(
                Paging.builder()
                        .offset(offset)
                        .size(size)
                        .build()
        );
    }

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

    public int getTotalArticleCnt() {
        return boardMapper.getArticleCnt();
    }

    public Article getBoardDetail(long id) {
        return boardMapper.getArticleById(id);
    }

    public Resource downloadFile(String fileName) {
        return fileService.downloadFile(fileName);
    }

    /*
    public void updateArticle(long id, String userId, String title, String content, MultipartFile file) {
        String path = null;
        if(!file.isEmpty()){
            path = fileService.fileUpload(file);
        }
        System.out.println(userId);
        System.out.println(title);

        Article newArticle = Article.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .filePath(path)
                .updated(LocalDateTime.now()).build();
        boardMapper.updateArticle(id,newArticle);
    }

    @Transactional
    public void deleteBoardById(long id) {
        Article findArticle = boardMapper.getArticleById(id);
        if(findArticle != null){
            boardMapper.deleteBoardById(id);
            fileService.deleteFile(findArticle.getFilePath());
        }
    }
     */

    // 강사님 코드
    public void updateArticle(
            long id,
            String title,
            String content,
            MultipartFile file,
            Boolean fileChanged,
            String filePath
    ) {
        String path = null;
        if(!file.isEmpty()){
            path = fileService.fileUpload(file);
        }
        if(fileChanged){
            fileService.deleteFile(path);
        }else{
            path = filePath;
        }

        boardMapper.updateArticle(
                Article.builder()
                        .id(id)
                        .title(title)
                        .content(content)
                        .updated(LocalDateTime.now())
                        .filePath(path)
                        .build()
        );
    }

    public void deleteBoardById(long id, BoardDeleteRequestDTO requestDTO) {
        fileService.deleteFile(requestDTO.getFilePath());
        boardMapper.deleteBoardById(id);
    }
}
