/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

func length(l *ListNode) (leng int) {
	if l == nil {
		leng = 0
		return
	}
	leng = 1
	for l.Next != nil {
		leng++
		l = l.Next
	}
	return
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	l := length(head)
	k := l - n
	if k == 0 {
		return head.Next
	}
	cur := 1
	curNode := head
	for cur != k {
		curNode = curNode.Next
		cur++
	}
	if cur == l-1 {
		curNode.Next = nil
	} else {
		curNode.Next = curNode.Next.Next
	}
	return head
}