package Recursion;

import org.junit.jupiter.api.Test;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/2/7 11:06
 * @Content
 */
public class RecursionTest {

    //221. 最大正方形
    public int maximalSquare(char[][] matrix) {
        /*
        动态规划，自底向上
        时间复杂度：O(mn)，其中 m 和 n 是矩阵的行数和列数。需要遍历原始矩阵中的每个元素计算 dp 的值。
空间复杂度：O(mn)，其中 m 和 n 是矩阵的行数和列数。创建了一个和原始矩阵大小相同的矩阵 dp。
         */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length,col = matrix[0].length;
        int[][] dp = new int[row][col];//dp[i][i]代表以dp[i][i]为右下角的最大正方形
        int maxSquare = 0;
        for (int i = 0;i < row;i++) {
            for (int j = 0;j < col;j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {//正方形边长为左上角和上方和左方中的最小值 再加1
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                    }
                    maxSquare = Math.max(maxSquare,dp[i][j]);
                }
            }
        }
        return maxSquare * maxSquare;
    }

    //64. 最小路径和
    public int minPathSum(int[][] grid) {
        /*
        动态规划，自底向上
        时间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。需要对整个网格遍历一次，计算 dp 的每个元素的值。
空间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。创建一个二维数组 dp，和网格大小相同。

         */
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length,col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1;i < row;i++) {//对于第一列的元素，只能是上一行第一列的元素向下走得到
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1;j < col;j++) {//对于第一行的元素，只能是第一行上一列的元素向右走得到
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1;i < row;i++) {//对于dp[i][j]的最小值为到达此元素的最小值加该元素的值
            for (int j = 1;j < col;j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }

}
