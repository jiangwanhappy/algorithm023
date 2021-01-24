package NineSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/1/22 9:46
 * @Content
 */
public class DFS {
    //-------------------------模板----------------------
    //递归写法

//#Python
//            visited = set()
//    def dfs(node, visited):
//            if node in visited: # terminator
//    # already visited
//        return
//                visited.add(node)
//            # process current node here.
//        ...
//        for next_node in node.children():
//        if next_node not in visited:
//    dfs(next_node, visited)


    //Java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        travel(root, 0, allResults);
        return allResults;
    }

    private void travel(TreeNode root, int level, List<List<Integer>> results) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if (root.left != null) {
            travel(root.left, level + 1, results);
        }
        if (root.right != null) {
            travel(root.right, level + 1, results);
        }
    }


    //非递归写法
//    #Python
//    def DFS(self, tree):
//            if tree.root is None:
//            return []
//    visited, stack = [], [tree.root]
//            while stack:
//    node = stack.pop()
//            visited.add(node)
//    process (node)
//    nodes = generate_related_nodes(node)
//    stack.push(nodes)
//            # other processing work
//    ...
//


    //--------------------题目-------------------------
    //岛屿数量
    int m,n;
    public int numIslands(char[][] grid) {
        int count = 0;//返回的数量
        if (grid == null) return 0;
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;//有岛屿
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        //上下左右能连在一起的属于同一个岛屿
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }
}
