package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Keyword {

  /**
   * @return keywordCategory, the category of a keyword
   */
  public String getKeywordCategory() {
    return keywordCategory;
  }

  @JsonSetter("name")
  private String keywordCategory;
}
