package com.bj58;

public class MyThread extends Thread{
    private Foo foo;
    private int i;
    public MyThread(Foo foo, int i){
        this.foo = foo;
        this.i = i;
    }
    static class First implements Runnable{

        @Override
        public void run() {
            System.out.println("first");
        }
    }
    static class Second implements Runnable{
        @Override
        public void run() {
            System.out.println("second");
        }
    }
    static class Third implements Runnable{
        @Override
        public void run() {
            System.out.println("third");
        }
    }
    @Override
    public void run() {
        switch (i){
            case 1:
                try{
                    foo.first(new First());
                }catch (Exception e){

                }

                break;
            case 2:
                try{
                    foo.second(new Second());
                }catch (Exception e){

                }

                break;
            case 3:
                try{
                    foo.third(new Third());
                }catch (Exception e){

                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
}
