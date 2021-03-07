var result []int

func printDfs(root *TreeNode) {
	if root == nil {
		return
	}
	printDfs(root.Left)
	result = append(result, root.Val)
	printDfs(root.Right)
}

func inorderTraversal(root *TreeNode) []int {
	result = make([]int, 0)
	printDfs(root)
	return result

}