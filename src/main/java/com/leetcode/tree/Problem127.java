package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 127. Word Ladder
 *
 * @author kufei.dxm
 * @date 2022/6/10
 */
public class Problem127 {
    private Problem127 solution;

    @Before
    public void setUp() {
        solution = new Problem127();
    }

    private int len = Integer.MAX_VALUE;

    /**
     * DFS思路的穷举法写了半天，Time Limit Exceeded了。。。其实思路完全错了，用BFS轻松解决。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        List<String> path = new ArrayList<>();
        findPath(beginWord, endWord, wordList, path);
        if (len < Integer.MAX_VALUE) {
            return len;
        } else {
            return 0;
        }
    }

    private void findPath(String currentWord, String endWord, List<String> wordList, List<String> curPath) {
        curPath.add(currentWord);
        //出现环路
        if (curPath.indexOf(currentWord) != curPath.size() - 1) {
            return;
        }
        //find a path
        if (currentWord.equals(endWord)) {
            if (curPath.size() < len) {
                len = curPath.size();
                return;
            } else if (curPath.size() > len) {
                return;
            }
        }
        //continue find
        List<String> diffWords = findDiffWords(currentWord, wordList);
        for (String diffWord : diffWords) {
            //curPath.add(diffWord);
            findPath(diffWord, endWord, wordList, curPath);
            curPath.remove(curPath.size() - 1);
        }
    }

    public List<String> findDiffWords(String word, List<String> wordList) {
        List<String> result = new ArrayList<>();
        for (String dict : wordList) {
            if (diff(word, dict)) {
                result.add(dict);
            }
        }
        return result;
    }

    private boolean diff(String word, String dict) {
        int diffCnt = 0;
        for (int i = 0; i < dict.length(); i++) {
            if (word.charAt(i) != dict.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    public List<String> findDiffWordsV2(String word, List<String> wordList) {
        List<String> toAdd = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String word1 = new String(chars);
                if (wordList.contains(word1)) {
                    toAdd.add(word1);
                    wordList.remove(word1);
                }
            }
        }
        return toAdd ;
    }

    /**
     * BFS。 还是会超时。。。。暂时无解了。。。。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthV2(String beginWord, String endWord, List<String> wordList) {
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        int len = 1;
        while (!reached.contains(endWord)) {
            List<String> toAdd = new ArrayList<>();
            for (String reach : reached) {
                toAdd.addAll(findDiffWordsV2(reach, wordList));
            }
            reached.addAll(toAdd);
            len++;
            if (toAdd.size() == 0) {
                return 0;
            }
        }
        return len;
    }

    @Test
    public void test() {
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        int len = solution.ladderLengthV2("hit", "cog", wordList);
        Assert.assertEquals(len, 5);
    }
}
