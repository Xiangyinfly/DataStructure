package com.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式实现的的计算器
 */

public class RPNCalculator {
    public static void main(String[] args) {

    }

    //利用后缀表达式进行运算
    public int calculate(String expression) {
        List<String> expressionList = getExpressionList(expression);
        Stack<String> stack = new Stack<>();
        for (String element : expressionList) {
            if (element.matches("\\d+")) {//正则表达式
                stack.push(element);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (element.equals("+")) {
                    res = num1 + num2;
                } else if (element.equals("-")) {
                    res = num2 - num1;
                } else if (element.equals("*")) {
                    res = num1 * num2;
                } else if (element.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转化为后缀表达式
    public static List<String> IN_to_RPN(String IN) {
        //先初始化stack,list，s为运算符栈，l为储存中间结果的列表
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        List<String> INList = getExpressionList(IN);

        for (String element : INList) {
            if (element.matches("\\d+")) {
                //如果是运算符
                list.add(element);
            } else if (element.equals("(")) {
                //如果是左括号，直接压入stack
                stack.push(element);
            } else if (element.equals(")")) {
                //如果是右括号，将栈中的元素添加进list，直到遇见左括号
                while (! stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop();//将左括号出栈，消除括号
            } else {
                while (stack.size() != 0 && priority(stack.peek()) >= priority(element)) {
                    //如果栈不为空且运算符优先级不高于栈顶运算符优先级，将栈顶运算符添加进list，直到while不成立
                    list.add(stack.pop());
                }
                //栈为空或运算符优先级高于栈顶运算符优先级，直接压入栈
                stack.push(element);
            }
        }

        while (stack.size() != 0) {
            list.add(stack.pop());
        }

        return list;
    }

    public static List<String> getExpressionList(String expression) {
        //默认表达式各字符以空格分割
        String[] elements = expression.split(" ");
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static int priority(String operator) {
        if (operator == "*" || operator == "/") {
            return 1;
        }
        if (operator == "+" || operator == "-") {
            return 0;
        }
        return -1;
    }
}
