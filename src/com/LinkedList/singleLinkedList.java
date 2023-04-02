package com.LinkedList;

import java.util.Stack;

public class singleLinkedList {
    //初始化头节点，不放置任何数据
    public Node_single head = new Node_single(0, "");

    //将数据加到链表的最后
    public void add(Node_single nodeSingle) {
        //head节点不能动，因此需要temp辅助遍历
        Node_single point = head;
        while (true) {
            if (point.next == null) {
                break;
            }
            point = point.next;
        }
        point.next = nodeSingle;
    }

    //按顺序将节点插入指定位置
    public void addByOrder(Node_single nodeSingle) {
        Node_single point = head;
        while (true) {
            if (point.next == null) {
                break;
            }
            if (point.next.num > nodeSingle.num) {
                break;
            } else if (point.num == nodeSingle.num) {
                System.out.println("节点已被插入过...");
                return;
            }
            point = point.next;
        }

        nodeSingle.next = point.next;
        point.next = nodeSingle;
    }

    public void update(Node_single newNodeSingle) {
        Node_single point = head;
        while (true) {
            if (point.next == null) {
                System.out.println("节点不存在...");
                break;
            }
            if (point.num == newNodeSingle.num) {
                point.attribute = newNodeSingle.attribute;
                break;
            }
            point = point.next;
        }
    }

    public void delete(Node_single nodeSingle) {
        Node_single point = head;
        while (true) {
            if (point.next == null) {
                System.out.println("节点不存在...");
                break;
            }
            if (point.next.num == nodeSingle.num) {
                point.next = point.next.next;
                break;
            }
            point = point.next;
        }
    }

    //遍历链表
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        Node_single point = head.next;
        while (point != null) {
            System.out.println(point);
            point = point.next;
        }
    }

    //========================一些test========================

    //获取单链表有效节点的个数
    public static int getLength(Node_single head) {
        int length = 0;
        Node_single point = head.next;
        while (point != null) {
            length ++;
            point = point.next;
        }
        return length;
    }

    //查找单链表的倒数第k个节点
    public static Node_single findLastIndexNode(Node_single head, int index) {
        int length = getLength(head);
        Node_single point = head.next;
        if (index <= 0 || index > length) {
            System.out.println("节点不存在...");
            return null;
        }
        for (int i = 1;i <= length - index;i ++) {
            point = point.next;
        }
        return point;
    }

    //单链表的反转
    public static void reverse(Node_single head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        Node_single point = head.next;
        Node_single temp = null;
        Node_single reverseHead = new Node_single(0,"");

        while (point != null) {
            //🌟此处使用头插法
            temp = point.next;//利用temp保存point的下一个节点
            point.next = reverseHead.next;//将point指向reverseHead的下一个节点
            reverseHead.next = point;//将reverseHead指向point，实现point插入reverseHead和原本reverseHead指向的那个节点中间
            point = temp;//实现point后移至下一个节点
        }
        head.next = reverseHead.next;//将反转后链表的新头节点换为先前的头节点
    }

    //利用栈实现单链表的逆序打印
    public static void reversePrint(Node_single head) {
        if (head.next == null) {
            return;
        }
        //创建栈并将节点压入
        Stack<Node_single> nodeSingles = new Stack<>();
        Node_single point = head.next;
        while (point.next != null) {
            nodeSingles.push(point);
            point = point.next;
        }
        //逆序打印
        while (nodeSingles.size() > 0) {
            System.out.println(nodeSingles.pop());
        }
    }

    //合并两个有序单链表，使其合并之后有序
    //此方法会将两个链表都改变为合并链表（因为没有将head_.next置空，即未断开head_与后面节点的连接）
    public static void combineList(Node_single head, Node_single head_) {
        if (head.next != null && head_.next != null) {
            Node_single point_ = head_.next;
            Node_single point = head;
            while (point_ != null) {//遍历head_所在的链表，直到最后一个节点
                Node_single temp_ = point_.next;//保存point_.next到temp
                while (point.next != null) {//遍历head所在的链表，因为判定插入位置要取两个节点之间的区间，所以*至多*遍历到倒数第二个节点。
                    if (point.next.num >= point_.num) {
                        //在point和point.next中间插入point_
                        point_.next = point.next;
                        point.next = point_;
                        break;
                    }
                    point = point.next;//point后移
                }
                //内层while结束后，如果head_所在的链表还存在节点，则point在最后一个节点，会进入以下if
                if (point.next == null) {
                    point.next = point_;//head_所在的链表存在的这些节点，序号比head所在的链表所有节点的序号都大，因此连接在head_所在的链表的最后
                    break;
                }
                point_ = temp_;//point_后移
            }
        }
    }
}



class Node_single {
    public int num;
    public String attribute;
    public Node_single next;

    public Node_single(int num, String attribute) {
        this.num = num;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + num +
                ", name='" + attribute + '\'' +
                '}';
    }
}
