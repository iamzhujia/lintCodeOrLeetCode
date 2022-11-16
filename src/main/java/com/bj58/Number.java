package com.bj58;

public class Number {
    public static void main(String[] a){
        int nums[] = {3};
        nums = new Number().missingTwo(nums);
        System.out.println(nums[0] + "," + nums[1]);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(35^501);
    }
    public int[] missingTwo(int[] nums) {
        int len = nums.length;
        int a = -1,  b = -1;
        int tem = -1, tem2 = -1;
        for(int i = 0; i < len; ++i){
            if(nums[i] != i+1){
                tem = nums[i];
                nums[i] = 0;
                while(tem < len + 1 && nums[tem - 1] != 0){
                    tem2 = nums[tem - 1];
                    nums[tem - 1] = tem;
                    tem = tem2;
                }
                if(tem > len){
                    if(a == -1){
                        a = tem;
                    }else{
                        b = tem;
                    }
                }else{
                    nums[tem - 1] = tem;
                }
            }
        }
        if(a == -1){
                return new int[]{len+1, len+2};
        }else{
            if(b == -1){
                if(a == len+2){
                    for(int i = 0; i < len; ++i){
                        if(nums[i] == 0){
                            return new int[]{i + 1, a - 1};
                        }
                    }
                }
                for(int i = 0; i < len; ++i){
                    if(nums[i] == 0){
                        return new int[]{i + 1, a+1};
                    }
                }
            }else{
                int[] r = new int[]{0,0};
                for(int i = 0; i < len; ++i){
                    if(nums[i] == 0){
                        if(r[0] == 0){
                            r[0] = i + 1;
                        }else{
                            r[1] = i + 1;
                        }
                    }
                }
                return r;
            }
        }
        return new int[]{1,2};
    }
}
