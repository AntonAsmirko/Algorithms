class Solution {
    fun generate(numRows: Int): List<List<Int>> {
        val result = mutableListOf(arrayListOf(1))
        if(numRows == 1){
            return result
        }
        for(i in 2..numRows){
            result.add(arrayListOf(1).apply{
                var prev = 1
                var wasSkip = false
                for(item in result.last()){
                    if(!wasSkip){
                        wasSkip = true
                        continue
                    }
                    add(prev + item)
                    prev = item
                }
                add(1)
            })
        }
        return result
    }
}
