/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init() { self.val = 0; self.left = nil; self.right = nil; }
 *     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
 *     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
 *         self.val = val
 *         self.left = left
 *         self.right = right
 *     }
 * }
 */
class Solution {
    
    var result: [Int] = []
    var depthCovered = 0
    
    func rightSideView(_ root: TreeNode?) -> [Int] {
        if(root == nil){
            return []
        }
        result = []
        dfs(root, 1)
        return result
    }
    
    func dfs(_ root: TreeNode?, _ depth: Int){
        if(root == nil){
            return
        }
        if(depth > depthCovered){
            depthCovered = depth
            result.insert(root!.val, at: result.count)
        }
        dfs(root!.right, depth + 1)
        dfs(root!.left, depth + 1)
    }
}
