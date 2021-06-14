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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newList = new ListNode(-101, head);
        ListNode res = newList;
        ListNode prev = null;
        boolean mark = false;
        while(newList.next != null){
            if(newList.next.val == newList.val){
                newList.next = newList.next.next;
                mark = true;
            } else if (mark){
                prev.next = newList.next;
                newList = newList.next;
                mark = false;
            } else {
                prev = newList;
                newList = newList.next;
            }
        }
        if(mark){
            prev.next = null;
        }
        return res.next;
    }
}