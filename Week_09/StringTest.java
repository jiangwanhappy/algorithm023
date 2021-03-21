package String;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/3/18 15:43
 * @Content
 */
public class StringTest {
    @Test
    public void test() {
        System.out.println(toLowerCase("Hello"));
        System.out.println(lengthOfLastWord(" "));
    }

    //709. 转换成小写字母
    public String toLowerCase(String str) {
        char[] result = str.toCharArray();
        for (int i = 0;i < result.length;i++) {
            // a-z：97-122  A-Z：65-90  0-9：48-57
            if (result[i] >= 'A' && result[i] <= 'Z')
                result[i] = (char) (result[i] + 32);
        }
        return String.valueOf(result);
    }

    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        /*
        这题还要好好看看题解，spilt的速度是慢的
         */
        if (s.isEmpty() || s.trim().isEmpty()) return 0;
        String[] strs = s.split(" ");
        return strs == null ? 0 : strs[strs.length-1].length();
    }

    //771. 宝石与石头
    public int numJewelsInStones(String jewels, String stones) {
            /*
        这题还要好好看看题解，不用hashmap的方式
         */
        int count = 0;
        char[] stone = stones.toCharArray();
        Map<Character,Integer> chars = new HashMap<Character,Integer>();
        for (char c : stone) {
            chars.put(c,chars.getOrDefault(c,0) + 1);
        }
        for (int i = 0;i < jewels.length();i++) {
           count += chars.getOrDefault(jewels.charAt(i),0);
        }
        return count;
    }

    //387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        /*
        一： 循环 + map(hashmap O(1), treemap O(logN))
        时间 ：O(n) or O(logN)
         */
//        Map<Character,Integer> chars = new HashMap<Character,Integer>();
//        char[] ss = s.toCharArray();
//        for (char c : ss) {
//            chars.put(c,chars.getOrDefault(c,0) + 1);
//        }
//        for (int i = 0;i < ss.length;i++) {
//            if (chars.get(ss[i]) == 1)
//                return i;
//        }
//        return -1;
        /*
          二：用字母対应的数组下标来表示 ，就是一个最简单的hash table
         */
        int[] count = new int[256];
        char[] ss = s.toCharArray();
        for (char c : ss) {
            count[c]++;
        }
        for (int i = 0;i < ss.length;i++) {
            if (count[ss[i]] == 1) {
                return i;
            }
        }
        return -1;
    }

    //151. 翻转字符串里的单词
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" +"); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for(int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if(strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }




}
