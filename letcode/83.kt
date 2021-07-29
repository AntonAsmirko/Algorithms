/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if(head == null){
            return head
        }
        val tmpHead = head
        var cursor = head
        var prev = cursor
        cursor = cursor.next
        while(cursor != null){
            if(cursor!!.`val` != prev!!.`val`){
                prev!!.next = cursor
                prev = cursor
            }
            cursor = cursor.next
        }
        prev!!.next = null
        return tmpHead
    }
}
