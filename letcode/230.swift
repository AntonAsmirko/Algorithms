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
    
    var subtreeSize: [String:Int] = [:]
    
    func kthSmallest(_ root: TreeNode?, _ k: Int) -> Int {
        let size = dfsForSize(root, "")
        return search(root, "", k)
    }
    
    func dfsForSize(_ root: TreeNode?, _ move: String) -> Int{
        if(root == nil){
            subtreeSize[move] = 0
            return 0
        }
        let size = dfsForSize(root!.left, move + "l") + dfsForSize(root!.right, move + "r") + 1
        subtreeSize[move] = size
        return size
    }
    
    func search(_ root: TreeNode?, _ move: String, _ k: Int) -> Int{
        let pos = subtreeSize[move]! - subtreeSize[move + "r"]!
        print("pos = \(pos)", "k = \(k)")
        if(pos == k){
            return root!.val
        } else if pos < k {
            return search(root!.right, move + "r", k - (subtreeSize[move + "l"]! + 1))
        } else {
            return search(root!.left, move + "l", k)
        }
    }
}
