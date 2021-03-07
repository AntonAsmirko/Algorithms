/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var result [][]int

func clone(arr []int) (res []int) {
	res = make([]int, len(arr))
	copy(res, arr)
	return
}

func makePaths(path []int, root *TreeNode, targetSum, sum int) {
	if root == nil {
		return
	}
	sum += root.Val
	path = append(path, root.Val)
	//fmt.Printf("%d\n", sum)
	if root.Left == nil && root.Right == nil && sum == targetSum {
		result = append(result, clone(path))
		return
	} else if root.Left == nil && root.Right != nil {
		makePaths(path, root.Right, targetSum, sum)
	} else if root.Left != nil && root.Right == nil {
		makePaths(path, root.Left, targetSum, sum)
	} else {
		makePaths(path, root.Right, targetSum, sum)
		makePaths(path, root.Left, targetSum, sum)
	}
	path = path[:len(path)-1]
}

func pathSum(root *TreeNode, targetSum int) [][]int {
	result = make([][]int, 0)
	makePaths(make([]int, 0), root, targetSum, 0)
	return result
}