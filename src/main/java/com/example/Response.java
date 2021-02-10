package com.example;

import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;

public class Response {
  
  /**
   * @return articleList, a JSON receiver class instance
   */
  public List<Article> getArticleList() {
    return articleList;
  }

  @JsonSetter("docs")
  private List<Article> articleList;
  
}
