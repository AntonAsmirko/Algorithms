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
    
    List<Integer> result;
    
    private void print(TreeNode root){
        if(root.left != null){
            print(root.left);
        }
        if(root.right != null){
            print(root.right);
        }
        result.add(root.val);
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        result = new LinkedList<>();
        if(root == null){
            return result;
        }
        print(root);
        return result;
    }
}