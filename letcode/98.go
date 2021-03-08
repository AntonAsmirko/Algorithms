/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

/*
* info[i] == true if left
* info[i] == false if right
 */
func check(Val int, arr []int, info []bool) bool {
	for i := len(info) - 1; i >= 0; i-- {
		if info[i] && Val >= arr[i] || !info[i] && Val <= arr[i] {
			return false
		}
	}
	return true
}

func validate(tree *TreeNode, flow []int, info []bool) bool {
	if tree == nil {
		return true
	}
	isOk := true
	flow = append(flow, tree.Val)
	if tree.Left != nil {
		info = append(info, true)
		isOk = check(tree.Left.Val, flow, info) && validate(tree.Left, flow, info)
		if len(info) > 1 {
			info = info[:len(info)-1]
		} else {
			info = []bool{}
		}
	}
	if !isOk {
		if len(flow) > 1 {
			flow = flow[:len(flow)-1]
		} else {
			flow = []int{}
		}
		return false
	}
	if tree.Right != nil {
		info = append(info, false)
		isOk = check(tree.Right.Val, flow, info) && validate(tree.Right, flow, info)
		if len(info) > 1 {
			info = info[:len(info)-1]
		} else {
			info = []bool{}
		}
	}
	if len(flow) > 1 {
		flow = flow[:len(flow)-1]
	} else {
		flow = []int{}
	}
	return isOk
}

func isValidBST(root *TreeNode) bool {
	return validate(root, make([]int, 0), make([]bool, 0))
}