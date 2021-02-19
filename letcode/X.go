/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Neighbors []*Node
 * }
 */

func cloneGraph(node *Node) *Node {
	if node == nil {
		return nil
	}
	newNode := new(Node)
	newNode.Val = node.Val
	newNode.Neighbors = make([]*Node, 0)
	mapp := make([]*Node, 101)
	mapp[newNode.Val] = newNode
	visited := make([]bool, 101)
	q := make([]*Node, 0)
	q = append(q, node)
	visited[newNode.Val] = true
	for len(q) != 0 {
		n := q[0]
		nnn := mapp[n.Val]
		if len(q) == 1 {
			q = make([]*Node, 0)
		} else {
			q = q[1:]
		}
		for i := 0; i < len(n.Neighbors); i++ {
			if !visited[n.Neighbors[i].Val] {
				visited[n.Neighbors[i].Val] = true
				nn := new(Node)
				nn.Val = n.Neighbors[i].Val
				mapp[nn.Val] = nn
				nnn.Neighbors = append(nnn.Neighbors, nn)
				q = append(q, n.Neighbors[i])
			} else {
				nnn.Neighbors = append(nnn.Neighbors, mapp[n.Neighbors[i].Val])
			}
		}
	}
	return newNode
}