package com.bj58;

import java.util.*;

public class Tree {
    public static void main(String[] args){
        TreeNode t = new TreeNode(7);
        int k = new Tree().maxPathSum(t);
        System.out.println(k);
    }
    private int value = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        int v = maxPathSum1(root);
        return Math.max(v, value);
    }
    public int maxPathSum1(TreeNode root) {
        int left = 0;
        if(root.left != null){
            left = maxPathSum1(root.left);
            if(left < 0){
                left = 0;
            }
        }
        int right = 0;
        if(root.right != null){
            right = maxPathSum1(root.right);
            if(right < 0){
                right = 0;
            }
        }
        int v = root.val + left + right;
        if(v > value){
            value = v;
        }
        return root.val + Math.max(left, right);
    }
}

 // Definition for a binary tree node.
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 