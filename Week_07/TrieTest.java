package Trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/2/20 15:34
 * @Content
 */
public class TrieTest {
    /*
    Trie的Python实现模板
  */
//    class Trie(object):
//
//    def __init__(self):
//    self.root = {}
//    self.end_of_word = "#"
//    def insert(self, word):
//    node = self.root
// for char in word:
//    node = node.setdefault(char, {})
//    node[self.end_of_word] = self.end_of_word
//    def search(self, word):
//    node = self.root
// for char in word:
//      if char not in node:
//      return False
//      node = node[char]
//      return self.end_of_word in node
//    def startsWith(self, prefix):
//    node = self.root
// for char in prefix:
//      if char not in node:
//      return False
//      node = node[char]
//      return True

    /*
    此相邻称为四联通：上下左右四联通，如果斜线也算相邻，则成为八联通
     */
    Set results = new HashSet<String>();
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    public List<String> findWords(char[][] board, String[] words) {
        /*
        方法一：words 遍历 ，boards --> search
        假设words单词个数为N，boards为m * m,k为words的所有单词的平均长度
       则时间复杂度为O（N*m*m*4^k）
       N*m*m是查找所有单词首字母，还有每个单词除了首字母外其他字符需要上下左右查找，再乘4^k
         */
        /*
        方法二：字典树Trie

         */

        if (words.length == 0 || board.length == 0) return new ArrayList<>();
        Trie wordtrie = new Trie();
        for (String s : words) {
            wordtrie.insert(s);
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                DFSboard(board, i, j, m, n, "", wordtrie);
            }
        }
        return new ArrayList<>(results);
    }

    private void DFSboard(char[][] board, int i, int j, int m, int n, String curword, Trie curtrie) {
        curword += board[i][j];
        curtrie = curtrie.next[board[i][j] - 'a'];
        if (curtrie.end) results.add(curword);
        char tmp = board[i][j];board[i][j] = '@';//@ 是自定义的访问过的标识
        for (int k = 0;k < 4;k++) {
            int x = i + dx[k],y = j + dy[k];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '@' && curtrie.next[board[x][y] - 'a'] != null) {
                DFSboard(board,x,y,m,n,curword,curtrie);
            }
        }
        board[i][j] = tmp;
    }

}
