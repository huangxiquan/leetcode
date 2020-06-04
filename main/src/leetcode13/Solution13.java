package leetcode13;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangxiquan on 2020/5/30.
 * 13. 罗马数字转整数 https://leetcode-cn.com/problems/roman-to-integer/
 */
public class Solution13 {

    public int romanToInt(String s) {
        Pattern pattern = Pattern.compile("(CM|CD|XC|XL|IV|IX|C|M|L|V|I|D|X)");
        Matcher matcher = pattern.matcher(s);
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("CM",900);
        map.put("CD",400);
        map.put("XC",90);
        map.put("XL",40);
        map.put("IX",9);
        map.put("IV",4);
        int result = 0;
        while (matcher.find()) {
            result += map.get(matcher.group());
        }
        return result;
    }

    public static void main(String[] args) {
        //MCMXCIV
        //III
        System.out.println(new Solution13().romanToInt("MCMXCIV"));
    }
}
