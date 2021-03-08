/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

type Pair struct {
	Level int
	Item  *Node
}

func levelOrder(root *Node) (result [][]int) {
	result = make([][]int, 0)
	if root == nil {
		return
	}
	q := make([]Pair, 0)
	q = append(q, Pair{0, root})
	res := make([]Pair, 0)
	for len(q) > 0 {
		item := q[0]
		if len(q) > 1 {
			q = q[1:]
		} else {
			q = []Pair{}
		}
		for _, child := range item.Item.Children {
			if child != nil {
				q = append(q, Pair{item.Level + 1, child})
			}
		}
		res = append(res, item)
	}
	if len(res) == 0 {
		return
	}
	result = append(result, []int{res[0].Item.Val})
	for i := 1; i < len(res); i++ {
		if res[i-1].Level != res[i].Level {
			result = append(result, []int{res[i].Item.Val})
		} else {
			result[len(result)-1] = append(result[len(result)-1], res[i].Item.Val)
		}
	}
	return result
}