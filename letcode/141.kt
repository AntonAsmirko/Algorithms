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
    fun hasCycle(head: ListNode?): Boolean {
        var tmp = head
        while(tmp != null){
            if(tmp.`val` == Int.MAX_VALUE){
                return true
            }
            tmp.`val` = Int.MAX_VALUE
            tmp = tmp.next
        }
        return false
    }
}
