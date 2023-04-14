package com.Tree.HuffmanTree;

import java.util.*;

public class HuffmanCode {
    //=========================================编码=========================================
    //整合方法：该方法获得压缩后的数据（编码）
    public static byte[] HuffmanCompress(String string) {
        byte[] bytes = string.getBytes();
        return getCompressCode(bytes,StringToHCS(string));
    }


    /**
     * 获得压缩后的数据
     * @param bytes 原始数据的字节数组
     * @param huffmanCode 哈夫曼编码表
     * @return 压缩后数据的字节数组
     */
    private static byte[] getCompressCode(byte[] bytes, Map<Byte,String> huffmanCode) {
        //获得哈夫曼编码，存储在stringBuilder中
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b :bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }

        //以8位字节存储
        int length;
        if (stringBuilder.length() % 8 == 0) {
            length = stringBuilder.length() / 8;
        } else {
            length = stringBuilder.length() / 8 + 1;
        }

        int index = 0;
        String string;
        byte[] huffmanCodeBytes = new byte[length];
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                string = stringBuilder.substring(i);//不够8位直接取到最后
            } else {
                string = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index ++] = (byte) Integer.parseInt(string,2);
        }

        return huffmanCodeBytes;
    }

    //整合方法：传入string，返回string的哈夫曼编码表
    public static Map<Byte,String> StringToHCS(String s) {
        byte[] bytes = s.getBytes();
        return getCode(createHT(getNodes(bytes)));
    }

    static Map<Byte,String> huffmanCode = new HashMap<Byte,String>();
    static StringBuilder stringBuilder = new StringBuilder();

    //重载一下
    private static Map<Byte,String> getCode(Node root) {
        if (root == null) {
            return null;
        }
        getCode(root,"",stringBuilder);
        return huffmanCode;
    }

    /**
     * 得到node的所有叶子结点的哈夫曼编码，放入huffmanCode
     * @param node 传入的节点
     * @param code 路径：左1右2
     * @param stringBuilder 用于拼接
     */
    private static void getCode(Node node,String code,StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);//这个地方为什么还要再创建stringBuilder1？可否直接在方法内只创建一个stringBuilder？类属性中的stringBuilder有什么用？
        stringBuilder1.append(code);
        if (node != null) {
            if (node.data == null) {//说明是非叶子结点
                getCode(node.left,"0",stringBuilder1);
                getCode(node.right,"1",stringBuilder1);
            } else {
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //此方法用于创建哈夫曼树
    private static Node createHT(List<Node> nodes) {
        while (nodes.size() > 1) {//List中只有一个数据时，建立完毕
            //排序
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null,left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //返回root
        return nodes.get(0);
    }

    //此方法用于数据以Node形式放入list中
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        //利用map记录字符出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b :bytes) {
            counts.merge(b, 1, Integer::sum);//如何理解merge方法？

//            Integer count = counts.get(b);
//            if (count == null) {//这里的警告看不懂
//                counts.put(b,1);
//            } else {
//                counts.put(b,count + 1);
//            }
        }

        //遍历map内容，转换为node并添加至nodes
        for (Map.Entry<Byte, Integer> entry :counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }
}
