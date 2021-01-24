package Ten;

import java.util.Arrays;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/1/24 23:20
 * @Content
 *///使用贪心算法解题
public class greed {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);//先排序
        Arrays.sort(s);
        int g_i = 0,s_i = 0;
        for (;g_i < g.length && s_i < s.length;) {
            if (g[g_i] <= s[s_i]) {
                g_i++;//满足g_i最小胃口
            }
            s_i++;
        }
        return g_i;
    }
}
