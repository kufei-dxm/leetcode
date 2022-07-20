package com.leetcode.array;

import java.util.BitSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 287. Find the Duplicate Number
 *
 * @author kufei.dxm
 * @date 2022/7/18
 */
public class Problem287 {
    private Problem287 solution;

    @Before
    public void setUp() {
        solution = new Problem287();
    }

    /**
     * 根据题目的限制，配合bitMap可比较直观地求解。（不过不太符合constant space的要求）
     * 运行性能和时间中位数。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int numCnt = nums.length - 1;
        BitSet bitSet = new BitSet(numCnt + 1);
        for (int i : nums) {
            if (!bitSet.get(i)) {
                bitSet.set(i);
            } else {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param nums
     * @return
     */
    public int findDuplicateV2(int[] nums) {
        int slow = 0, fast = 0, slow1 = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        while (slow1 != fast) {
            slow1 = nums[slow1];
            fast = nums[fast];
        }
        return slow1;
    }

    @Test
    public void test() {
        int[] nums = new int[] {3, 1, 3, 4, 2};
        int dup = solution.findDuplicate(nums);
        Assert.assertEquals(dup, 3);

        dup = solution.findDuplicateV2(nums);
        Assert.assertEquals(dup, 3);
    }
}
