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
    fun isSymmetric(root: TreeNode?): Boolean {
        return check(root!!.left, root!!.right)
    }
    
    fun check(l: TreeNode?, r: TreeNode?): Boolean{
        if(l == null && r == null){
            return true
        }
        if(l == null && r != null){
            return false
        }
        if(l != null && r == null){
            return false
        }
        if(l!!.`val` != r!!.`val`){
            return false
        }
        return check(l.right,r.left) && check(l.left, r.right)
    }
}