/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func flattenTree(root *TreeNode) *TreeNode {
	if root.Left == nil && root.Right == nil {
		return root
	} else if root.Left != nil && root.Right != nil {
		tail := flattenTree(root.Left)
		tailR := flattenTree(root.Right)
		root.Left, root.Right = root.Right, root.Left
		tail.Right = root.Left
		root.Left = nil
		return tailR
	} else if root.Left == nil && root.Right != nil {
		return flattenTree(root.Right)
	} else {
		tail := flattenTree(root.Left)
		root.Right = root.Left
		root.Left = nil
		return tail
	}
}

func flatten(root *TreeNode) {
	if root == nil {
		return
	}
	flattenTree(root)
}