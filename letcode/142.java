/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    
    private static final int CYCLE_FOUND = 10001;
    
    private int rec(ListNode head, int count){
        if(head == null){
            return -1;
        }
        if (head.val > 1000000000){
            head.val -= 2000000000;
            return CYCLE_FOUND;
        }
        head.val += 2000000000;
        int mayBeRes = rec(head.next, count + 1);
        if (mayBeRes == -1){
            return -1;
        }
        if(mayBeRes == CYCLE_FOUND && head.val < 1000000000){
            return count;
        }
        head.val -= 2000000000;
        return mayBeRes;
        
    }
        
    public ListNode detectCycle(ListNode head) {
        int pos = rec(head, 0);
        if(pos == -1){
            return null;
        }
        for(int i = 0; i < pos; i++){
            head = head.next;
        }
        return head;
    }
}