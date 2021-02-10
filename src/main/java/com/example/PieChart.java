package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

public class PieChart extends ApplicationFrame {

  public PieChart(String title) throws IOException {
    super(title);
    // initializes the content window to the pie chart
    setContentPane(new ChartPanel(generateTrumpPieChart()));
  }

  /**
   * Generates a pie chart about trump.
   *
   * @return trumpPieChart, a pie chart featuring the percentage of articles Trump was mentioned in
   *     Jan 2019
   * @throws IOException for the JsonContainerNYT JSON.
   */
  public JFreeChart generateTrumpPieChart() throws IOException {

    // initializes trump chart data
    final File nytJsonFile = new File("src/main/resources/NYT_JSON.json");
    final JsonContainerNYT jsonContainerNYT =
        new ObjectMapper().readValue(nytJsonFile, JsonContainerNYT.class);
    jsonContainerNYT.calcNumArticlesTrumpMentioned();

    // for readability
    final int numArticlesTrumpMentioned = jsonContainerNYT.getNumTimesTrumpMentioned();
    final int totalNumArticles = jsonContainerNYT.getTotalNumArticles();

    // building the pie chart
    final JFreeChart trumpPieChart =
        ChartFactory.createPieChart(
            "Percentage of Articles Trump Mentioned vs Not Mentioned", // title
            generateTrumpPieChartDataset(), // from private helper fn below
            true, // includes a legend
            true, // includes a tooltip
            false // used to generate urls
            );

    /*
    Math.round() rounds immediately after the decimal point.
    multiply .07233564787463 by 10,000 => 723.3564787463
    Math.round(723.3564787463) = 723
    723 / 100.0 = 7.23
     */
    final double percentTrumpMentioned =
        Math.round(((double) numArticlesTrumpMentioned / totalNumArticles) * 10000) / 100.0;
    String subtitle =
        "Trump accounted for "
            + percentTrumpMentioned // percentage value
            + "% of total articles written ("
            + numArticlesTrumpMentioned
            + "/"
            + totalNumArticles
            + ") "
            + "in January.";

    trumpPieChart.addSubtitle(new TextTitle(subtitle));
    // sets background of pie chart itself
    // no specific jpg source for background, hence null
    trumpPieChart.setBackgroundPaint(null);

    return trumpPieChart;
  }

  /**
   * Helper fn that populates trumpPieChart dataset
   *
   * @return trumpPieChartDataset takes trump statistics to build trumpPieChart.
   * @throws IOException for the JsonContainerNYT JSON.
   */
  private DefaultPieDataset<String> generateTrumpPieChartDataset() throws IOException {
    // initializes trump chart data
    final File nytJsonFile = new File("src/main/resources/NYT_JSON.json");
    final JsonContainerNYT jsonContainerNYT =
        new ObjectMapper().readValue(nytJsonFile, JsonContainerNYT.class);
    jsonContainerNYT.calcNumArticlesTrumpMentioned();

    // for readability
    final int numArticlesTrumpMentioned = jsonContainerNYT.getNumTimesTrumpMentioned();
    final int totalNumArticles = jsonContainerNYT.getTotalNumArticles();

    // initializing trumpPieChartDataSet values
    trumpPieChartDataset.setValue("Articles About Trump", numArticlesTrumpMentioned);
    trumpPieChartDataset.setValue(
        "Articles Not About Trump", totalNumArticles - numArticlesTrumpMentioned);

    return trumpPieChartDataset;
  }

  private final DefaultPieDataset<String> trumpPieChartDataset = new DefaultPieDataset<>();
}
