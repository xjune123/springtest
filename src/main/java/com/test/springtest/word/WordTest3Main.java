package com.test.springtest.word;

import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/8/2
 */
public class WordTest3Main {
    public static void main(String[] args) {
        String str1 = "in charge"; //
        String str2 = "in charge of";//正确答案
        int result = getLevenshteinDistance(str1, str2);
        System.out.println(result);
        if (result != 0) {
            System.out.println(1 - Double.valueOf(result));
            System.out.println(Double.valueOf(str2.length()));
            System.out.println((Double.valueOf(str2.length()) - Double.valueOf(result)) / Double.valueOf(str2.length()))
            ;
        }
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        int result2 = levenshteinDistance.apply(str1, str2);
        int maxLength = str1.length() > str2.length() ? str1.length() : str2.length();
        System.out.println(result2);
        System.out.println(maxLength);
        System.out.println("result:" + ((Double.valueOf(maxLength) - Double.valueOf(result2)) / Double.valueOf(maxLength)));

    }

    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int n = s.length(); // length of s
        int m = t.length(); // length of t
        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }
        if (n > m) {
            // swap the input strings to consume less memory
            final CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }
        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d
        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t
        char t_j; // jth character of t
        int cost; // cost
        for (i = 0; i <= n; i++) {
            p[i] = i;
        }
        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;
            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }
        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }
}
