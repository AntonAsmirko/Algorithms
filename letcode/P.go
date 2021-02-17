import "math"

func newListNode(Val int, Next *ListNode) (res *ListNode) {
	res = new(ListNode)
	res.Val = Val
	res.Next = Next
	return
}

type Node struct {
	min    int
	minPos int
	l      int
	r      int
	lCh    *Node
	rCh    *Node
}

func newNode(min int, minPos int, l int, r int, lCh *Node, rCh *Node) (res *Node) {
	res = new(Node)
	res.min = min
	res.minPos = minPos
	res.l = l
	res.r = r
	res.lCh = lCh
	res.rCh = rCh
	return
}

const intMax = 2000000000

func (n *Node) sett(val int, pos int) {
	if n.r-n.l == 1 {
		n.min = val
		return
	}
	mid := n.l + (n.r-n.l)/2
	if pos < mid {
		n.lCh.sett(val, pos)
	} else {
		n.rCh.sett(val, pos)
	}
	if n.lCh.min < n.rCh.min {
		n.min = n.lCh.min
		n.minPos = n.lCh.minPos
	} else {
		n.min = n.rCh.min
		n.minPos = n.rCh.minPos
	}
}

func buildSegmentTree(segmentTree []*Node, pos int, l int, r int) {
	if segmentTree[pos] != nil {
		return
	}
	segmentTree[pos] = newNode(intMax, -1, l, r, nil, nil)
	buildSegmentTree(segmentTree, pos*2+1, l, l+(r-l)/2)
	buildSegmentTree(segmentTree, pos*2+2, l+(r-l)/2, r)
	segmentTree[pos].lCh = segmentTree[pos*2+1]
	segmentTree[pos].rCh = segmentTree[pos*2+2]
	if segmentTree[pos*2+1].min < segmentTree[pos*2+2].min {
		segmentTree[pos].min = segmentTree[pos*2+1].min
		segmentTree[pos].minPos = segmentTree[pos*2+1].minPos
	} else {
		segmentTree[pos].min = segmentTree[pos*2+2].min
		segmentTree[pos].minPos = segmentTree[pos*2+2].minPos
	}
}

func mergeKLists(lists []*ListNode) (result *ListNode) {
	tmp := make([]*ListNode, 0)
	for i := 0; i < len(lists); i++ {
		if lists[i] != nil {
			tmp = append(tmp, lists[i])
		}
	}
	lists = tmp
	if len(lists) == 0 {
		return nil
	}
	logSize := int(math.Log2(float64(len(lists))))
	if 1<<logSize != len(lists) {
		logSize += 1
	}
	segmentTree := make([]*Node, 1<<(logSize+1)-1)
	var startPos = (1 << logSize) - 1
	for i := startPos; i < len(segmentTree); i++ {
		if i-startPos < len(lists) {
			segmentTree[i] = newNode(lists[i-startPos].Val, i-startPos, i, i+1, nil, nil)
		} else {
			segmentTree[i] = newNode(intMax, i-startPos, i, i+1, nil, nil)
		}
	}
	buildSegmentTree(segmentTree, 0, 0, 1<<logSize)
	root := segmentTree[0]
	result = newListNode(lists[root.minPos].Val, nil)
	curListNode := result
	lists[root.minPos] = lists[root.minPos].Next
	if lists[root.minPos] == nil {
		root.sett(intMax, root.minPos)
	} else {
		root.sett(lists[root.minPos].Val, root.minPos)
	}
	for root.min != intMax {
		curListNode.Next = newListNode(lists[root.minPos].Val, nil)
		lists[root.minPos] = lists[root.minPos].Next
		if lists[root.minPos] == nil {
			root.sett(intMax, root.minPos)
		} else {
			root.sett(lists[root.minPos].Val, root.minPos)
		}
		curListNode = curListNode.Next
	}
	return
}