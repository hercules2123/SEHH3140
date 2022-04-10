package com.example.sehh3140;

/***
 * 抓取到的文章数据封装
 */
public class Article {

    private String title;
    private String author;
    private String imgUrl;
    private String context;
    private String articleUrl;


    //有几个属性还没用到，所以构造方法先用上这四个有爬取到数据的
    public Article(String title, String author, String imgUrl, String context, String articleUrl) {
        this.title = title;
        this.author = author;
        this.imgUrl = imgUrl;
        this.context = context;
        this.articleUrl = articleUrl;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }



    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", context='" + context + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                '}';
    }
}
