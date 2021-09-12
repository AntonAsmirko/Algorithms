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
    
    var res = 0
    
    fun longestConsecutive(root: TreeNode?): Int {
        res = 0
        if(root == null){
            return 0
        }
        res = 1
        dfs(root!!.left, root, 1)
        dfs(root!!.right, root, 1)
        return res
    }
    
    fun dfs(node: TreeNode?, prev: TreeNode, count: Int) {
        if(node == null){
            return
        }
        var count = count
        if(node!!.`val` - prev.`val` == 1){
            res = Math.max(res, count + 1)
        } else {
            count = 0
        }
        dfs(node.left, node, count + 1)
        dfs(node.right, node, count + 1)
    }
}
