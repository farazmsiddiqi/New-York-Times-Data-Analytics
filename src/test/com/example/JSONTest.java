package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.plot.Plot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class JSONTest {
    /**
     * General Notes:
     * Testing is a bit different for this project, because there is only one possible input.
     * The assignment states that "you’re deciding the functionality", so I am deciding the sc-
     * ope of this project to be January 2019. There is only one possible input because one JSON
     * file gives all the data for Jan 2019. Though my code can handle a different
     * month (or even a set of months), I would have to create a couple new test suites
     * for different JSON packages.
     * 
     * Class Design:
     * JsonContainerNYT.java contains a Response (in the form of an object) from the NYT API.
     * The Response object contains a list of NYT Articles (Article class) from January, 2019.
     * Each Article has a Headline (class) and a Keyword (class) list.
     * The Headline class consists of a header.
     * The Keyword class consists of a keyword category, and a keyword name.
     *
     * Levels 
     * (1) JsonConxtainerNYT
     * (1) PieChart
     * (2) Response
     * (3) Article
     * (4) Headline
     * (4) Keyword
     * 
     * Testing Strategy:
     * Null Tests
     * Default Value Tests
     * Correct Value Tests
     *
     * 
     */

    @Before
    public void setUp() throws IOException {
        final File nytJsonFile = new File("src/main/resources/NYT_JSON.json");
        jsonContainerNYT = new ObjectMapper().readValue(nytJsonFile, JsonContainerNYT.class);
        assertNotNull(jsonContainerNYT);
    }

    /** Json Receiver Object Tests **/

    @Test
    public void checkJsonContainerNYTClassNull() {
        assertNotNull(JsonContainerNYT.class);
    }
    @Test
    public void checkResponseClassNull() {
        assertNotNull(Response.class);
    }
    @Test
    public void checkArticleClassClassNull() {
        assertNotNull(Article.class);
    }
    @Test
    public void checkHeadlineClassNull() {
        assertNotNull(Headline.class);
    }
    @Test
    public void checkKeywordClassNull() {
        assertNotNull(Headline.class);
    }
    
    /** calcNumArticlesTrumpMentioned tests **/

    // calcNumArticlesTrumpMentioned returns an int, and can therefore never be null. (hence, no null test)

    // default value test
    @Test
    public void checkCalcNumArticlesTrumpMentionedDefaultValue() {
        final int defaultValue = 0;
        assertNotEquals(defaultValue, jsonContainerNYT.calcNumArticlesTrumpMentioned());
    }
    
    // correct value test
    @Test
    public void checkCalcNumArticlesTrumpMentionedCorrectValue() {
        final int correctNumTimesTrumpMentioned = 324;
        assertEquals(correctNumTimesTrumpMentioned, jsonContainerNYT.calcNumArticlesTrumpMentioned());
    }

    /** calcDayMostArticlesPublished tests **/

    // null test
    @Test
    public void checkCalcDayMostArticlesPublishedNull() {
        assertNotNull(jsonContainerNYT.calcDayMostArticlesPublished());
    }
    
    // default value test
    @Test
    public void checkCalcDayMostArticlesPublishedDefaultValue() {
        final String defaultValue = "largest key does not exist";
        assertNotEquals(defaultValue, jsonContainerNYT.calcDayMostArticlesPublished());
    }
    
    // correct value test
    @Test
    public void checkCalcDayMostArticlesPublishedCorrectValue() {
        final String correctDate = "2019-01-20";
        assertEquals(correctDate, jsonContainerNYT.calcDayMostArticlesPublished());
    }
    
    /** calcMostUsedKeywordCategory tests **/

    // null test
    @Test
    public void checkCalcMostUsedKeywordCategoryNull() {
        assertNotNull(jsonContainerNYT.calcMostUsedKeywordCategory());
    }

    // default value test
    @Test
    public void checkCalcMostUsedKeywordCategoryDefaultValue() {
        final String defaultValue = "largest key does not exist";
        assertNotEquals(defaultValue, jsonContainerNYT.calcMostUsedKeywordCategory());
    }

    // correct value test
    @Test
    public void checkCalcMostUsedKeywordCategoryCorrectValue() {
        final String correctDate = "organizations";
        assertEquals(correctDate, jsonContainerNYT.calcMostUsedKeywordCategory());
    }
    
    /** calcAvgWordCount tests **/

    // calcAvgWordCount returns an int, and can therefore never be null. (hence, no null test)

    // default value test
    @Test
    public void checkCalcAvgWordCountDefaultValue() {
        final int defaultValue = 0;
        assertNotEquals(defaultValue, jsonContainerNYT.calcAvgWordCount());
    }

    // correct value test
    @Test
    public void checkCalcAvgWordCountCorrectValue() {
        final int correctWordCount = 1876;
        assertEquals(correctWordCount, jsonContainerNYT.calcAvgWordCount());
    }

    /** PieChart tests **/
    
    // correct value test
    @Test
    public void checkGenerateTrumpPieChartCorrectValue() throws IOException {
         PieChart testPieChart = new PieChart("test_pie_chart.java");
         Plot testPlot = testPieChart.generateTrumpPieChart().getPlot();
        assertEquals(testPlot, testPieChart.generateTrumpPieChart().getPlot());
    }

    private JsonContainerNYT jsonContainerNYT;
}