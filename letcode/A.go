/*
type ListNode struct {
    Val int
    Next *ListNode
}
*/

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {

	var curL1 *ListNode = l1
	var curL2 *ListNode = l2

	var ans *ListNode = new(ListNode)

	var carriage int = ((*curL1).Val + (*curL2).Val) / 10

	var curAns *ListNode = ans

	(*curAns).Val = ((*curL1).Val + (*curL2).Val) % 10

	curL1 = (*curL1).Next
	curL2 = (*curL2).Next

	for curL1 != nil && curL2 != nil {
		var newCurAns *ListNode = new(ListNode)

		(*newCurAns).Val = ((*curL1).Val + (*curL2).Val + carriage) % 10
		carriage = ((*curL1).Val + (*curL2).Val + carriage) / 10

		(*curAns).Next = newCurAns
		curAns = newCurAns

		curL1 = (*curL1).Next
		curL2 = (*curL2).Next
	}

	for curL1 != nil {
		var newCurAns *ListNode = new(ListNode)

		(*newCurAns).Val = ((*curL1).Val + carriage) % 10
		carriage = ((*curL1).Val + carriage) / 10

		(*curAns).Next = newCurAns
		curAns = newCurAns

		curL1 = (*curL1).Next
	}

	for curL2 != nil {
		var newCurAns *ListNode = new(ListNode)

		(*newCurAns).Val = ((*curL2).Val + carriage) % 10
		carriage = ((*curL2).Val + carriage) / 10

		(*curAns).Next = newCurAns
		curAns = newCurAns

		curL2 = (*curL2).Next
	}

	if carriage != 0 && curL1 == nil && curL2 == nil {
		var end *ListNode = new(ListNode)
		(*end).Val = 1
		(*curAns).Next = end
	}

	/*
	   fmt.Print("[")
	   fmt.Print((*ans).Val)
	   ans = (*ans).Next

	   for ans != nil {
	       fmt.Printf(", %v", (*ans).Val)
	       ans = (*ans).Next
	   } */

	return ans
}