class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        for(i in 0 until m){
            nums1[nums1.size - i - 1] = nums1[m - i - 1] 
        }
        var l1 = 0
        var l2 = nums1.size - m
        var cur = 0
        while(l1 < nums2.size && l2 < nums1.size){
            if(nums1[l2] <= nums2[l1]){
                nums1[cur] = nums1[l2]
                l2++
            } else {
                nums1[cur] = nums2[l1]
                l1++
            }
            cur++
        }
        while(l1 < nums2.size){
            nums1[cur] = nums2[l1]
            l1++
            cur++
        }
        while(l2 < nums1.size){
            nums1[cur] = nums1[l2]
            cur++
            l2++
        }
    }
}
