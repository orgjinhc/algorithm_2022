package leetcode.everyday.january;

import java.util.LinkedList;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * <p>
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * 示例 3：
 * <p>
 * 输入：path = "/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * <p>
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 * <p>
 * 提示：
 * <p>
 * 1 <= path.length <= 3000
 * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 * path 是一个有效的 Unix 风格绝对路径。
 * <p>
 * 链接：https://leetcode-cn.com/problems/simplify-path
 */
public class January_6_simplifyPath {

    /**
     * 核心思路, 通过一个简单的数据结构 + 几种特殊的规则, 操作字符串
     * ..  类似出栈行为
     * .   什么都不做
     * xxx 入栈行为
     *
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        String[] paths = path.split("/");

        //  stack 记录路径
        LinkedList<String> queue = new LinkedList<>();
        for (int i = 0; i < paths.length; i++) {
            String curPath = paths[i];
            //  /a/./ -> 当前路径不用添加到结果
            if (curPath.equals(".") || curPath.equals("") || curPath.length() < 1) {
                continue;
            }
            //  /a/b/../ -> /a 直接跳转到父目录
            if (curPath.equals("..")) {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
                continue;
            }
            //  一个正常的目录, 直接放入队列
            queue.addLast(curPath);
        }

        StringBuilder sb = new StringBuilder();
        if (queue.isEmpty()) {
            sb.append("/");
            return sb.toString();
        }
        while (!queue.isEmpty()) {
            sb.append("/");
            sb.append(queue.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/home//foo/";
        System.out.println(simplifyPath(path));
    }

}