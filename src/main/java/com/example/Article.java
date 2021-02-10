package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
  /**
   * @return wordCount, the word count for an article
   */
  public int getWordCount() {
    return wordCount;
  }

  /**
   * @return headline, a JSON receiver class instance
   */
  public Headline getHeadline() {
    return headline;
  }

  /**
   * @return publishDate, a JSON receiver class instance
   */
  public String getPublishDate() {
    return publishDate;
  }

  /**
   * @return articleAbstract, a JSON receiver class instance
   */
  public String getArticleAbstract() {
    return articleAbstract;
  }
  
  /**
   * @return keyword, a JSON receiver class instance
   */
  public List<Keyword> getKeyword() {
    return keyword;
  }
  /**
   * @return documentType, the type of document the article is
   */
  public String getDocumentType() {
    return documentType;
  }

  private Headline headline;
  @JsonSetter("document_type")
  private String documentType;
  @JsonSetter("pub_date")
  private String publishDate;
  @JsonSetter("abstract")
  private String articleAbstract;
  @JsonSetter("keywords")
  private List<Keyword> keyword;
  @JsonSetter("word_count")
  private int wordCount;

  
  
  
}
