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
    
    fun reverseList(head: ListNode?): ListNode? {
        dfs(head)
        return head
    }
    
    fun dfs(node: ListNode?){
        node?.let{
            ll.add(it.`val`)
            dfs(it.next)
            it.`val` = ll.removeFirst()
        }
    }
}
