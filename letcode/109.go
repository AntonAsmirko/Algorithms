/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func newTreeNode(Val int, Left, Right *TreeNode) (node *TreeNode) {
	node = new(TreeNode)
	node.Val = Val
	node.Left = Left
	node.Right = Right
	return
}

func length(head *ListNode) int {
	lengtht := 0
	for head != nil {
		head = head.Next
		lengtht++
	}
	return lengtht
}

func toArray(head *ListNode) (arr []*TreeNode) {
	arr = make([]*TreeNode, length(head))
	for i := 0; head != nil; i, head = i+1, head.Next {
		arr[i] = newTreeNode(head.Val, nil, nil)
	}
	return
}

func buildTree(arr []*TreeNode, l, r int) *TreeNode {
	if r-l < 1 {
		return nil
	}
	if r-l == 1 {
		return arr[l]
	}
	if r-l == 2 {
		arr[l].Right = arr[l+1]
		return arr[l]
	}
	mid := l + (r-l)/2
	arr[mid].Left = buildTree(arr, l, mid)
	arr[mid].Right = buildTree(arr, mid+1, r)
	return arr[mid]
}

func sortedListToBST(head *ListNode) *TreeNode {
	arr := toArray(head)
	return buildTree(arr, 0, len(arr))
}