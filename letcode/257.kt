import java.util.LinkedList

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
    
    var res = LinkedList<String>()
    
    fun binaryTreePaths(root: TreeNode?): List<String> {
        res = LinkedList<String>()
        if(root == null){
            return res
        }
        dfs(root, LinkedList<Int>())
        return res
    }
    
    fun dfs(node: TreeNode, str: LinkedList<Int>){
        str.add(node.`val`)
        if(node.left == null && node.right == null){
            res.add(str.joinToString(separator = "->"))
        }
        if(node.left != null){
            dfs(node.left, str)
        }
        if(node.right != null){
            dfs(node.right, str)
        }
        str.removeLast()
    }
}
