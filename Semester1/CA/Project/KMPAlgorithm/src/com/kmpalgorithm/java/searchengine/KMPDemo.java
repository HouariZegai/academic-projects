package com.kmpalgorithm.java.searchengine;

import java.util.List;

public class KMPDemo {
    public static void main(String[] args) {
        // The text you are searching for
        String[] text = {"hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithmhouari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My algorithmhouwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithm houari",
                "hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houari.hello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife houarihello My houwekaar name Hellohoua iskapotchino houarHouari ZEGAIlife algorithm houari"};
        // Text to search for
        String pattern = "algorithm";



        Double[] times = new Double[10];
        long startTime, endTime;
        int index;

        for(int i = 0; i < text.length; i++) {
            KMP kmp = new  KMP(text[i]);
            startTime = System.nanoTime();
            //kmp.setLetterSensitive(true);
            index = kmp.indexOf(pattern);
            endTime = System.nanoTime();

            if(index != -1) {
                times[i] = (endTime - startTime) / 100000d;
            }
        }

        for(int i = 0; i < times.length; i++)
            System.out.println(times[i]);

//        List<Integer> foundIndex = kmp.searchAndGetIndex(pattern);
//        if(!foundIndex.isEmpty()) {
//            for(Integer index : foundIndex)
//                System.out.println("At index: " + index);
//        }
    }
}
