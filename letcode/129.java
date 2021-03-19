/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    int cumSum = 0;
    
    private void dfs(TreeNode root, int curSum){
        if (root == null) {
            return;
        }
        curSum = curSum * 10 + root.val;
        if(root.left == null && root.right == null){
            cumSum += curSum;
            return;
        }
        if(root.left != null){
            dfs(root.left, curSum);
        }
        if(root.right != null){
            dfs(root.right, curSum);  
        }
    }
    
    public int sumNumbers(TreeNode root) {
        this.cumSum = 0;
        dfs(root, 0);
        return this.cumSum;
    }
}