class Solution {
    fun deleteAndEarn(nums: IntArray): Int {
        val grouped = group(nums)
        val maxEarned = IntArray(grouped.size)
        maxEarned[0] = grouped[0].run {
            this.isTaken = true
            this.getSum()
        }
        val lastTwo = intArrayOf(-1, maxEarned[0])
        val addLastTwo = {num: Int
            -> when{
            lastTwo[1] < 0 -> lastTwo[1] = num
            else -> { lastTwo[0] = lastTwo[1]; lastTwo[1] = num }
        }
        }
        val take = { i:Int
            -> maxEarned[i] = lastTwo[1] + grouped[i]
            .run {
                this.isTaken = true
                this.getSum()
            }
        }
        for(i in 1 until grouped.size){
            if(grouped[i].first - grouped[i-1].first > 1 || !grouped[i - 1].isTaken){
                take(i)
                addLastTwo(maxEarned[i])
            } else {
                if(grouped[i - 1].isTaken
                    && maxEarned[i - 1] < maxEarned[i - 1] + grouped[i].getSum() - grouped[i - 1].getSum()
                    && lastTwo[0] + grouped[i].getSum() < maxEarned[i - 1] + grouped[i].getSum() - grouped[i - 1].getSum()){
                    grouped[i - 1].isTaken = false
                    take(i)
                    maxEarned[i] -= grouped[i - 1].getSum()
                    addLastTwo(maxEarned[i])
                } else if(grouped[i - 1].isTaken
                    && lastTwo[0] + grouped[i].getSum() > maxEarned[i - 1]
                    && lastTwo[0] + grouped[i].getSum() >= maxEarned[i - 1] + grouped[i].getSum() - grouped[i - 1].getSum()){
                    maxEarned[i] = lastTwo[0] + grouped[i].getSum()
                    grouped[i].isTaken = true
                    grouped[i - 1].isTaken = false
                    addLastTwo(maxEarned[i])
                } else if(!grouped[i - 1].isTaken && maxEarned[i - 1] < maxEarned[i - 1] + grouped[i].getSum()) {
                    take(i)
                    addLastTwo(maxEarned[i])
                }
            }
        }
        return maxEarned.max()!!
    }

    fun group(nums: IntArray): ArrayList<MyPair>{
        val numsSorted = nums.sorted()
        val result = arrayListOf<MyPair>()
        for(num in numsSorted){
            if(result.isEmpty() || result[result.size - 1].first != num){
                result.add(MyPair(num, 1))
            } else {
                result[result.size - 1].second++
            }
        }
        return result
    }

    class MyPair(var first:Int, var second: Int) {
        fun getSum() = this.first * this.second
        var isTaken = false
    }
}
