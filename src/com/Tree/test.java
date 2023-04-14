package com.Tree;


import com.Tree.HuffmanTree.HuffmanCode;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        System.out.println(HuffmanCode.StringToHCS("i like like like java do you like a java"));
        System.out.println(Arrays.toString(HuffmanCode.HuffmanCompress("i like like like java do you like a java")));
    }
}
