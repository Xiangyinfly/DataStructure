## 单链表顺序插入节点

1. 首先找到新添加的节点的位置，是通过辅助变量(指针).  point的位置是在要添加位置前一个位置
2. 新的节点.next = point.next
3. point.next = 新的节点

## 实现单链表的逆转

1. 建立新的头节点reverseHead.
2. 将原链表的节点依次脱下，使用头插法逆转排列到新的头节点之后.
   2.1 利用temp保存point的下一个节点.
   2.2 将point指向reverseHead的下一个节点.
   2.3 将reverseHead指向point，实现point插入reverseHead和原本reverseHead指向的那个节点中间.
   2.4 将point后移至下一个节点.
3. 将反转后链表的新头节点换为先前的头节点.

## 逆序打印单链表

方式一：反转单链表，再打印。但是会改变原本链表的形态。
方式二：利用栈先进后出的特点实现。

## 合并两个有序单链表，使其合并之后有序

此方法会将两个链表都改变为合并链表（因为没有将head_.next置空，即未断开head_与后面节点的连接）

> singleLinkedList的`combineList`方法
