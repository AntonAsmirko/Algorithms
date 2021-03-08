/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var result []int

func dfs(node *TreeNode) {
	if node == nil {
		return
	}
	result = append(result, node.Val)
	dfs(node.Left)
	dfs(node.Right)
}

func preorderTraversal(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	result = make([]int, 0)
	dfs(root)
	return result
}