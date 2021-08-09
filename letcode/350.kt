class Solution {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val map = nums1.map{ it to 1 }.groupBy{ it.first }.map{ it.key to Dummy(it.value.size) }.toMap()
        val res = mutableListOf<Int>()
        nums2.forEach{
            map[it]?.let{ dummy ->
                val `val` = dummy.num
                if(`val` > 0){
                    res.add(it)
                    map[it]?.num = `val` - 1
                }
            }
        }
        return res.toIntArray()
    }
}

class Dummy(var num: Int)
