package com.Tree.HuffmanTree;

public class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "value=" + value +
//                '}';
//    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大排序
    }

    //=========================仅构建哈夫曼编码所用=========================
    Byte data;

    public Node(Byte data,int value) {
        this.value = value;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", data=" + data +
                '}';
    }
}
