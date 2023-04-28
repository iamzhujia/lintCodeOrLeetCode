package com.bj58;

public class Life {
    public static void main(String s){

    }
    public void gameOfLife(int[][] board) {

        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[i].length; ++j){
                update(board,board[i][j], i, j);
            }
        }
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[i].length; ++j){
                board[i][j] = (board[i][j] & 2)>>1;
            }
        }
    }

    private void update(int[][] board, int v, int i, int j) {
        int c = getNumber(board, i, j);
        if(v == 1){
            if(c < 2 || c > 3){
                board[i][j] = 1;
            }else{
                board[i][j] = 3;
            }
        }else{
            if(c == 3){
                board[i][j] = 2;
            }
        }
    }

    private int getNumber(int[][] board, int i, int j) {
        int c = 0;
        for(int k = j-1; k < j+2; k++){
            if(in(board, i-1, k)){
                c+=(board[i-1][k]&1);
            }
        }
        for(int k = j-1; k < j+2; k++){
            if(in(board, i+1, k)){
                c+=(board[i+1][k]&1);
            }
        }
        if(in(board, i, j-1)){
            c+=(board[i][j-1] & 1);
        }
        if(in(board, i, j+1)){
            c+=(board[i][j+1] & 1);
        }
        return c;
    }

    private boolean in(int[][] board, int i, int k) {
        if(i < 0 || i > board.length - 1){
            return false;
        }
        if(k < 0 || k > board[i].length -1){
            return false;
        }
        return true;
    }
}
