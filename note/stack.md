## 利用栈完成计算器

1.通过一个index值（索引），来遍历我们的表达式

2.如果我们发现是一个数字,就直接入数栈

3.如果发现扫描到是一个符号，就分如下情况：
如果发现兰前的符号栈为空，就直接入栈
如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈

4.当表达式扫描完毕，就顺序的从数栈和符号找中pop出相应的数和符号，并运行

5.最后在数栈只有一个数字，就是表达式的结果

## 中缀表达式转后缀表达式

1.初始化两个栈：运算符栈s1和储存中间结果的栈s2

2.从左至右扫描中缀表达式

3.遇到操作数时，将其压s2

4.遇到运算符时，比较其与s1栈顶运算符的优先级:
4.1如果s1为空，或栈顶运算符为左括号"(",则直接符此运算符入栈;
4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1;
4.3否则，(优先级小于等于栈顶运算符)将s1栈项的运算符弹出并压入到s2中，重复步骤4，与s1中新的栈顶运算符相比较;

5.遇到括号时：
5.1如果是左括号"("，则直接压入s1;
5.2如果是右括号")"，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃

6.重复步骤2至5，直到表达式的最右边

7.将s1中剩余的运算符依次弹出并压入s2

8.依次弹出s2中的元素并输出，结果的逆序即为中级表达式对应的后缀表达式
