package com.bj58;

public class NiuPai {
public static void main(String[] args){

    try{
        int n =300, m =200;
        NiuPai n1 = new NiuPai();
        int k = n1.bullCards(n,m);
        System.out.println(k);
        System.out.println(new NiuPai3().bullCards(n, m));
    }catch (Exception e){
        e.printStackTrace();
    }
}

private static  int a=1000000007;

    public  int bullCards(int n, int m) {
        int[][] result = new int[n + 1][m + 1];
        for(int i = 1; i <= n; ++i){
            result[i][1] = i;
            result[i][0] =1;
        }
        for(int i = 1; i <= 4; ++i){
            if(i > m ){
                break;
            }
            result[1][i] = 1;
        }
        for(int i = 2; i <= n; ++i){
            for(int j = 2; j <= m; ++j){

                for(int k = 0; k < 5; ++k){
                    if(j < k ){
                        break;
                    }
                    result[i][j] = (result[i][j]+result[i - 1][j - k])%a;


                }
            }
        }
        return result[n][m];
    }
}
