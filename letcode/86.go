func newListNode(Val int, Next *ListNode) (result *ListNode) {
	result = new(ListNode)
	result.Next = Next
	result.Val = Val
	return
}

func removeNext(this *ListNode) (next *ListNode) {
	next = this.Next
	this.Next = next.Next
	next.Next = nil
	return
}

func partition(head *ListNode, x int) *ListNode {
	headTmp := head
	count := 0
	countLess := 0
	for headTmp != nil {
		if headTmp.Val == x {
			count++
		} else if headTmp.Val < x {
			countLess++
		}
		headTmp = headTmp.Next
	}
	dummy := new(ListNode)
	dummy.Next = head
	head = dummy
	headMove := head
	headTmp = head
	countt := count
	wasGreater := false
	for headTmp.Next != nil && (countt > 0 || countLess > 0) {
		if headTmp.Next.Val < x && wasGreater {
			n := removeNext(headTmp)
			tmp := headMove.Next
			headMove.Next = n
			headMove = headMove.Next
			headMove.Next = tmp
			countLess--
		} else if headTmp.Next.Val < x {
			headTmp = headTmp.Next
			headMove = headMove.Next
			countLess--
		} else if headTmp.Next.Val > x && !wasGreater {
			wasGreater = true
		} else {
			wasGreater = true
			headTmp = headTmp.Next
			countt--
		}
	}
	return head.Next
}