package com.bj58;

class Foo {
    long id;
    public Foo() {
        id = System.currentTimeMillis();
    }
    private volatile boolean firstDid = false;
    private volatile boolean secondDid = false;
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstDid = true;

    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!firstDid){

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondDid = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(!secondDid){

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}