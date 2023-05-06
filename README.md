# TextTest

一个刷题 + 自测的数据填充工具。
适合复杂数据结构的重复性导入。(单次可能性价比不高)

---
### 适用场合

大量的、重复的、多种数据结构的某个方法单侧。
为了面试刷以核心算法为题的时候，将屏幕上的参数导入到核心代码中


是否遇见过这种问题：
```
int sum(List<Integer> list1, List<Inter> list2) {
    int sum = 0;
    for (int i : list1) {
        sum += i;
    }
    for (int i : list2) {
        sum += i
    }
    return sum
}
```

这个代码看起来没有问题：但是有隐患：会爆int。

正常的测试就得写个方法，写入数据，然后主动启动一个类，最后往列表里面写入值：
```
List<Interger> list1 = new ArrayList<>();
for (int i = 0; i < 100000; i++) {
    list.add(i);
}

Fun fun = new fun();
fun.sum(list1, list1);
```
上哪里来那么多随机数据？

这只是单一类型，如果是这种呢？
```
int sum(int a, long b, List<String> c, DateTime d, ListNode e) {}
```

### 需要做什么？

假如需要单侧某个方法：
```java
public class Solution {
    public int run(String a, List<Integer> b, int[] c, ListNode d, TreeNode e, String[] f) {
        return (int) (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }
}
```

启动方式很简单:执行TextTestStarter，将类的class传进去。
然后执行@Test 或者直接psvm新建一个main方法

```
public static void main(String[]args){
    new TextTestStarter(Solution.class);
}
```
然后在需要启动的类中加入注解@TextTest(value = "单侧文件的名字.ttest")
```java
public class Solution {
    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, int[] c, ListNode d, TreeNode e, String[] f) {
        return (int) (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }
}
```
在1.ttest填入数据
``` 
"babad"
[11]
[12]
[5, 2, 3, 4]
[1, 2]
abc
```

得到结果：
```
[INFO ] 2023-05-06 18:12:06.162 方法run 的1.ttest 配置文件已经找到, 准备配置
[DEBUG] 2023-05-06 18:12:06.198 采集完成！一共 1 个任务
[INFO ] 2023-05-06 18:12:06.293 开始任务, 配置文件1.ttest, 方法为examples.Solution@53b7f657 的 run
[INFO ] 2023-05-06 18:12:06.299 成功注入, 类型为 class java.lang.String ,值为 babad
[INFO ] 2023-05-06 18:12:06.316 成功注入, 类型为 class java.util.ArrayList ,值为 [11]
[INFO ] 2023-05-06 18:12:06.318 成功注入, 类型为 int[] ,值为 [12]
[INFO ] 2023-05-06 18:12:06.320 成功注入, 类型为 class lib.javaCollections.ListNode ,值为 [5, 2, 3, 4]
[INFO ] 2023-05-06 18:12:06.321 成功注入, 类型为 class lib.javaCollections.TreeNode ,值为 [1, 2]
[INFO ] 2023-05-06 18:12:06.326 成功注入, 类型为 class java.lang.String[] ,值为 [abc]
[INFO ] 2023-05-06 18:12:06.327 获取结果, 类型为 class java.lang.Integer ,值为 126
```

当然，在src/main/java/examples里面有个例子
git clone了以后可以尝试test里面的run方法，我相信自然会理解这个项目干了什么

### 细节
* 项目是基于JDK11的，JDK8如果想用可能会稍微改一改方法。
* 用日志作为自测工具，简单修改日志实现即可实现测试结果的持久化
* 底层是用的JackJson + Java原生反射，还有一些标准的工具类（commons实现），无其他引入
所以如果这个可以通过JackJson进行序列化，直接将类解析后的字符串放入列表即可。
JackJson无法解析的我也无法解析
* 为了刷题的人，自己实现了TreeNode和NListNode方法，可以直接复制数组即可序列化为对应数据结构
至于具体实现可以看代码
* 对数组类String[] 实现了简易导入，如果有需要可以注册导入规则到WorkerParser。实现自定义解析


### todo
* 代码内自动生成范围内的随机数。或者特定格式的数据、人名、时间、工具，可以手写parser到WorkerParser实现
* 之前准备用注解，但是弃用了，继续叠加功能也可以

#### Thanks
现在还没有人……
无论如何，没人的时候先谢谢自己吧 ^ ^

