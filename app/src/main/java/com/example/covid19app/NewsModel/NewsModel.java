package com.example.covid19app.NewsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel
{
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResult")
    @Expose
    private int totalResult;

    @SerializedName("articles")
    @Expose
    private List<Article> articleList;

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTotalResult(int totalResult)
    {
        this.totalResult = totalResult;
    }

    public void setArticleList(List<Article> articleList)
    {
        this.articleList = articleList;
    }

    public String getStatus()
    {
        return status;
    }

    public List<Article> getArticleList()
    {
        return articleList;
    }

    public int getTotalResult()
    {
        return totalResult;
    }
}
