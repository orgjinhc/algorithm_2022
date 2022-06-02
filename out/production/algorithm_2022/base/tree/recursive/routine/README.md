# 将设以X节点为头, 可以向X左子树和X右子树要任何信息
一般情况下就是除了叶子结点边界逻辑下的代码部分, 万年不变递归将X的左右孩子作为X传入函数S

# 在上一步假设下, 讨论以X为头节点的树, 得到答案的可能性(核心, 大部分场景都是围绕这步进行逻辑分支和核心逻辑处理)
X参与逻辑和X不参与逻辑两大逻辑

# 列出所有可能性, 确定到底要左树和右树要什么样的信息
无论参不参与都要考虑左右子树需要什么返回信息 参考Info类

# 把左右两树的信息求全集, 就是任何一颗子树都需要返回的信息S
每一次递归内都可以获得左右两树的信息，汇总信息返回给上层递归调用处

# 递归函数都返回S, 每一棵子树都这么要求

# 开始写代码, 代码中考虑如何把左子树信息和右子树的信息整合出整棵树的信息

```java
public class Happy {
    //  将设以X节点为头, 可以向X左子树和X右子树要任何信息
    
    public static Info process(Employee X) {
        //  在上一步假设下, 讨论以X为头节点的树, 得到答案的可能性(核心, 大部分场景都是围绕这步进行逻辑分支和核心逻辑处理)
        if (X.subordinates.isEmpty()) {
            return new Info(X.happy, 0);
        }
        
        //  列出所有可能性, 确定到底要左树和右树要什么样的信息
        int yes = X.happy;
        int no = 0;

        for (Employee subordinate : X.subordinates) {

            //  向子树要任何信息
            Info subordinateInfo = process(subordinate);


            //  列出所有可能性, 确定信息Info
            yes += subordinateInfo.no;
            no += Math.max(subordinateInfo.yes, subordinateInfo.no);
        }
        return new Info(yes, no);
    }
}
```