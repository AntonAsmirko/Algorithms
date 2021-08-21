class Solution {
    fun simplifyPath(path: String): String {
        return LinkedList<Char>().apply{
            for(ch in path){
                add(ch)
                if(size >= 4){
                    val f = removeLast()
                    val s = removeLast()
                    val t = removeLast()
                    if(f != '/' || s != '.' || t != '.' || last() != '/'){
                        add(t)
                        add(s)
                        add(f)
                    } else if(size > 1){
                        removeLast()
                        while(size > 1 && last() != '/'){
                            removeLast()
                        }
                    }
                }
                if(size >= 3){
                    val f = removeLast()
                    val s = removeLast()
                    if(f != '/' || s != '.' || last() != '/'){
                        add(s)
                        add(f)
                    }
                }
                if(size >= 2){
                    val f = removeLast()
                    if(f != '/' || last() != '/'){
                        add(f)
                    }
                }
            }
            if(size >= 2){
                val f = removeLast()
                if(last() != '/' || f != '.'){
                    add(f)
                }
            }
            if(size >= 3){
                val f = removeLast()
                val s = removeLast()
                if(last() != '/' || s != '.' || f != '.'){
                    add(s)
                    add(f)
                } else if(size > 1){
                    removeLast()
                    while(size > 1 && last() != '/' ){
                        removeLast()
                    }
                }
            }
            if(size > 1 && last() == '/'){
                removeLast()
            }
        }.joinToString(separator = "")
    }
}
