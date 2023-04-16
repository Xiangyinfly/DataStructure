package com.Tree.HuffmanTree;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    //文件压缩以及解压
    public static void fileCompress(String srcFile,String dstFile) {
        try {
            //直接创建很大的byte数组？待改进
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dstFile));
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            objectOutputStream.writeObject(HuffmanCompress(data));
            objectOutputStream.writeObject(getHCS(data));

            fileInputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileDecompress(String srcFile,String dstFile) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(srcFile));
            FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
            byte[] data = (byte[]) objectInputStream.readObject();
            Map<Byte, String> HCS = (Map) objectInputStream.readObject();
            fileOutputStream.write(huffmanDecode(data,HCS));

            objectInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //=========================================编码=========================================

    //整合后的方法
    /**
     * 利用哈夫曼编码压缩
     * @param bytes 原始数据得到的字节数组
     * @return 编码后的字节数组
     */
    public static byte[] HuffmanCompress(byte[] bytes) {
        return getCompressCode(bytes, getHCS(bytes));
    }

    /**
     * 返回传入byte数组的哈夫曼编码表
     * @param bytes 原始数据的字节数组
     * @return 哈夫曼编码表
     */
    public static Map<Byte,String> getHCS(byte[] bytes) {
        return getCode(createHT(getNodes(bytes)));
    }

    //=========================================

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

        //以字节数组存储

        //+2是因为：
        //1.huffmanCodeBytes[0]存储最后一个字节数据的有效长度
        //2.huffmanCodeBytes最后一个字节存储编码后的01字符串末尾不足8位的部分。如果01数据长度能被8整除，则最后一个字节为空
        int length = stringBuilder.length() / 8 + 2;

        int index = 1;
        String string;
        byte[] huffmanCodeBytes = new byte[length];
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                string = stringBuilder.substring(i);//不够8位直接取到最后
            } else {
                string = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index ++] = (byte) Integer.parseInt(string,2);//以二进制进行解析字符串
        }

        huffmanCodeBytes[0] = (byte)(stringBuilder.length() % 8);//获得最后一个字节的有效长度

        return huffmanCodeBytes;
    }

    static Map<Byte,String> huffmanCodeSet = new HashMap<Byte,String>();
    static StringBuilder stringBuilder = new StringBuilder();

    //重载一下
    private static Map<Byte,String> getCode(Node root) {
        if (root == null) {
            return null;
        }
        getCode(root,"",stringBuilder);
        return huffmanCodeSet;
    }

    /**
     * 得到node的所有叶子结点的哈夫曼编码，放入huffmanCodeSet
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
                huffmanCodeSet.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * 创建哈夫曼树
     * @param nodes 原始数据转化而来的节点的列表
     * @return 哈夫曼树的根节点
     */
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

    /**
     * 原始数据转化为Node形式放入list中
     * @param bytes 原始数据的的byte数组
     * @return 原始数据转化而来的节点的列表
     */
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

    //=========================================解码=========================================

    /**
     * 利用哈夫曼编码解压
     * @param huffmanCodeBytes 编码后数据
     * @param huffmanCodeSet 编码表
     * @return 解码后数据的byte数组
     */
    public static byte[] huffmanDecode(byte[] huffmanCodeBytes, Map<Byte,String> huffmanCodeSet) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < huffmanCodeBytes.length - 1; i++) {
            stringBuilder.append(byteToBString(huffmanCodeBytes[i]));
        }
        //拼接最后一个字节，截取huffmanCodeBytes[0]中存储的有效长度
        //如果最后一个字节为空，则huffmanCodeBytes[0] = 0，不会截取任何数据
        stringBuilder.append(byteToBString(huffmanCodeBytes[huffmanCodeBytes.length - 1]).
                substring(8 - huffmanCodeBytes[0]));

        //反转哈夫曼编码表
        Map<String,Byte> reHCS = new HashMap<>();
        for (Map.Entry<Byte,String> byteStringEntry :huffmanCodeSet.entrySet()) {
            reHCS.put(byteStringEntry.getValue(),byteStringEntry.getKey());
        }

//        ArrayList<Byte> dataList = new ArrayList<>();
//        int count;
//        for (int i = 0;i < stringBuilder.length();i = i + count) {
//            count = 1;
//            boolean flag = true;
//            Byte b = null;
//            while (flag) {
//                b = reHCS.get(stringBuilder.substring(i, i + count));
//                if (b == null) {
//                    count ++;
//                } else {
//                    flag = false;
//                }
//            }
//            dataList.add(b);
//        }
        //利用containsKey的改进
        ArrayList<Byte> dataList = new ArrayList<>();
        int count;
        for (int i = 0; i < stringBuilder.length(); i += count) {
            boolean flag = true;
            count = 1;
            while (flag) {
                if (reHCS.containsKey(stringBuilder.substring(i, i + count))) {//substring左闭右开
                    dataList.add(reHCS.get(stringBuilder.substring(i, i + count)));
                    flag = false;
                } else {
                    count ++;
                }
            }
        }

        byte[] data = new byte[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
        return data;
    }

    /**
     * 字节转二进制字符串
     * @param b 字节
     * @return b的二进制字符串
     */
    private static String byteToBString(byte b) {
        int i = b;
        if (i >= 0) {
            i |= 256;
        }
        String s = Integer.toBinaryString(i);//返回的是i的补码

        return s.substring(s.length() - 8);
    }
}
