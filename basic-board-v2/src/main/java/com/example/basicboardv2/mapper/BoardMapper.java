package com.example.basicboardv2.mapper;

import com.example.basicboardv2.model.Article;
import com.example.basicboardv2.model.Paging;
import lombok.Value;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void saveArticle(Article article);
    List<Article> getBoardArticles(Paging paging);
    int getArticleCnt();
    Article getArticleById(long id);
    void updateArticle(Article article);
    void deleteBoardById(long id);
}
