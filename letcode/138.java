/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    
    Node[] newNodes;
    ArrayList<Node> oldNodes;
    
    private boolean equals(Node a, Node b){
        if(a == null || b == null){
            return false;
        }
        return a.val == b.val && a.next == b.next && a.random == b.random;
    }
    
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        Node headTmp = head;
        oldNodes = new ArrayList<>();
        while(headTmp != null){
            oldNodes.add(headTmp);
            headTmp = headTmp.next;
        }
        newNodes = new Node[oldNodes.size()];
        for(int i = newNodes.length - 1; i >= 0; i--){
            newNodes[i] = new Node(oldNodes.get(i).val);
            if(i != newNodes.length -1){
                newNodes[i].next = newNodes[i+1];
            }
        }
        for(int i = 0; i < oldNodes.size(); i++){
            for(int j = 0; j < oldNodes.size(); j++){
                if(equals(oldNodes.get(i).random, oldNodes.get(j))){
                    oldNodes.get(i).val = oldNodes.get(i).val + 10000 | (j << 16);
                }
            }
        }
        for(int i = 0; i < newNodes.length; i++){
            if(oldNodes.get(i).random != null){
                newNodes[i].random = newNodes[oldNodes.get(i).val >> 16];
            }
            oldNodes.get(i).val = (oldNodes.get(i).val & (((Double)Math.pow(2, 16)).intValue() - 1)) - 10000;
        }
        return newNodes[0];
    }
}