package com.Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    /**
     * 创建哈夫曼树
     * @param array 数据数组
     * @return 哈夫曼树根节点
     */
    public static Node createHT(int[] array) {
        //节点放入List
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value :array) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {//List中只有一个数据时，建立完毕
            //排序
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);//集合里先删除0在删除1，否则会越界？
            nodes.remove(right);
            nodes.add(parent);
        }
        //返回root
        return nodes.get(0);
    }
}
