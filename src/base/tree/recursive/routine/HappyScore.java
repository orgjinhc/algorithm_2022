package base.tree.recursive.routine;

import java.util.List;

/**
 * 派对最大快乐值
 */
public class HappyScore {

    static class Employee {
        int happy;
        List<Employee> subordinates;

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }

        public int getHappy() {
            return happy;
        }

        public void setHappy(int happy) {
            this.happy = happy;
        }

        public List<Employee> getSubordinates() {
            return subordinates;
        }

        public void setSubordinates(List<Employee> subordinates) {
            this.subordinates = subordinates;
        }
    }

    static class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    public static Info process(Employee X) {
        if (X.subordinates.isEmpty()) {
            return new Info(X.happy, 0);
        }

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

    public static void main(String[] args) {

    }
}
