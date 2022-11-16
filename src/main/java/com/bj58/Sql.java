package com.bj58;

import javax.security.sasl.SaslClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sql {
    public static void main(String[] args) throws Exception {
        String a = "";
        Scanner scanner  =  new Scanner(new File("D:\\00000\\1111.txt"));
        while(scanner.hasNextLine()){
            a += (scanner.nextLine() + "\n");
        }
        Pattern p = Pattern.compile("hdp_jinrong_qiangui_defaultdb.+?view");//("ifnull\\(.+?\\)");
        Matcher m =  p.matcher(a);
        //List<String[]> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        while(m.find()){
            String s = m.group();
           // int s1 = s.indexOf("(");
           // int s2 = s.indexOf(",");
            //int s3 = s.indexOf(")");
            //String str = s.substring(s1+1,s2);
            //System.out.println(str);
            //list.add(new String[]{s});
            set.add(s);
        }
        scanner.close();
        for(String arr : set){
            a = a.replace(arr,"(select * from " + arr + " where daystr='20220930')");
        }
        System.out.println(a);
    }
}
