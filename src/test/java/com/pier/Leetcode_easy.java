package com.pier;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhongweiwu
 * @date 2019/1/31 18:21
 */
public class Leetcode_easy {

    /**
     * 1 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length;i++){
            int x = target-nums[i];
            if (map.containsKey(x)){
                return new int[]{map.get(x),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    /**
     * 7 整数反转
     * @param x
     * @return
     */
    public long reverse(int x) {
        long r=0;
        for(int y=x;y!=0;y/=10){
            r=r*10+y%10;
        }
        if(r>Integer.MAX_VALUE || r<Integer.MIN_VALUE){
            return 0;
        }
        return x>0?(int)r:(int)(-r);
    }

    /**
     * 9 回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        long r = reverse(x);
        if (r>Integer.MAX_VALUE || r<Integer.MIN_VALUE){
            return false;
        }
        if (x == r){
            return true;
        }
        return false;
    }

    public boolean isPalindrome2(int x) {
        if (x<0){
            return false;
        }
        String str = (""+x);
        int len=str.length();
        for (int i=0;i<len/2;i++){
            if(str.charAt(0)!=str.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome3(int x) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = String.valueOf(x).length();
        for (int i=1;i<=(int)Math.ceil(len/2.0);i++){
            map.put(i,x%10);
            x/=10;
        }
        for(int i=(int)Math.ceil(len/2.0)+1;i<=len;i++){
            if (map.get(len+1-i)!=(x%10)){
                return false;
            }
            x/=10;
        }
        return true;
    }

    // 超时
    public boolean isPalindrome4(int x) {
        if (x<0) {
            return false;
        }
            int len = String.valueOf(x).length();
            int div = (int)Math.pow(10,len/2);
            int tail = x % div;
            x/=div;
            int y=reverse2(tail);
            while(x%10==0){
                x/=10;
            }
            if((len & 1)!=0 && x/10==y){
                return true;
            }
            if((len & 1)==0 && x==y){
                return true;
            }
            return false;
        }
        public int reverse2(int x) {
            int r=0;
            for(int y=x;y!=0;y/=10){
                r=r*10+y%10;
            }
            return x>0?r:-r;
        }

    public boolean isPalindrome5(int x) {
        if (x<0 || (x!=0 && x%10==0)){
            return false;
        }
        int r=0;
        for(;x>r;x/=10){
            r=r*10+x%10;
        }
        return r==x||r/10==x;
    }

    @Test
    public void get() throws Exception {
        System.out.println(isPalindrome5(1001));

    }
}
