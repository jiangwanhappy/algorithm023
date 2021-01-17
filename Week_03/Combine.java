import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/1/17 23:27
 * @Content
 */
public class Combine {
    //组合
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;

    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // terminator
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= n; i++) {
            // process current logic
            path.addLast(i);
            //drill down
            dfs(n, k, i + 1, path, res);
            // restore current state
            path.removeLast();
        }
    }

}

