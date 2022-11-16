package com.bj58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tonghe {

    public static void main(String[] args){
        List<Integer> list = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 3, 1, 0, 0, 0, 3, 1, -1, 0, 1, 1, 0, 0, 1, 1, 2, 2, 2, 0, 1, 2, 2, 3, 2);

        System.out.println(new Tonghe().splitArray(list));
    }
    public boolean splitArray(List<Integer> nums) {
        int n = nums.size();
        if(n > 7){
            nums = filter(nums);
        }
        n = nums.size();
        int[] sums = new int[n];
        sums[0] = nums.get(0);
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1]+nums.get(i);
        }
        Object[] result = split(nums,3, sums, 0, n );
        Boolean boolResult = (Boolean) result[0];
       return boolResult;
    }
    private List<Integer> filter(List<Integer> nums){
        int index = 0;
        int size = nums.size();
        int endIndex = size -1 ;
        boolean breaked = false;
        for(;index < size - 7; ++index){
            if(nums.get(index) != 0){
                breaked = true;
                break;
            }
        }

        if(breaked){
            for(;endIndex-index>=6;--endIndex){
                if(nums.get(endIndex)!=0){
                    break;
                }
            }
            return nums.subList(index,endIndex + 1);
        }else{
            return nums.subList(index, size);
        }

    }

    private Object[] split(List<Integer> nums, int i, int[] sums, int start, int endIndex) {
        List<Integer> list = new ArrayList<>();
        int size = nums.size();
        if(size < i * 2 + 1){
            return new Object[]{false,0};
        }
        boolean zero = true;

        for(Integer k : nums){
            if(k!=0){
                zero = false;
            }

        }
        if(zero){
            List<Integer> li = new ArrayList<>();
            li.add(0);
            return new Object[]{true, li};
        }

        int end = size-1 - (i - 1)*2 - 1;
        for(int index=1;index<=end; ++ index){
            int left = start == 0?sums[index-1]:sums[index-1+start]-sums[start-1];
            int right = 0;
            if(i == 1){
                right = sums[endIndex-1]-sums[index+start];//sum(nums.subList(index + 1, size));
                if(left == right){
                    list.add(left);
                }
            }else{
                Object[] result = split(nums.subList(index+1,size), i -1, sums, start+index+1, endIndex);
                Boolean boolResult = (Boolean) result[0];
                if(boolResult){
                    List<Integer> li = (List<Integer>) result[1];
                    for(Integer in : li){
                        if(left == in){
                            list.add(left);
                        }
                    }

                }
            }

        }
        if(list.size()>0){
            return new Object[]{true,list};
        }
        return new Object[]{false,0};
    }

    private int sum(List<Integer> subList) {
        int i = 0;
        for(Integer in : subList){
            i+=in;
        }
        return i;
    }
}
