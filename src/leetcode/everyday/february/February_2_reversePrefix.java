package leetcode.everyday.february;

public class February_2_reversePrefix {

    public static String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        }

        String subWord = word.substring(0, index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(subWord);
        StringBuilder ans = sb.reverse();
        return ans + word.substring(index + 1);
    }

    public static int minSum(int[][] matrix) {
        return f(matrix, 0, 0, matrix.length, matrix[0].length);
    }

    /**
     * 从index出发, 到目标i、j位置的最短路径和
     *
     * @param matrix
     * @param index
     * @param i
     * @param j
     * @return
     */
    public static int f(int[][] matrix, int index, int i, int j) {
        if (index == i && index == j) {
            return 0;
        }


    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {2, 4, 1}, {4, 6, 2}};
    }
}
