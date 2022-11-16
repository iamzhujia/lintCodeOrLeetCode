package com.bj58;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Single {
    public static void main(String[] args){
        for (int i = 0; i< 10000; ++i){
            final int j = i;
            new Thread(){
                @Override
                public void run() {
                    int d = 1;
                    Data s = Single.getData(d );
                    if(s.getId() > 1){
                        System.err.println("Error");
                    }
                    System.out.println(s);
                }
            }.start();
        }
    }
    private static volatile Map<Integer, Data> m = new HashMap<Integer, Data>();

    public static Data getData(int s){
        Data r = m.get(s);
        if(r == null){
            synchronized (m){
                r = m.get(s);
                if(r == null){
                    Data a = new Data();
                    m.put(s, a);
                    r = a;
                }
            }
        }
        return r;
    }



}
class Data{
    private static AtomicInteger idc = new AtomicInteger(0);
    private Integer id;

    public Data() {
        this.id = idc.incrementAndGet();
    }
    public Data(int k) {
        this.id = k;
    }
    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }
}