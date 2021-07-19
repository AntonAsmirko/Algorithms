class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        val check = HashSet<Int>()
        for(i in nums){
            if(check.contains(i)){
                return true
            }
            check.add(i)
        }
        return false
    }
}