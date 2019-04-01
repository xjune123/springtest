package com.test.springtest.word;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/8/2
 */
public class WordTestMain {
    public static void main(String[] args) {
        char[] s1 = {'a', 'r', 'i', 'm'};    //预置单词
        //char[] s2 = {'s', 'a', 'r', 'r', 'n'};
        char[] s2 = {'a', 'r', 'i', 'm'};
        int m = s2.length + 1, n = s1.length + 1;        //矩阵
        int[][] table = new int[m][n];
        // 矩阵的初始化
        for (int i = 0; i < n; i++) {
            table[0][i] = i;

        }
        for (int i = 0; i < m; i++) {
            table[i][0] = i;

        }
        //算法开始，向矩阵中添加数值
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                int temp = 0;
                if (s1[i] != s2[j])
                    temp = 1;
                else
                    temp = 0;
                table[j + 1][i + 1] = Min(table[j][i] + temp, table[j + 1][i] + 1, table[j][i + 1] + 1);
            }

        }
        //输出矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(table[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
    }

    private static int Min(int i, int j, int k) {
        // TODO Auto-generated method stub
        int[] tempSequence = {i, j, k};
        int min = i;
        for (int n = 0; n < 3; n++) {
            if (tempSequence[n] < min) {
                min = tempSequence[n];
            }
        }
        return min;
    }

}
