package com.example.basicboardv2prac.mapper;

import com.example.basicboardv2prac.model.Article;
import com.example.basicboardv2prac.model.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void saveArticle(Article article);
    List<Article> getBoardArticles(Paging paging);

    int getArticleCnt();
}
