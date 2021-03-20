/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    int r;
    ListNode l;
    boolean wasMiddle;
    
    private void rec(int count, ListNode cur){
        if(count < r){
            rec(count + 1, cur.next);
        }
        if(cur == l || cur.next == l){
            wasMiddle = true;
        }
        if (!wasMiddle){
            int tmp = l.val;
            l.val = cur.val;
            cur.val = tmp;
            l = l.next;
        }
    }
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        wasMiddle = false;
        l = head;
        r = right;
        for(int i = 1; i < left; i++){
            l = l.next;
        }
        rec(left, l);
        return head;
    }
}