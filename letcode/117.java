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
        int curLevelSize = 1;
        q.add(root);
        int countOnLevel = 0;
        Node prevOnLevel = null;
        int nextLevelCount = 0;
        while(!q.isEmpty()){
            Node cur = q.removeFirst();
            countOnLevel += 1;
            if(prevOnLevel != null){
                prevOnLevel.next = cur;
            }
            if(cur.left != null){
                q.add(cur.left);
                nextLevelCount++;
            }
            if (cur.right != null){
                q.add(cur.right);
                nextLevelCount++;
            }
            prevOnLevel = cur;
            if(countOnLevel == curLevelSize){
                countOnLevel = 0;
                curLevelSize = nextLevelCount;
                nextLevelCount = 0;
                prevOnLevel = null;
            }
        }
        return root;
    }
}