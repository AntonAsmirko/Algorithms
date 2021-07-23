class Solution {
    fun plusOne(digits: IntArray): IntArray {
        var i = digits.size - 1
        while(i >= 0 && digits[i] == 9){
            digits[i] = 0
            i--
        }
        if(i < 0){
            val res = IntArray(1 + digits.size)
            res[0] = 1
            return res
        }
        digits[i]++
        return digits
    }
}
