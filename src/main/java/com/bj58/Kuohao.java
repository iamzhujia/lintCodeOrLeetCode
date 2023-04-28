package com.bj58;

public class Kuohao {
    //括号
    public static void main(String[] args){
        boolean b =  new Kuohao().checkValidString("((*)*****(");//"(*))*)((");
        System.out.println(b);
    }

    public boolean checkValidString(String s) {
        s = s.replace("()","");
        int len = s.length();
        while(true){
            s = s.replace("()","");
            int le = s.length();
            if(len==le){
                break;
            }
            len = le;
        }
        if (len == 0) {
            return true;
        }
        int j=0,k=0,l=0;
        for (int i = 0; i < len; i++) {
            char a = s.charAt(i);
            if(a=='('){
                j++;
            }else if(a==')'){
                k++;
            }else{
                l++;
            }
            if(k>(j+l)){
                return false;
            }
        }
        if(j>(k+l)){
            return false;
        }
        j = 0;
        k = 0;
        l = 0;
        for (int i = len - 1; i > -1; i--) {
            char a = s.charAt(i);
            if(a=='('){
                j++;
            }else if(a==')'){
                k++;
            }else{
                l++;
            }
            if(j>(k+l)){
                return false;
            }

        }
        if(k>(j+l)){
            return false;
        }
        return true;
    }
}
