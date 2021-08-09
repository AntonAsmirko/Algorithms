import java.util.LinkedList

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
    
    val ll = LinkedList<Int>()
    
    var hm = true
    
    fun isPalindrome(head: ListNode?): Boolean {
        hm = true
        check(head)
        return hm
    }
    
    fun check(node: ListNode?){
        node?.let{
            ll.add(it.`val`)
            check(it.next)
            if(it.`val` != ll.removeFirst()) hm = false
        }
    }
}
