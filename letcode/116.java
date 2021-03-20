/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        LinkedList<Node> q = new LinkedList<>();
        Double currentLevel = 0.0;
        q.add(root);
        Double countOnLevel = 0.0;
        Node prevOnLevel = null;
        while(!q.isEmpty()){
            Node cur = q.removeFirst();
            countOnLevel += 1.0;
            if(prevOnLevel != null){
                prevOnLevel.next = cur;
            }
            if(cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
            prevOnLevel = cur;
            if(countOnLevel.equals(Math.pow(2.0, currentLevel))){
                countOnLevel = 0.0;
                currentLevel += 1.0;
                prevOnLevel = null;
            }
        }
        return root;
    }
}