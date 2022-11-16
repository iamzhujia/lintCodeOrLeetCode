package com.bj58;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringLoop {
    public static void main(String[] args) throws Exception {
      String[] arg =   {"awef","eawf","zdaeff","awefzewaf","awefzewaf"};

        //String
        List<String> li = new ArrayList<>();
        Arrays.stream(arg).forEach(s->li.add(s));
       String  a = new StringLoop().splitLoopedString(li);
        System.out.println(a);
        PrintWriter pw = new PrintWriter("D:\\sql1887755.sql");
//        String[] sts = {"0820","0821","0822","0823","0824"};
//        for(String str : sts){
//            Scanner scanner = new Scanner(new File("C:\\Users\\Administrator\\Downloads\\"+str+".txt"));//new Scanner(new File("D:\\work\\2022\\wwww.csv"));
//            scanner.nextLine();
//            while(scanner.hasNext()){
//                String line = scanner.nextLine();
//                if(line.indexOf("202201213635513172773609716")>-1){
//                    System.out.println(line);
//                }
//                String[] arr = line.split("\t");
//                String sql = "insert into recon_warn_cus_fund(batch_id,bill_no,bill_date,term_no,update_time,create_time,result,remark,capital) values('"
//                        + arr[1] + "','" + arr[2] + "','" + arr[3] + "',"+arr[4] +",'"+arr[5]+"','"+arr[6]+"'," + arr[7] +",'','"+arr[9]+"');";
//               //pw.println(sql);
//            }
//            scanner.close();
       // }
        Scanner scanner = new Scanner(new File("D:\\work\\2022\\777.sql"));//new Scanner(new File("D:\\work\\2022\\wwww.csv"));

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if(!line.startsWith("INSERT")){
                String sql = "INSERT INTO `recon_warn_cus_fund` (`id`, `batch_id`, `bill_no`, `bill_date`, `term_no`, `update_time`, `create_time`, `result`, `remark`, `capital`) VALUES";
                sql += line;
                if(line.endsWith(",")){
                    sql = sql.substring(0, sql.length() - 1);
                    sql += ";";
                }
                pw.println(sql);
            }
//            String sql = "insert into recon_warn_cus_fund(batch_id,bill_no,bill_date,term_no,update_time,create_time,result,remark,capital) values('"
//                    + arr[1] + "','" + arr[2] + "','" + arr[3] + "',"+arr[4] +",'"+arr[5]+"','"+arr[6]+"'," + arr[7] +",'','"+arr[9]+"');";
            //pw.println(sql);
        }
        scanner.close();
        pw.flush();
        pw.close();
    }

    private static void kkk(Scanner scanner, Set<String> set) {
        StringBuilder sb = new StringBuilder();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            sb.append(s);
        }
        scanner.close();
        for(String s :set){
            boolean a = sb.toString().contains(s);
            if(!a){
                System.out.println(s + " wwwww");
            }
        }
        System.out.println("finish");
    }

    private static void fff(Scanner scanner, Set<String> set) {
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            //System.out.println(s);
            if(s.startsWith("stop")){
                break;
            }
            int index = s.indexOf("yearMonth=");

            set.add(s);
            //System.out.println("select '" + arr[0]+"' as bill_no," + arr[1] +" as term_no union all ");
        }
        scanner.close();
    }

    public String splitLoopedString(List<String> strs) {
        int size = strs.size();
        int total = 0;
        for(String s : strs){
            total += s.length();
        }
        char max = strs.get(0).charAt(0);
        for(String str : strs){
            char[] cs = str.toCharArray();
            for(char c : cs){
                if(c > max){
                    max = c;
                }
            }
        }
        Map<String,Object[]> tops = new HashMap<>();
        int i = 0, j = 0;
        List<Character> maxList = null;
        for(String str : strs){
            char[] cs = str.toCharArray();
            j = 0;
            for(char c : cs){
                if(c == max){
                    if(maxList == null){
                        Object[][] obs = grow(strs, cs, i,j);
                        for(Object[] ob : obs){
                            tops.put(i + "," + j + ","+ob[6],ob);
                        }
                        maxList = (List<Character>)obs[0][2];
                    }else{
                        Object[][] obs = grow(strs, cs, i,j);
                        List<Character> temp = (List<Character>)obs[0][2];
                        int k = notLittle(temp, maxList, 1);
                        if(k==0){
                            for(Object[] ob : obs){
                                tops.put(i + "," + j + ","+ob[6],ob);
                            }
                            maxList = (List<Character>)obs[0][2];
                        }else if(k>0){
                            tops.clear();
                            for(Object[] ob : obs){
                                tops.put(i + "," + j + ","+ob[6],ob);
                            }
                            maxList = (List<Character>)obs[0][2];
                        }
                    }

                }
                j++;
            }
            i++;
        }
        int step = 1;
        int start = 2;
        while (true){
            Object[] obs = tops.entrySet().iterator().next().getValue();
            List<Character> list = (List<Character>)obs[2];
            if(tops.size() == 1){
                if(list.size() < total){
                    return growToEnd(total, list, obs, size, strs);
                }else{
                    String result = "";
                    for(Character c : list){
                        result += c;
                    }
                    return result;
                }
            }
            if(list.size() == total){
                String result = "";
                for(Character c : list){
                    result += c;
                }
                return result;
            }
            List<String> keys = new ArrayList<>();
            for(String  key : tops.keySet()){
                keys.add(key);
            }
            List<String> keys2 = new ArrayList<>();
            for(String  key : keys){
                Object[] obs2 = tops.get(key);
                growStep(step, obs2, size, strs);
                List<Character> list2 = (List<Character>)obs2[2];
                int k = notLittle(list2, maxList, start);
                if(k==0){
                    maxList = (List<Character>)obs2[2];
                    keys2.add(key);
                }else if(k>0){
                    for(String k3 : keys2){
                        tops.remove(k3);
                    }
                    keys2.clear();
                    tops.put(key, obs2);
                    maxList = (List<Character>)obs2[2];
                    keys2.add(key);
                }else{
                    tops.remove(key);
                }
            }
            start += step;
        }
    }

    private void growStep(int step, Object[] obs, int size, List<String> strs) {
        Integer curI = (Integer) obs[3];
        List<Character> list  = (List<Character>) obs[2];
        Integer curJ = (Integer) obs[4];
        Integer startI = (Integer)obs[0];
        Integer startJ = (Integer)obs[1];
        Boolean startReserve = (Boolean) obs[6];
        char[] firstChars = (char[])obs[7];
        char[] curChars = (char[])obs[8];
        if(startReserve){
            startJ = firstChars.length - 1 - startJ;
        }
        int js = 0;
        for(++ curJ ;curJ < curChars.length; ++ curJ){
            list.add(curChars[curJ]);
            js++;

            if(js >= step){
                obs[4] = curJ;
                return;
            }
        }

        int nextI = curI + 1;
        if(nextI >= size){
            nextI = 0;
        }
        while(nextI!=startI){
            String s = strs.get(nextI);
            Object[] r = reserveOrSelf(s);
            s = r[0].toString();
            boolean fz = (Boolean) r[1];
            char[] cs = s.toCharArray();
            for(int i = 0; i < cs.length; ++i){
                list.add(cs[i]);
                js++;
                if(js>=step){
                    obs[3] = nextI;
                    obs[4] = i;
                    obs[8] = cs;
                    obs[5] = fz;
                    return;
                }
            }
            nextI ++;
            if(nextI >= size){
                nextI = 0;
            }
        }
        for(int i = 0; i < startJ; ++i){
            list.add(firstChars[i]);
            js++;
            if(js>=step){
                obs[3] = startI;
                obs[4] = i;
                obs[8] = firstChars;
                obs[5] = startReserve;
                return;
            }
        }
        obs[3] = startI;
        obs[4] = startJ - 1;
        obs[8] = firstChars;
        obs[5] = startReserve;
    }

    private String growToEnd(int total, List<Character> list, Object[] obs, int size, List<String> strs) {
        Integer curI = (Integer) obs[3];
        Integer curJ = (Integer) obs[4];
        Integer startI = (Integer)obs[0];
        Integer startJ = (Integer)obs[1];
        Boolean startReserve = (Boolean) obs[6];
        char[] firstChars = (char[])obs[7];
        char[] curChars = (char[])obs[8];
        if(startReserve){
            startJ = firstChars.length - 1 - startJ;
        }
        String result = "";
        for(Character c : list){
            result  += c;
        }
        for(++ curJ ;curJ < curChars.length; ++ curJ){
            result += curChars[curJ];
        }
        int nextI = curI + 1;
        if(nextI >= size){
            nextI = 0;
        }
        while(nextI!=startI){
            String s = strs.get(nextI);
            result += reserveOrSelf(s)[0];
            nextI ++;
            if(nextI >= size){
                nextI = 0;
            }
        }
        for(int i = 0; i < startJ; ++i){
            result += firstChars[i];
        }
        return result;
    }

    private int notLittle(List<Character> temp, List<Character> maxList, int start) {
        int size = Math.min(temp.size(), maxList.size());
        for(int i = start ; i < size; ++i){
            if(temp.get(i) < maxList.get(i)){
                return -1;
            }
            if(temp.get(i) > maxList.get(i)){
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param strs
     * @param cs
     * @param i
     * @param j
     * @return  初始ij，list，当前最后一个字符坐标k,f,
     *              当前是否翻转，初始是否翻转,初始数组，当前数组
     */
    private Object[][] grow(List<String> strs, char[] cs, int i, int j) {

        Object[][] obs = null;
        int size = strs.size();
        int index = 0;
        if(i != size - 1){
            index = i + 1;
        }
       // boolean[] booleans = new boolean[size];
        if(j == 0){
            if(cs.length == 1){
                return getOnlyOne(size, cs, strs, i, j, index);
            }else{
                if(strs.size() > 1){
                    char a = cs[1];
                    String s = strs.get(index);
                    Object[] r = reserveOrSelf(s);
                    s = r[0].toString();
                    boolean fz = (Boolean) r[1];
                    char[] cars = s.toCharArray();
                    char b = cars[0];
                    List<Character> list = new ArrayList<>();
                    if(a<b){
                        list.add(cs[0]);
                        list.add(b);
                        obs = new Object[1][];
                        obs[0] = new Object[]{i,j,list,index,0,fz, true,reverse(cs),cars};
                        return obs;
                    }else if(b<a){
                        list.add(cs[0]);
                        list.add(a);
                        obs = new Object[1][];
                        obs[0] = new Object[]{i,j,list,i,1,false, false,cs,cs};
                        return obs;
                    }else{
                        list.add(cs[0]);
                        list.add(a);
                        List<Character> l = new ArrayList<>();
                        l.add(cs[0]);
                        l.add(a);
                        obs = new Object[2][];
                        obs[0] = new Object[]{i,j,list,i,1,false, false,cs,cs};
                        obs[1] = new Object[]{i,j,l,index,0,fz, true,reverse(cs),cars};
                        return obs;
                    }
                }else{
                    List<Character> list = new ArrayList<>();
                    list.add(cs[0]);
                    list.add(cs[1]);
                    obs = new Object[1][];
                    obs[0] = new Object[]{i,j,list,i,1,false, false,cs,cs};
                    return obs;
                }
            }

        }else if(j == cs.length - 1){
            if(cs.length == 1){//其实到不了这里
                return getOnlyOne(size, cs, strs, i, j, index);
            }else{
                if(strs.size() > 1){
                    char a = cs[j - 1];
                    String s = strs.get(index);
                    Object[] r = reserveOrSelf(s);
                    s = r[0].toString();
                    boolean fz = (Boolean) r[1];
                    char[] cars = s.toCharArray();
                    char b = cars[0];
                    List<Character> list = new ArrayList<>();
                    if(a<b){
                        list.add(cs[j]);
                        list.add(b);
                        obs = new Object[1][];
                        obs[0] = new Object[]{i,j,list,index,0,fz, false,cs,cars};
                        return obs;
                    }else if(b<a){
                        list.add(cs[j]);
                        list.add(a);
                        obs = new Object[1][];
                        obs[0] = new Object[]{i,j,list,i,1,true, true,reverse(cs),reverse(cs)};
                        return obs;
                    }else{
                        list.add(cs[j]);
                        list.add(a);
                        List<Character> l = new ArrayList<>();
                        l.add(cs[j]);
                        l.add(a);
                        obs = new Object[2][];
                        obs[0] = new Object[]{i,j,list,i,1,true, true,reverse(cs),reverse(cs)};
                        obs[1] = new Object[]{i,j,l,index,0,fz, false,cs,cars};
                        return obs;
                    }
                }else{
                    List<Character> list = new ArrayList<>();
                    list.add(cs[j]);
                    list.add(cs[j - 1]);
                    obs = new Object[1][];
                    obs[0] = new Object[]{i,j,list,i,1,true, true,reverse(cs),reverse(cs)};
                    return obs;
                }
            }

        }else{
            char a = cs[j - 1];
            char b = cs[j + 1];
            List<Character> list = new ArrayList<>();
            if(a > b){

                list.add(cs[j]);
                list.add(cs[j - 1]);
                obs = new Object[1][];
                obs[0] = new Object[]{i,j,list,i,cs.length - 1 - (j -1),true, true,reverse(cs),reverse(cs)};
                return obs;
            }else if(b > a){
                list.add(cs[j]);
                list.add(cs[j +1]);
                obs = new Object[1][];
                obs[0] = new Object[]{i,j,list,i,j+1,false, false,cs,cs};
                return obs;
            }else{
                List<Character> li = new ArrayList<>();
                list.add(cs[j]);
                list.add(cs[j +1]);
                li.add(cs[j]);
                li.add(cs[j-1]);
                obs = new Object[2][];
                obs[0] = new Object[]{i,j,list,i,j+1,false, false,cs,cs};
                obs[1] = new Object[]{i,j,li,i,cs.length - 1 - (j -1),true, true,reverse(cs),reverse(cs)};
                return obs;
            }
        }
    }

    private Object[][] getOnlyOne(int size, char[] cs, List<String> strs, int i, int j, int index) {
        Object[][] obs = null;
        List<Character> list = new ArrayList<>();
        list.add(cs[j]);
        if(size > 1){
            String s = strs.get(index);
            Object[] r = reserveOrSelf(s);
            s = r[0].toString();
            boolean fz = (Boolean) r[1];
            char[] ars = s.toCharArray();
            list.add(ars[0]);
            obs = new Object[1][];
            obs[0] = new Object[]{i,j,list,index,0,fz, false,cs,ars};//初始ij，list，当前最后一个字符坐标index,0,
            // 当前是否翻转，初始是否翻转,初始数组，当前数组
            return obs;
        }else{
            obs = new Object[1][];
            obs[0] = new Object[]{i,j,list,i,j,false, false,cs,cs};
            return obs;
        }
    }

    private char[] reverse(char[] cs) {
        int len = cs.length;
        if(len == 1){
            return cs;
        }
        char[] r = new char[len];
        for(int i = len - 1; i>-1;--i){

            r[len- 1 -i] = cs[i];
        }
        return r;
    }

    private Object[] reserveOrSelf(String s) {
        char[] cs = s.toCharArray();
        int len = cs.length;
        if(len == 1){
            return new Object[]{s, false};
        }
        String str = "";
        boolean fz = false;
        for(int i = len - 1; i>-1;--i){
            if(cs[i] < cs[len - 1 - i] && !fz){
                return new Object[]{s, false};
            }else if(cs[i] > cs[len - 1 - i]){
                fz = true;
            }else{
                //尚待决策是否翻转，其它分支已经确认结果了
            }
            str+=cs[i];
        }
        return  new Object[]{str, true};
    }



}
