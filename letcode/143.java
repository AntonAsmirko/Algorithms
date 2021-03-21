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
    
    private ListNode curLeft;
    private boolean wasBreak;
    
    private void rec(ListNode head){
        if(head.next != null){
            rec(head.next);
        }
        if(wasBreak){
            return;
        }
        if(head == curLeft || curLeft.next == head){
            head.next = null;
            wasBreak = true;
            return;
        }
        ListNode tmp = curLeft.next;
        curLeft.next = head;
        head.next = tmp;
        curLeft = curLeft.next;
        curLeft = curLeft.next;
    }
    
    public void reorderList(ListNode head) {
        this.wasBreak = false;
        this.curLeft = head;
        if(head == null){
            return;
        }
        rec(head);
    }
}