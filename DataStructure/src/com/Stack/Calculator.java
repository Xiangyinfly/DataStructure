package com.Stack;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("输入表达式：");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();
        new Calculator().calculate(expression);
    }

    public void calculate(String expression) {
        arrayStack numStack = new arrayStack(10);
        arrayStack opeStack = new arrayStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char element = ' ';

        while (index < expression.length()) {
            element = expression.substring(index,index + 1).charAt(0);
            //是运算符
            if (opeStack.isOperator(element)) {
                //符号栈不为空且element优先级低
                if (! opeStack.isEmpty()
                        && opeStack.priority(element) <= opeStack.priority(opeStack.topValue())) {
                    //数栈取出两个数，符号栈取出一个符号
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    operator = opeStack.pop();
                    //计算结果
                    res = numStack.calculate(num1,num2,operator);
                    //结果压入数栈
                    numStack.push(res);
                    //当前符号element压入数栈
                    opeStack.push(element);
                } else {
                    opeStack.push(element);
                }
            } else {
                numStack.push(element - '0');//🌟,也可以为element - 48
            }

            index ++;
        }

        while (! opeStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = opeStack.pop();
            res = numStack.calculate(num1,num2,operator);
            numStack.push(res);
        }

        System.out.printf("表达式%s的结果为%d",expression,numStack.pop());
    }
}
