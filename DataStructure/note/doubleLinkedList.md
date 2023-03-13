## 遍历
遍历方法单链表一祥，但是可以双向遍历  
## 添加(默认添加到双向链表的最后)
(1)先找到双向链表的最后这个节点  
(2)temp.next = newHeroNode  
(3)newHeroNode.pre=temp
## 删除
(1)因为是双向链表，因此，我们可以实现自我删除某个节点  
(2)直接找到要删除的这个节点，比如temp  
(3)temp.pre.next = temp.next  
(4)temp.next.pre = temp.pre