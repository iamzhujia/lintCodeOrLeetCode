package com.bj58;

public class CycleQueue {
    private int[] data;
    private int rear = 0;
    private int front = 0;
    public CycleQueue(int size){
        data = new int[size + 1];
    }

    public boolean offer(int d){
        if((rear+1) % data.length == front){
            return false;
        }
        data[rear] = d;
        rear = (rear+1)%data.length;
        return true;
    }

    public int poll(){
        if(rear == front){
            return -1;
        }
        int d = data[front];
        front = (front+1)%data.length;
        return d;
    }

    public int get(){
        return data[front];
    }

    public static void yanghui(int n){
        CycleQueue cycleQueue = new CycleQueue(n+1);
        cycleQueue.offer(0);
        cycleQueue.offer(1);
        int e=0,s=0;
        for(int i = 0; i < n-1;++i){
            for(int j = 0;j<n-i-1;++j){
                System.out.print("  ");
            }
            cycleQueue.offer(0);
            do{
                s= cycleQueue.poll();
                e = cycleQueue.get();
                if(e!=0){
                    System.out.print(e + (e<10?"  ":" "));
                }else{
                    System.out.println();
                }
                cycleQueue.offer(s+e);
            }while(e!=0);
        }
        cycleQueue.poll();
        for (int i = 0; i < n; i++) {
            e = cycleQueue.poll();
            System.out.print(e+ (e<10?"  ":" "));
        }
    }
    public static  void main(String[] args){
        yanghui(8);
    }
}
