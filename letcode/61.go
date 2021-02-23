/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

 func lenList(head *ListNode) (leng int){
    for head != nil{
        leng++
        head = head.Next
    }
    return
}

func rotateRight(head *ListNode, k int) *ListNode {
    heads := head
    curPos := 0
    leng := lenList(head)
    if leng == 0{
        return nil
    }
    if k == 0  || k % leng == 0{
        return head
    }
    if leng == k {
        return head
    }
    k = k % leng
    if leng == 1 {
        return head
    }
    for ;curPos != leng - k; curPos++{
        heads = heads.Next
    }
    headsTmp := heads
    for headsTmp.Next != nil{
        headsTmp = headsTmp.Next
    }
    headsT := head
    for curPos = 0; curPos < leng - k - 1; curPos++{
        headsT = headsT.Next
    }
    headsT.Next = nil
    headsTmp.Next = head
    return heads
}