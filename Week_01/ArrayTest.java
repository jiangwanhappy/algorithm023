package Array;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author jiangshine
 * @version 1.0
 * @date 2021/1/27 14:43
 * @Content
 */
public class ArrayTest {

    @Test
    public void test() {
        int[] nums = {3,2,4};
        twoSum(nums,6);
    }

    @Test
    public int[] twoSum(int[] nums, int target) {
        //2层循环
        int[] result = new int[2];
        for (int i = 0;i < nums.length - 1;i++) {
            for (int j = i+1;j < nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    //移动零
    public void moveZeroes(int[] nums) {
        int j = 0;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
//        if (nums == null || nums.length < 3) {
//            return new ArrayList<>();
//        } // [-1, 0, 1, 2, -1, -4]
//        Set<List<Integer>> results = new HashSet<List<Integer>>();
//        Arrays.sort(nums);
//        int value;
//        for (int i = 0; i< nums.length - 2;i++) {
//            if (nums[i] > 0) break;//nums[i]大于0 ，则3数之和一定大于0
//            if (i > 0 && nums[i] == nums[i-1]) continue;//去重
//            int j = i + 1,k = nums.length - 1;
//            while (j < k) {
//                value = -(nums[j] + nums[k]);
//                if (nums[i] < value)
//                    while (j < k && nums[j] == nums[++j]);//不包含重复数据
//                else if (nums[i] > value)
//                    while (j < k && nums[k] == nums[--k]);
//                else if (nums[i] == value) {
//                    results.add( Arrays.asList(nums[i],nums[j],nums[k]));
//                    while (j < k && nums[j] == nums[++j]);
//                    while (j < k && nums[k] == nums[--k]);
//                }
//            }
//        }
//        return new ArrayList<>(results);

        //一次hash + 左右紧逼
        //todo LinkedHashSet还不了解，之后了解了再了解并默写这种写法
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        } // [-1, 0, 1, 2, -1, -4]
        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            Map<Integer, Integer> hashmap = new HashMap<>(nums.length - 2);
            for (int j = i + 1; j < nums.length; j++) {
                int v = target - nums[j];
                Integer exist = hashmap.get(v);
                if (exist != null) {
                    List<Integer> list = Arrays.asList(nums[i], exist, nums[j]);
                    list.sort(Comparator.naturalOrder());
                    result.add(list);
                } else {
                    hashmap.put(nums[j], nums[j]);
                }
            }
        }
        return new ArrayList<>(result);
    }




















}
