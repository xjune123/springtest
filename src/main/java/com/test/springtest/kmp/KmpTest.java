package com.test.springtest.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/2/26 下午5:24
 */
public class KmpTest {

    public static void main(String[] args) {

        String inputWord = "BBC ABCDAB ABCDABCDABDE";
        String keyWord = "ABCDABD";
        List<Integer> wordLengthList = find(inputWord, keyWord);

        char[] inputWordChar = inputWord.toCharArray();
        char[] keWordChar = keyWord.toCharArray();
        boolean match = false;
        int matchIndex = 0;
        int i = 0;
        while (i < inputWordChar.length ) {
            for (int j = 0; j < keWordChar.length; j++) {
                if (inputWordChar[i] == keWordChar[j]) {
                    i = i + 1;
                } else {
                    i = i + (j - wordLengthList.get(j));
                    j = 0;
                }
                if (j == keWordChar.length - 1) {
                    match = true;
                    matchIndex = i;
                    break;
                }


            }

            if(match){
                break;
            }
        }

        System.out.println("match:" + match);
        System.out.println("matchIndex:" + matchIndex);


    }

    public static List<Integer> find(String inputWord, String keyWord) {

        List<Integer> wordLengthList = new ArrayList<>();
        //优先计算每个字符串最大相似长度
        for (int i = 1; i <= keyWord.length(); i++) {
            System.out.println("---------" + i + "---------");

            List<String> frontList = new ArrayList<>();
            List<String> backList = new ArrayList<>();

            String limitWord = keyWord.substring(0, i);
            System.out.println("limitWord:" + limitWord);

            for (int j = 1; j < limitWord.length(); j++) {
                frontList.add(limitWord.substring(0, j));
                backList.add(limitWord.substring(j, limitWord.length()));
            }

            System.out.println("frontList:");

            for (String word : frontList) {
                System.out.println(word);
            }
            System.out.println("backList:");

            for (String word : backList) {
                System.out.println(word);
            }

            frontList.retainAll(backList);


            String maxWord = "";
            for (String word : frontList) {
                if ("".equals(word)) {
                    maxWord = word;
                } else if (word.length() > maxWord.length()) {
                    maxWord = word;
                }
            }

            wordLengthList.add(maxWord.length());

        }

        for (Integer len : wordLengthList) {
            System.out.println(len);
        }

        return wordLengthList;

    }
}
