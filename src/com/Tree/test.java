package com.Tree;


import com.Tree.BinarySortTree.BinarySortTree;
import com.Tree.BinarySortTree.Node;
import com.Tree.HuffmanTree.HuffmanCode;


public class test {
    public static void main(String[] args) {
//        String s = "i like like like java do you like java 你好";
//        byte[] b = s.getBytes();
//        byte[] decode = HuffmanCode.huffmanDecode(HuffmanCode.HuffmanCompress(b), HuffmanCode.getHCS(b));
//        System.out.println(new String(decode));

//        HuffmanCode.fileCompress("src/a.jpg","src/b.hcd");
//        HuffmanCode.fileDecompress("src/b.hcd","src/c.jpg");

        int[] arr = {7,3};
        BinarySortTree b = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            b.add(new Node(arr[i]));
        }
        b.infixOrder();
        b.delete(7);
        System.out.println();
        b.infixOrder();
    }
}
