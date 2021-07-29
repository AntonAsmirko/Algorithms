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
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if(nums.size == 0){
            return null
        }
        return makeTree(0, nums.size, nums)
    }
    
    fun makeTree(l: Int, r: Int, nums:IntArray): TreeNode? {
        if(l == r){
            return null
        }
        val mid = l + (r - l) / 2
        return TreeNode(nums[mid]).apply{
            left = makeTree(l, mid, nums)
            right = makeTree(mid + 1, r, nums)
        }
    }
}
