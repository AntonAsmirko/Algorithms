class Solution {
    fun sortedSquares(nums: IntArray): IntArray {
        val res = IntArray(nums.size)
        val negatives = arrayListOf<Int>()
        val positives = arrayListOf<Int>()
        for(i in nums){
            if(i < 0){
                negatives.add(i)
            } else {
                positives.add(i)
            }
        }
        for(i in negatives.indices){
            negatives[i] *= negatives[i]
        }
        for(i in positives.indices){
            positives[i] *= positives[i]
        }
        var pL = 0
        var nR = negatives.size - 1
        var cur = 0
        while(pL < positives.size && nR >= 0){
            if(negatives[nR] < positives[pL]){
                res[cur] = negatives[nR--]
            } else {
                res[cur] = positives[pL++]
            }
            cur++
        }
        while(pL < positives.size){
            res[cur++] = positives[pL++]
        }
        while(nR >= 0){
            res[cur++] = negatives[nR--]
        }
        return res
    }
}
