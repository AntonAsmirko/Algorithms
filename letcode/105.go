/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var (
	Preorder []int
	Inorder  []int
)

func makeTreeNode(Val int, Left, Right *TreeNode) (result *TreeNode) {
	result = new(TreeNode)
	result.Val, result.Left, result.Right = Val, Left, Right
	return
}

func find(val int, arr []int) int {
	for index, i := range arr {
		if i == val {
			return index
		}
	}
	return -1
}

func construct(preI, l, r int) *TreeNode {
	if preI == -1 {
		return nil
	}
	if r-l <= 1 {
		return makeTreeNode(Preorder[preI], nil, nil)
	}
	inI := find(Preorder[preI], Inorder)
	var dummy struct{}
	rNums := make(map[int]struct{})
	for i := inI + 1; i < r; i++ {
		rNums[Inorder[i]] = dummy
	}
	rIndex := -1
	for i := preI + 1; i < len(Preorder); i++ {
		if _, ok := rNums[Preorder[i]]; ok {
			rIndex = i
			break
		}
	}
	lNums := make(map[int]struct{})
	for i := l; i < inI; i++ {
		lNums[Inorder[i]] = dummy
	}
	lIndex := -1
	for i := preI + 1; i < len(Preorder); i++ {
		if _, ok := lNums[Preorder[i]]; ok {
			lIndex = i
			break
		}
	}
	return makeTreeNode(Preorder[preI], construct(lIndex, l, inI), construct(rIndex, inI+1, r))
}

func buildTree(preorder []int, inorder []int) *TreeNode {
	Preorder = preorder
	Inorder = inorder
	return construct(0, 0, len(preorder))
}