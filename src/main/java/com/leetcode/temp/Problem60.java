package com.leetcode.temp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dxm
 * @Date 2022/9/4
 * @see <a href="https://leetcode.com/problems/permutation-sequence/">leetcode-> 60. Permutation Sequence</a>
 */
public class Problem60 {
    private Problem60 solution;

    @Before
    public void setUp() {
        solution = new Problem60();
    }

    public String getPermutation(int n, int k) {
        long start = System.currentTimeMillis();
        //init array. ？
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> permuRets = new ArrayList<>();
        dfs(permuRets, new ArrayList<>(), nums, k);
        List<Integer> kthPermu = permuRets.get(k - 1);
        StringBuilder sb = new StringBuilder();
        for (Integer num : kthPermu) {
            sb.append(num);
        }
        System.out.println(n + " num permutations takes time:" + (System.currentTimeMillis() - start) + " ms");
        return sb.toString();
    }

    public void dfs(List<List<Integer>> result, List<Integer> currPermu, int[] nums, int k) {
        if (currPermu.size() == nums.length) {
            result.add(new ArrayList<>(currPermu));
            return;
        }
        if (result.size() == k) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (currPermu.contains(nums[i])) {
                continue;
            }
            currPermu.add(nums[i]);
            dfs(result, currPermu, nums, k);
            currPermu.remove(currPermu.size() - 1);
        }
    }

    public String getPermutationV2(int n, int k) {
        return dp(n, k);
    }

    /**
     * dp解法
     */
    public String dp(int n, int k) {
        long start = System.currentTimeMillis();
        List<String>[] dp = new ArrayList[n + 1];
        List<String> st = new ArrayList<>();
        st.add("1");
        dp[1] = st;
        for (int i = 2; i <= n; i++) {
            List<String> nextDp = new ArrayList<>();
            List<String> preDp = dp[i - 1];
            for (String per : preDp) {
                nextDp.addAll(insertStr(per, String.valueOf(i)));
            }
            dp[i] = nextDp;
        }
        System.out.println(n + " nums with dp cost time:" + (System.currentTimeMillis() - start) + " ms");
        return dp[n].get(k - 1);
    }

    /**
     * Round Three
     * @param n
     * @param k
     * @return
     */
    public String permutation(int n, int k) {
        long start = System.currentTimeMillis();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int idx = k / factorial[n - i];
            sb.append(nums.get(idx));
            nums.remove(idx);
            k -= idx * factorial[n - i];
        }
        System.out.println(n + " nums with per cost time:" + (System.currentTimeMillis() - start) + " ms");
        return sb.toString();
    }

    /**
     * 在string的[0,length]位置插入str，生成新的str
     *
     * @param orgStr
     * @param insertStr
     * @return
     */
    public List<String> insertStr(String orgStr, String insertStr) {
        List<String> result = new ArrayList<>();
        result.add(insertStr + orgStr);
        for (int i = 0; i < orgStr.length(); i++) {
            result.add(orgStr.substring(0, i + 1) + insertStr + orgStr.substring(i + 1));
        }
        return result;
    }


    @Test
    public void testInterStr() {
        List<String> result = solution.insertStr("123", "4");
        result.forEach(System.out::println);
    }

    @Test
    public void testPer(){
        String per = solution.permutation(4, 9);
        Assert.assertEquals("2314", per);
        per = solution.permutation(3, 3);
        Assert.assertEquals("213", per);
        per = solution.permutation(9, 278893);
        Assert.assertEquals("793416258", per);
    }
    @Test
    public void testDp() {
        String per = solution.getPermutationV2(4, 9);
        Assert.assertEquals("2314", per);
        per = solution.getPermutationV2(3, 3);
        Assert.assertEquals("213", per);
        per = solution.getPermutation(9, 278893);
        Assert.assertEquals("793416258", per);
    }

    @Test
    public void test() {
        String per = solution.getPermutation(3, 3);
        Assert.assertEquals("213", per);
        per = solution.getPermutation(4, 9);
        Assert.assertEquals("2314", per);
        per = solution.getPermutation(9, 278893);
        Assert.assertEquals("793416258", per);
    }
}
