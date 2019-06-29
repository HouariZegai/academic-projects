package com.kmpalgorithm.java.controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.kmpalgorithm.java.searchengine.KMP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class GraphComplexityController implements Initializable {

    @FXML
    private LineChart<String, Double> lineChart;
    
    XYChart.Series seriesBruteForce, seriesKMP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Data to search in
        String[] text = {"hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari. algorithm",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My algorithmhouwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarialgorithm",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithm houari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithm houari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithm houari "};
        // Text to search for
        String pattern = "algorithm";

        Double[] timesKMP  = getTimesKMP(text, pattern);
        Double[] timesBruteForce  = getTimesBruteForce(text, pattern);

        //defining a series
        seriesBruteForce = new XYChart.Series();
        seriesBruteForce.setName("Brute Force Algorithm");

        seriesKMP = new XYChart.Series();
        seriesKMP.setName("KMP Algorithm");

        // Initialize series
        for (int i = 0; i < text.length; i++) {
            seriesBruteForce.getData().add(new XYChart.Data<>(text[i].length()  + "", timesBruteForce[i]));
            seriesKMP.getData().add(new XYChart.Data<>(text[i].length() + "", timesKMP[i]));
        }
        
        lineChart.getData().addAll(seriesBruteForce, seriesKMP);
    }   

    private Double[] getTimesKMP(String[] text, String pattern) {
        Double[] times = new Double[text.length];
        long startTime, endTime;
        int index;

        for(int i = 0; i < text.length; i++) {
            KMP kmp = new  KMP(text[i]);
            startTime = System.nanoTime();
            index = kmp.indexOf(pattern);
            endTime = System.nanoTime();

            if(index != -1) {
                times[i] = (endTime - startTime) / 100000d;
            }
        }

        return times;
    }

    private Double[] getTimesBruteForce(String[] text, String pattern) {
        Double[] times = new Double[text.length];
        long startTime, endTime;
        int index;

        for(int i = 0; i < text.length; i++) {
            startTime = System.nanoTime();
            index = doesContains(text[i], pattern);
            endTime = System.nanoTime();

            if(index != -1) {
                times[i] = (endTime - startTime) / 100000d;
            }
            else
                times[i] = 0d;
        }

        return times;
    }

    public static int doesContains(String text,String pattren){
        int index=-1;
        int tLength=text.length();// getting text length
        int pLength=pattren.length(); // getting pattern length
        for(int i=0;i<tLength-pLength;i++){ // for text
            int j; // for pattern
            for(j=0;j<pLength;j++){
                if(text.charAt(i+j)!=pattren.charAt(j)) break;
            }
            if(j==pLength) {index=i;break ;}
        }
        return index;
    }
}
