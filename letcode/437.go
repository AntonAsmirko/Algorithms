/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var result int = 0

func makePaths(path []int, root *TreeNode, targetSum, sum int) {
	if root == nil {
		return
	}
	sum += root.Val
	path = append(path, root.Val)
	if sum == targetSum {
		result++
	}
	if root.Left == nil && root.Right != nil {
		makePaths(path, root.Right, targetSum, sum)
	} else if root.Left != nil && root.Right == nil {
		makePaths(path, root.Left, targetSum, sum)
	} else {
		makePaths(path, root.Right, targetSum, sum)
		makePaths(path, root.Left, targetSum, sum)
	}
	path = path[:len(path)-1]
}

func commonDfs(root *TreeNode, targetSum, sum int) {
	if root == nil {
		return
	}
	makePaths(make([]int, 0), root, targetSum, sum)
	commonDfs(root.Left, targetSum, sum)
	commonDfs(root.Right, targetSum, sum)
}

func pathSum(root *TreeNode, sum int) int {
	result = 0
	commonDfs(root, sum, 0)
	return result
}