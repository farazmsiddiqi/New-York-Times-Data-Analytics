package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Headline {

  /**
   * @return main, the main headline
   */
  public String getMain() {
    return main;
  }
  
  private String main;

}
