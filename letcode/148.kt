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
    fun sortList(head: ListNode?): ListNode? {
        if(head == null){
            return head
        }
        var len = 0
        var tmpHead = head
        while(tmpHead != null){
            len++
            tmpHead = tmpHead.next
        }
        return rec(head, len)
    }
    
    fun rec(head: ListNode?, len: Int): ListNode?{
        var head = head
        if(len == 1){
            return head
        }
        val hLen = len / 2
        var f = head;
        var cnt = 0
        while(cnt != hLen - 1){
            cnt++
            head = head!!.next
        }
        var s = head!!.next
        head!!.next = null
        val recF = rec(f, cnt + 1)
        val recS = rec(s, len - cnt -1)
        return merge(recF, recS)
    }
    
    fun merge(f: ListNode?, s: ListNode?): ListNode?{
        var res: ListNode? = null
        var f = f
        var s = s
        if(f == null){
            return s
        }
        if(s == null){
            return f
        }
        if(f.`val` < s.`val`){
            res = f
            f = f!!.next
        } else {
            res = s
            s = s!!.next
        }
        res!!.next = null
        var resTmp = res
        while(f != null && s != null){
            if(f!!.`val` < s!!.`val`){
                resTmp!!.next = f
                f = f!!.next
                resTmp = resTmp!!.next
                resTmp!!.next = null
            } else {
                resTmp!!.next = s
                s = s!!.next
                resTmp = resTmp!!.next
                resTmp!!.next = null
            }
        }
        if(f != null){
            resTmp!!.next = f
        }
        if(s != null){
            resTmp!!.next = s
        }
        return res
    }
}