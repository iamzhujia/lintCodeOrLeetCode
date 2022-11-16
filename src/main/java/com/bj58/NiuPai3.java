package com.bj58;

public class NiuPai3 {

    public int bullCards(int n, int m){
    int a=1000000007;
    //dp[i][j]

    int dp[][]=new int[n+1][m+1];
    for(int i=1;i<=m;i++){
        if(i>4)break;
        dp[1][i]=1;

    }

    for(int i=1;i<=n;i++){
        dp[i][1]=i;
    }


    for(int i=2;i<=n;i++){
        for(int j=2;j<=m;j++){
            for(int k=0;k<=4;k++){
                dp[i][j]=(dp[i-1][j-k]+dp[i][j])%a;
                if(j<=4 && j-k==0){
                    dp[i][j]+=1;
                    break;
                }
            }
        }
    }

    return dp[n][m];

}
}
