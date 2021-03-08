/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func makeTreeNode(Val int, Left, Right *TreeNode) (node *TreeNode) {
	node = new(TreeNode)
	node.Val = Val
	node.Left = Left
	node.Right = Right
	return
}

func makeTrees(n int, startNum int) []*TreeNode {
	if n == 0 {
		return make([]*TreeNode, 0)
	}
	if n == 1 {
		return []*TreeNode{makeTreeNode(startNum, nil, nil)}
	}
	result := make([]*TreeNode, 0)
	until := n + startNum
	for i := startNum; i < until; i++ {
		l, r := makeTrees(i-startNum, startNum), makeTrees(until-i-1, i+1)
		if len(l) == 0 {
			l = append(l, nil)
		}
		if len(r) == 0 {
			r = append(r, nil)
		}
		for _, iteml := range l {
			for _, itemr := range r {
				result = append(result, makeTreeNode(i, iteml, itemr))
			}
		}
	}
	return result
}

func generateTrees(n int) []*TreeNode {
	return makeTrees(n, 1)
}