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
    
    String smallest = "zzzzzzzzzzzzzzz";
    
    private String toReversedString(LinkedList<Character> ll){
        StringBuilder sb = new StringBuilder();
        for (Character str : ll){
            sb.append(str);
        }
        return sb.reverse().toString();
    }
    
    private void dfs(TreeNode root, LinkedList<Character> sb){
        if(root == null){
            return;
        }
        char rch = (char)root.val;
        rch += 'a';
        sb.add(rch);
        if(root.left == null && root.right == null){
            String current = toReversedString(sb);
            if(smallest.compareTo(current) > 0){
                smallest = current;
            }
        }
        if (root.left != null) {
            dfs(root.left, sb);
        }
        if (root.right != null){
            dfs(root.right, sb);
        }
        sb.removeLast();
    }
    
    public String smallestFromLeaf(TreeNode root) {
        smallest = "zzzzzzzzzzzzz";
        dfs(root, new LinkedList<Character>());
        return smallest;
    }
}