# TextTest

一个刷题 + 自测的数据填充工具。
适合复杂数据结构的重复性导入。(单次可能性价比不高)

---
### 适用场合

通过注解实现大量的、重复的、多种数据结构的某个方法单侧。
为了面试刷以核心算法为题的时候，将屏幕上的参数导入到核心代码中
支持Range、UUID、随机数

```java
package examples;

import lib.interfaces.SourceParam;
import lib.interfaces.TextTest;
import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, char[] c, ListNode d, TreeNode e, String[] f) {
        return (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }

    @SourceParam(value = "[1, 3, 4]")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "[6, 7]")
    public double run02(List<Integer> a, List<Long> b, int[] c, ListNode d, char[] e, String[] f) {
        return (b.get(0) + c[0] + d.val + e[0] + f[0].charAt(0));
    }

    @SourceParam(value = "RandomInt(20, 1, 13)")
    @SourceParam(value = "1, 2")
    @SourceParam(value = "[1, 5, 5]")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "UUID")
    public String run03(int[] a, List<Long> b, int[] c, ListNode d, char[] e, String f) {
        return f + (b.get(0) + c[0] + d.val + e[0]);
    }

    @SourceParam(value = "NowTime(yyyy-MM-dd)")
    @SourceParam(value = "a b c")
    @SourceParam(value = "a b c")
    @SourceParam(value = "abcd")
    public String run04(String str1, String[] b, List<String> c, char[] d) {
        return str1;
    }

    @SourceParam
    public String run05() {
        return "hello world";
    }

    @SourceParam(value = "RandomString(3)")
    @SourceParam(value = "RandomChar(10)")
    public String run06(String name, String id) {
        return name + id;
    }
}
```

```
[DEBUG] 2023-05-08 19:57:12.439 方法run 的1.ttest 配置文件已经找到
[DEBUG] 2023-05-08 19:57:12.488 通过文件采集完成！一共 1 个任务
[DEBUG] 2023-05-08 19:57:12.490 通过注解成功采集到 5 个任务
[INFO ] 2023-05-08 19:57:12.624 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.624   任务: 1.ttest   任务来源: examples.Solution          方法:  run
[INFO ] 2023-05-08 19:57:12.624 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.634 对第 1 个参数注入结束, 注入类型为 class java.lang.String ,值为 notMessage
[INFO ] 2023-05-08 19:57:12.655 对第 2 个参数注入结束, 注入类型为 class java.util.ArrayList ,值为 [11]
[INFO ] 2023-05-08 19:57:12.657 对第 3 个参数注入结束, 注入类型为 char[] ,值为 [a]
[INFO ] 2023-05-08 19:57:12.662 对第 4 个参数注入结束, 注入类型为 class lib.javaCollections.ListNode ,值为 [5, 2, 3, 4]
[INFO ] 2023-05-08 19:57:12.664 对第 5 个参数注入结束, 注入类型为 class lib.javaCollections.TreeNode ,值为 [1, 2]
[DEBUG] 2023-05-08 19:57:12.664 青碧凝霜提示：解析字符串尽量要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示
[INFO ] 2023-05-08 19:57:12.672 对第 6 个参数注入结束, 注入类型为 class java.lang.String[] ,值为 [abc, dfs]
[INFO ] 2023-05-08 19:57:12.672 获取结果, 类型为 class java.lang.Integer ,值为 211
[INFO ] 2023-05-08 19:57:12.672 运算时间小于1毫秒
[INFO ] 2023-05-08 19:57:12.673 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.673   任务: 注解SourceFrom   任务来源: examples.Solution          方法:  run02
[INFO ] 2023-05-08 19:57:12.673 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.673 对第 1 个参数注入结束, 注入类型为 class java.util.ArrayList ,值为 [1, 3, 4]
[INFO ] 2023-05-08 19:57:12.686 对第 2 个参数注入结束, 注入类型为 class java.util.ArrayList ,值为 [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99]
[INFO ] 2023-05-08 19:57:12.690 对第 3 个参数注入结束, 注入类型为 int[] ,值为 [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99]
[INFO ] 2023-05-08 19:57:12.691 对第 4 个参数注入结束, 注入类型为 class lib.javaCollections.ListNode ,值为 [1]
[INFO ] 2023-05-08 19:57:12.691 对第 5 个参数注入结束, 注入类型为 char[] ,值为 [b]
[INFO ] 2023-05-08 19:57:12.691 对第 6 个参数注入结束, 注入类型为 class java.lang.String[] ,值为 [6, 7]
[INFO ] 2023-05-08 19:57:12.691 获取结果, 类型为 class java.lang.Double ,值为 155.0
[INFO ] 2023-05-08 19:57:12.691 运算时间小于1毫秒
[INFO ] 2023-05-08 19:57:12.691 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.691   任务: 注解SourceFrom   任务来源: examples.Solution          方法:  run03
[INFO ] 2023-05-08 19:57:12.691 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:12.692 对第 1 个参数注入结束, 注入类型为 int[] ,值为 [7, 2, 5, 2, 8, 12, 11, 1, 1, 2, 5, 7, 1, 7, 10, 6, 8, 1, 5, 11]
[DEBUG] 2023-05-08 19:57:12.692 青碧凝霜提示：解析字符串尽量要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示
[INFO ] 2023-05-08 19:57:12.692 对第 2 个参数注入结束, 注入类型为 class java.util.ArrayList ,值为 [1, null, 2]
[INFO ] 2023-05-08 19:57:12.692 对第 3 个参数注入结束, 注入类型为 int[] ,值为 [1, 5, 5]
[INFO ] 2023-05-08 19:57:12.692 对第 4 个参数注入结束, 注入类型为 class lib.javaCollections.ListNode ,值为 [1]
[INFO ] 2023-05-08 19:57:12.693 对第 5 个参数注入结束, 注入类型为 char[] ,值为 [b]
[INFO ] 2023-05-08 19:57:13.089 对第 6 个参数注入结束, 注入类型为 class java.lang.String ,值为 14a85fab-3526-4046-9eb5-4bd7dcbc99b9
[INFO ] 2023-05-08 19:57:13.094 获取结果, 类型为 class java.lang.String ,值为 14a85fab-3526-4046-9eb5-4bd7dcbc99b9101
[INFO ] 2023-05-08 19:57:13.094 运算时间小于1毫秒
[INFO ] 2023-05-08 19:57:13.094 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.094   任务: 注解SourceFrom   任务来源: examples.Solution          方法:  run04
[INFO ] 2023-05-08 19:57:13.094 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.157 对第 1 个参数注入结束, 注入类型为 class java.lang.String ,值为 2023-05-08
[DEBUG] 2023-05-08 19:57:13.158 青碧凝霜提示：解析字符串尽量要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示
[INFO ] 2023-05-08 19:57:13.158 对第 2 个参数注入结束, 注入类型为 class java.lang.String[] ,值为 [a, b, c]
[DEBUG] 2023-05-08 19:57:13.159 青碧凝霜提示：解析字符串尽量要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示
[INFO ] 2023-05-08 19:57:13.160 对第 3 个参数注入结束, 注入类型为 class java.util.ArrayList ,值为 [a, b, c]
[DEBUG] 2023-05-08 19:57:13.160 青碧凝霜提示：解析字符串尽量要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示
[INFO ] 2023-05-08 19:57:13.161 对第 4 个参数注入结束, 注入类型为 char[] ,值为 [a]
[INFO ] 2023-05-08 19:57:13.161 获取结果, 类型为 class java.lang.String ,值为 2023-05-08
[INFO ] 2023-05-08 19:57:13.161 运算时间小于1毫秒
[INFO ] 2023-05-08 19:57:13.161 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.161   任务: 注解SourceFrom   任务来源: examples.Solution          方法:  run05
[INFO ] 2023-05-08 19:57:13.161 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.161 获取结果, 类型为 class java.lang.String ,值为 hello world
[INFO ] 2023-05-08 19:57:13.161 运算时间小于1毫秒
[INFO ] 2023-05-08 19:57:13.161 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.161   任务: 注解SourceFrom   任务来源: examples.Solution          方法:  run06
[INFO ] 2023-05-08 19:57:13.161 ------------------------------------------------------------------------------
[INFO ] 2023-05-08 19:57:13.162 对第 1 个参数注入结束, 注入类型为 class java.lang.String ,值为 南默请
[INFO ] 2023-05-08 19:57:13.162 对第 2 个参数注入结束, 注入类型为 class java.lang.String ,值为 ewyDtVzsyW
[INFO ] 2023-05-08 19:57:13.163 获取结果, 类型为 class java.lang.String ,值为 南默请ewyDtVzsyW
[INFO ] 2023-05-08 19:57:13.163 运算时间小于1毫秒

进程已结束，退出代码为 0

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
    new TextTestStarter(Solution.class).starkAllTask();
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


如果实在无法理解，在src/main/java/examples里面有例子
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

