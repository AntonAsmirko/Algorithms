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
    fun maxDepth(root: TreeNode?): Int {
        return dfs(root)
    }
    
    fun dfs(node: TreeNode?): Int{
        if(node == null){
            return 0
        }
        return Math.max(dfs(node!!.left), dfs(node!!.right)) + 1
    }
}
