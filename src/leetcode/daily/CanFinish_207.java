package leetcode.daily;

import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为'0'到'numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组'prerequisites 给出，其中'prerequisites[i] = [ai, bi] ，表示如果要学习课程'ai 则 必须 先学习课程' bi 。
 * <p>
 * 例如，先修课程对'[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 链接：https://leetcode-cn.com/problems/course-schedule
 */
public class CanFinish_207 {

    public static class Course {
        private int name;
        private int in;
        private List<Course> nexts;

        public Course(int name) {
            this.name = name;
        }
    }

    /**
     * 核心流程
     * 1.先把所有课和其对应关系构造出来
     * 2.所有入度为0的加入队列
     * 3.每次弹出一个课统计一个完成数量, 最后和课程数做比较
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length < 1) {
            return true;
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
        Queue<Course> zeroQueue = new LinkedList<>();
        for (Map.Entry<Integer, Course> entry : courseMap.entrySet()) {
            Course course = entry.getValue();
            if (course.in == 0) {
                zeroQueue.add(course);
            }
        }

        //  3.统计完成课程数量, 更新依赖关系
        int ans = 0;
        while (!zeroQueue.isEmpty()) {
            Course poll = zeroQueue.poll();
            ans++;
            for (Course next : poll.nexts) {
                if (--next.in == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return ans == courseMap.size();
    }
}
