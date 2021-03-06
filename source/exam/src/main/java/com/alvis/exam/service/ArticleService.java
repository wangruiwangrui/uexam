package com.alvis.exam.service;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.dto.article.ArticleDTO;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleService {
    List<ArticleType> findArticleType();

    //将上传数据进行封装存储
    void saveArticle(Article articleVM);

    //返回文章信息
    PageInfo<Article> page(MessagePageRequestVM requestVM);

    //删除文章
    void deleteArticle(Article article);

    String getConText(Article article);

    void updateArticle(Article article);

    List<Article> findArticle(Integer typeId);

    PageInfo<ArticleDTO> studentPage(Integer typeId, MessageRequestVM messageRequestVM);

    String findDetails(Integer id);

    Article find(Integer id);

    List<Integer> findArticleId();

    PageInfo<Article> articlePage( Integer x,MessageRequestVM messageRequestVM);

    void trunCate();

    Integer findChapter(String chapter);

    Article findByName(String name);
}
