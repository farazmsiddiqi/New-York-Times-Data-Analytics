package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.jfree.ui.RefineryUtilities;

public class Main {
  public static void main(String[] args) throws IOException {
    File nytJsonFile = new File("src/main/resources/NYT_JSON.json");
    JsonContainerNYT jsonContainerNYT =
        new ObjectMapper().readValue(nytJsonFile, JsonContainerNYT.class);

    // for calcNumArticlesTrumpMentioned()
    System.out.println(
        "Number of articles Trump is the topic of: "
            + jsonContainerNYT.calcNumArticlesTrumpMentioned());

    // for calcDayMostArticlesPublished()
    System.out.println(
        "Busiest day in January: " + jsonContainerNYT.calcDayMostArticlesPublished());

    // for calcMostUsedKeywordCategory()
    System.out.println(
        "Most used keyword category: " + jsonContainerNYT.calcMostUsedKeywordCategory());

    // for calcAvgWordCount()
    System.out.println("Average word count: " + jsonContainerNYT.calcAvgWordCount());

    // for pie chart generation
    final PieChart trumpPieChart = new PieChart("trump_pie_chart.java");
    trumpPieChart.setSize(560, 367);
    RefineryUtilities.centerFrameOnScreen(trumpPieChart);
    System.out.println("Displaying pie chart...");
    trumpPieChart.setVisible(true);
  }
}
