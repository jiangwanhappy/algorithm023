package Trie;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/2/20 15:34
 * @Content   Trie的Java实现模板 ,力扣原题208
 */
public class Trie {
    public boolean end;//代表从根节点到此节点走过的路径所代表的字符串是否是一个单词
    public Trie[] next;

    /** Initialize your data structure here. */
    public Trie() {
        end = false;
        next = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        char[] chararray = word.toCharArray();
        Trie curr = this;
        for (int i = 0;i < chararray.length;i++) {
            int n = chararray[i] - 'a';
            if (curr.next[n] == null)
                curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie curr = searchPrefix(word);
        return curr != null && curr.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie curr = searchPrefix(prefix);
        return curr != null;
    }

    private Trie searchPrefix(String word) {
        if (word == null || word.length() == 0)
            return null;
        char[] chararray = word.toCharArray();
        Trie curr = this;
        for (int i = 0;i < chararray.length;i++) {
            curr = curr.next[chararray[i] - 'a'];
            if (curr == null) return null;
        }
        return curr;
    }
}
