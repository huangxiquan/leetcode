package leetcode347;

import java.util.*;

/**
 * Created by huangxiquan on 2020/5/16.
 * 347:前 K 个高频元素 https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums) {
            if(map.containsKey(num)) {
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        System.out.println(map);
        for(Integer key : map.keySet()) {
            if(queue.size() < k) {
                queue.add(key);
            }else {
                if(map.get(key) > map.get(queue.peek())) {
                    queue.remove();
                    queue.add(key);
                }
            }
        }
        int[] result = new int[queue.size()];
        int i = 0;
        while (!queue.isEmpty()) {
            result[i] = queue.remove();
            i++;
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int[] results = new Solution347().topKFrequent(nums, 2);
        for(int result : results) {
            System.out.println(result);
        }
    }
}
