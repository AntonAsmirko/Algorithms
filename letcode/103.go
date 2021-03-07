/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func reverse(arr []int) []int {
	for i, j := 0, len(arr)-1; i < j; i, j = i+1, j-1 {
		arr[i], arr[j] = arr[j], arr[i]
	}
	return arr
}

func zigzagLevelOrder(root *TreeNode) (result [][]int) {
	result = make([][]int, 0)
	if root == nil {
		return
	}

	if root == nil {
		return result
	}

	if root.Left == nil && root.Right == nil {
		return append(result, []int{root.Val})
	}

	var (
		queue         []*TreeNode
		curLevelSize  int
		nextLevelSize int
	)

	if root.Left == nil {
		curLevelSize = 1
		queue = []*TreeNode{root.Right}
	} else if root.Right == nil {
		curLevelSize = 1
		queue = []*TreeNode{root.Left}
	} else {
		curLevelSize = 2
		queue = []*TreeNode{root.Left, root.Right}
	}
	nextLevelSize = 0
	result = append(result, []int{root.Val})
	curLevel := make([]int, 0)
	level := 1
	for len(queue) > 0 {
		next := queue[0]
		curLevel = append(curLevel, next.Val)
		if len(queue) > 1 {
			queue = queue[1:]
		} else {
			queue = []*TreeNode{}
		}
		curLevelSize--
		if next.Left != nil {
			queue = append(queue, next.Left)
			nextLevelSize++
		}
		if next.Right != nil {
			queue = append(queue, next.Right)
			nextLevelSize++
		}
		if curLevelSize == 0 {
			curLevelSize = nextLevelSize
			nextLevelSize = 0
			if level%2 == 1 {
				result = append(result, reverse(curLevel))
			} else {
				result = append(result, curLevel)
			}
			curLevel = make([]int, 0)
			level++
		}
	}
	return result
}