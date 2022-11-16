package com.bj58;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       float k = 1e-6f;
       System.out.println(k);
       System.out.println(maxSub(""));
        System.out.println(maxSub("123456"));
        System.out.println(maxSub("123446"));
        System.out.println(maxSub("1234456789"));
    }
    public static int maxSub(String s){
        int size = s.length();
        if(size == 0)
        return 0;
        int result = 0;
        int r = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] ars = s.toCharArray();

        for(int i = 0; i < ars.length; ++i){
            Character c = ars[i];
            Integer index = map.get(c);
            if(index == null){
                map.put(c, i);
                r++;
            }else{
                if(r > result)
                result = r;
                r= i - index;
                map.clear();
                for(int j = index + 1; j < i + 1; ++j){
                    map.put(ars[j], j);
                }
            }
        }
        if(r > result)
            result = r;
        return result;
    }
}
