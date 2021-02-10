package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonContainerNYT {

  /**
   * Calculates the number of articles where Trump is in the headline or abstract.
   *
   * @return numHeadlinesTrumpMentioned, an integer
   * representing the number of times Trump was mentioned.
   */
  public int calcNumArticlesTrumpMentioned() {
    int numTimesTrumpMentioned = 0;
    
    for (Article article : getResponse().getArticleList()) {
      totalNumArticles++;
      if (article.getHeadline().getMain().toLowerCase().contains("trump")
          || article.getArticleAbstract().contains("trump")) {
        
        numTimesTrumpMentioned++;
      }
    }

    this.numTimesTrumpMentioned = numTimesTrumpMentioned;

    return numTimesTrumpMentioned;
  }
  
  /**
   * Calculates the day of the month when the most articles were published
   *
   * @return findLargestValueInMap(dateToDateFrequencyMap),
   * a private helper function that returns the String date associated
   * with the map's largest integer value.
   */
  public String calcDayMostArticlesPublished() {
    
    /*
    Named according to https://medium.com/better-programming/useful-tips-for-naming-your-variables-8139cc8d44b5.
    dateToDateFrequencyMap holds a date as a key, and the number of articles published on that date as a value.
    */
    Map<String, Integer> dateToDateFrequencyMap = new HashMap<>();
    
    // defining start and end YYYY-MM-DD values for substring fn.
    int dateStringBeginning = 0, dateStringEnd = 10;
    // defining first time YYYY-MM-DD date enters map.
    int defaultValDate = 0;
    // adds 1 to date frequency value.
    int singularDate = 1;
    
    for (Article article : getResponse().getArticleList()) {
      // added variable for readability.
      String articlePublishDate = article.getPublishDate().substring(dateStringBeginning, dateStringEnd);
      
      // populates map with date value, and the number of articles published on that date.
      dateToDateFrequencyMap.put(
          articlePublishDate,
          dateToDateFrequencyMap
              .getOrDefault(articlePublishDate, defaultValDate) + singularDate
      );
    }
    
    return findLargestValueInMap(dateToDateFrequencyMap);
  }

  /**
   * Calculates the most used keyword category by all articles
   * keyword category example:
   * keywords = dad, mom, son, daughter
   * keywordCategory = family
   *
   * @return findLargestValueInMap(keywordCategoryToFrequencyMap),
   * a private helper function that returns the String keywordCategory associated
   * with the map's largest integer value.
   */
  public String calcMostUsedKeywordCategory() {
    /*
    Named according to https://medium.com/better-programming/useful-tips-for-naming-your-variables-8139cc8d44b5.
    dateToDateFrequencyMap holds a date as a key, and the number of articles published on that date as a value.
    */
    Map<String, Integer> keywordCategoryToFrequencyMap = new HashMap<>();
    // defining first time keywordCategory enters map.
    int defaultValKeywordCategoryFrequency = 0;
    // adds 1 to keywordCategory frequency value.
    int singularKeywordCategory = 1;
    
    for (Article article : getResponse().getArticleList()) {
      
      for (int i = 0; i < article.getKeyword().size(); i++) {
        // added variable for readability.
        String articleKeywordCategory = article.getKeyword().get(i).getKeywordCategory();
        // populates map with keywordCategory value, and the number of articles published with that keyword.
        keywordCategoryToFrequencyMap.put(
            articleKeywordCategory,
            keywordCategoryToFrequencyMap
                .getOrDefault(articleKeywordCategory, defaultValKeywordCategoryFrequency) + singularKeywordCategory
        );
      }
    }
    
    return findLargestValueInMap(keywordCategoryToFrequencyMap);
  }

  /**
   * Calculates the average word count of all articles with words.
   *
   * @return avgWordCount, the average word count rounded down
   * to the nearest integer. 
   */
  public int calcAvgWordCount() {
    
    int totalWordCount = 0;
    // Articles that aren't countable such as audio files, multimedia, etc.
    int totalCountableArticles = getResponse().getArticleList().size(); // 4482
    
    // integers can't be null, so no null check required
    for (Article article : getResponse().getArticleList()) {
      // if the article cannot be counted, then dont count it.
      for (String uncountableArticleType : findUncountableArticleTypes()) {
        // if the current article is a countable article type, 
        if (!article.getDocumentType().equals(uncountableArticleType)) {
          
          totalWordCount += article.getWordCount();
        } else {
          totalCountableArticles--;
        }
      }
    }
    
    try {
      
      final int avgWordCount = totalWordCount / totalCountableArticles;
      
      return avgWordCount;
    } catch (RuntimeException e) {
      
      throw new RuntimeException("Divided by zero. Make sure you input articles with words.");
    }
  }
  
  /**
   * @return response, a JSON receiver class instance
   */
  public Response getResponse() {
    return response;
  }

  /**
   * @return numTimesTrumpMentioned, the number of times trump was mentioned
   */
  public int getNumTimesTrumpMentioned() {
    return numTimesTrumpMentioned;
  }

  /**
   * @return totalNumArticles, the total number of articles
   */
  public int getTotalNumArticles() {
    return totalNumArticles;
  }

  /**
   * Helper fn that finds the largest integer value in the map
   *
   * @return keyForMaxIntCount, the String corresponding
   * to the map's largest integer value.
   */
  private String findLargestValueInMap(final Map<String, Integer> map) {
    // counter variable to help determine keyForMaxIntCount.
    int maxIntCount = 0;
    // default value caught later.
    String keyForMaxIntCount = "largest key does not exist";
    
    for (String date : map.keySet()) {

      if (map.get(date) > maxIntCount) {
        keyForMaxIntCount = date;
        maxIntCount++;
        
      }
    }

    // catching default value.
    if (keyForMaxIntCount.equals("largest key does not exist")) {
      throw new IllegalArgumentException("JSON data does not contain correctly formatted dates");
    }
    
    return keyForMaxIntCount;
  }

  /**
   * Helper fn that informs what article types result
   * in a word count of 0.
   *
   * @return uncountableArticleTypes, a set of articles
   * that convey news without words (videos, etc) which
   * have a word count of 0.
   */
  private Set<String> findUncountableArticleTypes() {
    Set<String> uncountableArticleTypes = new HashSet<>();
    
    for (Article article : getResponse().getArticleList()) {
      
      if (article.getWordCount() == 0) {
        
        uncountableArticleTypes.add(article.getDocumentType());
      }
    }
    
    return uncountableArticleTypes;
  }
  
  private Response response;
  private int numTimesTrumpMentioned;
  private int totalNumArticles;
  
}
