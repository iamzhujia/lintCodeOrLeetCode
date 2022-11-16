package com.bj58;

public class Test {
    public static void main(String[] args){
        String s = new Test().bcb("12G2C2G1A3T", "2C10T3G2T1G2A");
        int i = "T".charAt(0);
        System.out.println(s);
    }

    private String bcb(String Gene1, String Gene2) {
        int length0 = Gene1.length();
        int length1 = Gene2.length();
        int right = 0;
        int total = 0;
        int c1 = 0;
        int c2 = 0;
        int index0 = 0;
        int index1 = 0;
        int y0 = 0;
        int y1 = 0;
        int t0 = 0;
        int t1 = 0;
        while(index0 < length0 || index1 < length1){
            System.out.println("index=" + index0 +"," + index1);
            if(y0 > 0){
                int[] r = readNextInt(index1, Gene2);
                c2 = r[0];
                t1 = r[2];
                index1 = r[1] + 1;
                if(y0 > c2){
                    int t = y0 - c2;
                    total += c2;
                    if(t0 == t1)
                        right += c2;
                    y0 = t;
                    y1 = 0;
                }else if(y0 < c2){
                    int t = c2 - y0;
                    total += y0;
                    if(t0 == t1)
                       right += y0;
                    y0 = 0;
                    y1 = t;
                }else{
                    total += y0;
                    if(t0 == t1)
                      right += y0;
                    y0 = 0;
                    y1 = 0;
                }
            }else if(y1 > 0){
                int[] r = readNextInt(index0, Gene1);
                c1 = r[0];
                t0 = r[2];
                index0 = r[1] + 1;
                if(y1 > c1){
                    int t = y1 - c1;
                    total += c1;
                    if(t0 == t1)
                        right += c1;
                    y1 = t;
                    y0 = 0;
                }else if(y1 < c1){
                    int t = c1 - y1;
                    total += y1;
                    if(t0 == t1)
                        right += y1;
                    y0 = t;
                    y1 = 0;
                }else{
                    total += y1;
                    if(t0 == t1)
                        right += y1;
                    y1 = 0;
                    y0 = 0;
                }
            }else{
                int[] r = readNextInt(index0, Gene1);
                c1 = r[0];
                t0 = r[2];
                index0 = r[1]+1;
                r = readNextInt(index1, Gene2);
                c2 = r[0];
                t1 = r[2];
                index1 = r[1]+1;
                if(c1 > c2){
                    int t = c1 - c2;
                    total += c2;
                    if(t0 == t1)
                        right += c2;
                    y0 = t;
                    y1 = 0;
                }else if(c1 < c2){
                    int t = c2 - c1;
                    total += c1;
                    if(t0 == t1)
                        right += c1;
                    y1 = t;
                    y0 = 0;
                }else{
                    total += c1;
                    if(t0 == t1)
                        right += c1;
                    y1 = y0 = 0;
                }
            }
        }
        return right + "/" + total;
    }

    private int[] readNextInt(int index, String s) {
        int k = 0;
        int c = 0;
        while((c = s.charAt(index)) < 58 ){
            k = k*10 + (c - 48);
            index++;
        }
        return new int[]{ k, index, c};
    }
}
