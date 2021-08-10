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
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        return root?.let{
            if(it.`val` >= low && it.`val` <= high) {it.`val`} else {0} + rangeSumBST(it.left, low, high) + rangeSumBST(it.right, low, high)
        } ?: 0
    }
}
