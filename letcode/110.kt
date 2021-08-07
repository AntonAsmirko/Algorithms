/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun isBalanced(root: TreeNode?): Boolean {
       return isBalancedd(root) >= 0
    }
    
    fun isBalancedd(node: TreeNode?): Int{
        if(node == null){
            return 0
        }
        if(node!!.left == null && node!!.right == null){
            return 1
        }
        val lDepth = isBalancedd(node.left)
        val rDepth = isBalancedd(node.right)
        if(lDepth == -1 || rDepth == -1){
            return -1
        }
        if(Math.abs(lDepth - rDepth) > 1){
            return -1
        }
        return Math.max(lDepth, rDepth) + 1
    }
}
