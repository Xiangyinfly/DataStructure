package com.Stack;

public class arrayStack {
    private int top = -1;
    private int[] arrayStack;
    private int maxsize;

    public arrayStack(int maxsize) {
        this.maxsize = maxsize;
        arrayStack = new int[maxsize];
    }

    public void push(int value) {
        if (top < maxsize - 1) {
            arrayStack[++ top] = value;
        } else {
            System.out.println("栈已满");
        }
    }

    public int pop() {
        if (top == -1) {
            throw new RuntimeException("栈为空");
        }
        return arrayStack[top --];
    }

    public void show() {
        if (top == -1) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top;i >= 0;i --) {
            System.out.printf("stack[%d] = %d",i,arrayStack[i]);
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    //以下是计算器所用到的方法扩展
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        }
        if (operator == '+' || operator == '-') {
            return 0;
        }
        return -1;
    }

    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' ||value == '/';
    }

    public int calculate(int num1,int num2,int operator) {
        int res = 0;
        switch (operator) {
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num2 - num1;//🌟
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num2 / num1;
                break;
        }
        return res;
    }

    //返回当前栈顶的元素(并非出栈)
    public int topValue() {
        return arrayStack[top];
    }
}
