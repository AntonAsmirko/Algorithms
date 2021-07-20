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
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null){
            return l2
        }
        if (l2 == null){
            return l1
        }
        var f1: ListNode? = null
        var f2: ListNode? = null
        if(l1!!.`val` < l2!!.`val`){
            f1 = l1
            f2 = l2
        } else {
            f2 = l1
            f1 = l2
        }
        var l3 = ListNode(f1!!.`val`)
        var l33 = l3
        f1 = f1!!.next
        while(f1 != null && f2 != null){
            if(f1!!.`val` < f2!!.`val`){
                l33!!.next = ListNode(f1!!.`val`)
                f1 = f1!!.next
            } else {
                l33!!.next = ListNode(f2!!.`val`)
                f2 = f2!!.next
            }
            l33 = l33!!.next
        }
        if(f1 != null){
            l33!!.next = f1
        }
        if(f2 != null){
            l33!!.next = f2
        }
        return l3
    }
}