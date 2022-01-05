package leetcode.daily.easy;

import java.util.*;

/**
 * 现在你总共有 numCourses 门课需要选，记为'0'到'numCourses - 1。给你一个数组'prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修'bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程'1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 */
public class FindOrder_210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ansList = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ansList[i] = i;
        }
        if (prerequisites == null || prerequisites.length < 1) {
            return ansList;
        }

        //  1.构造所有课和其关系
        Map<Integer, Course> courseMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];

            if (!courseMap.containsKey(to)) {
                courseMap.put(to, new Course(to));
            }

            if (!courseMap.containsKey(from)) {
                courseMap.put(from, new Course(from));
            }

            Course fromCourse = courseMap.get(from);
            Course toCourse = courseMap.get(to);

            //  依赖关系维护好
            fromCourse.nexts.add(toCourse);
            //  入度更新
            toCourse.in++;
        }

        //  2.所有入度为0的课程加入队列
        int index = 0;
        Queue<Course> zeroQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            //  numCourses 所需课程
            //  如果当前课程关系集合不包含当前课程, 加入集合
            if (!courseMap.containsKey(i)) {
                ansList[index++] = i;
            } else {
                if (courseMap.get(i).in == 0) {
                    zeroQueue.add(courseMap.get(i));
                }
            }
        }

        //  3.统计完成课程数量, 更新依赖关系
        int ans = 0;
        while (!zeroQueue.isEmpty()) {
            Course poll = zeroQueue.poll();
            ansList[index++] = poll.name;
            ans++;
            for (Course next : poll.nexts) {
                if (--next.in == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return ans == courseMap.size() ? ansList : new int[0];
    }

    public class Course {
        public int name;
        public int in;
        public List<Course> nexts;

        public Course(int name) {
            this.name = name;
            this.in = 0;
            this.nexts = new ArrayList<>();
        }
    }
}
