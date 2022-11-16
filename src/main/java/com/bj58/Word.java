package com.bj58;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    public static void main(String[] args)throws Exception{
        System.out.println("Please input the day:");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int day = Integer.parseInt(br.readLine());
        System.out.println("Please input the mode:");
        int mode = Integer.parseInt(br.readLine());
        String filePath = "D:\\work\\2019\\ky\\final\\new\\" + day + "\\1.txt";
        FileInputStream inputStream = new FileInputStream(filePath );
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader fBuffer = new BufferedReader(inputStreamReader);
        String result = null;
        List<String[]> list = new ArrayList<>();
        List<String[]> iList = new ArrayList<>();
        int c = 0;
        Pattern pattern = Pattern.compile("(\\s)+");
        while((result = fBuffer.readLine()) != null){
            String[] arr = result.split("(\\s)+");
            if(arr.length < 2){
                continue;
            }
            Matcher matcher = pattern.matcher(result);
            String[] space = new String[2];
            matcher.find();
            space[0] = matcher.group();
            if(matcher.find())
                space[1] = matcher.group();
            else
                space[1] = space[0];
            iList.add(space);
            if(mode == 1 && result.indexOf(" error") == -1){
                list.add(arr);
                continue;
            }
            System.out.println(arr[1] + "please input the English word:");
            String word = br.readLine();
            String temp = word;
            if(!word.equals(arr[0])){
                System.err.println("Error!" + arr[0]);
                temp = "error";
                c++;
            }
            if(arr.length >= 3){
                arr[2] = temp;
                list.add(arr);
            }else{
                String[] newArr = new String[3];
                newArr[0] = arr[0];
                newArr[1] = arr[1];
                newArr[2] = temp;
                list.add(newArr);
            }

        }
        System.out.println("Finish,wrong word is " + c);
        br.close();
        fBuffer.close();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        OutputStreamWriter ow = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(ow);
        String a = "              ";
        AtomicInteger i = new AtomicInteger();
        list.forEach(arr-> {
            try {
                int j = i.getAndIncrement();
                String[] sp = iList.get(j);
                bw.write(arr[0] + sp[0] + arr[1] + sp[1] + arr[2] + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }
}
