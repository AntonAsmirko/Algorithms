class Solution {
    fun invalidTransactions(transactions: Array<String>): List<String> {
        val res = mutableListOf<String>()
        if(transactions.isEmpty()){
            return res
        }
        val wasAdded = hashSetOf<Int>()
        var splited = transactions.map{it.split(",").filter{ it.isNotEmpty() }}.sortedBy{it[0]}.sortedBy{it[1].toInt()}
        if(splited[0][2].toInt() > 1000){
            res.add(splited[0].joinToString(separator = ","))
            wasAdded.add(0)
        }
        for(i in 1 until splited.size){
            var j = 1
            while(i - j >= 0 && splited[i][1].toInt() - splited[i - j][1].toInt() <= 60){
                if(splited[i][0] == splited[i - j][0] && splited[i][3] != splited[i - j][3]){
                    if(!wasAdded.contains(i - j)){
                        wasAdded.add(i - j)
                        res.add(splited[i - j].joinToString(separator = ","))
                    }
                    if(!wasAdded.contains(i)){
                        res.add(splited[i].joinToString(separator = ","))
                        wasAdded.add(i)
                    }
                }
                j++
            }
            if(splited[i][2].toInt() > 1000 && !wasAdded.contains(i)){
                res.add(splited[i].joinToString(separator = ","))
                wasAdded.add(i)
            }
        }
        return res
    }
}
