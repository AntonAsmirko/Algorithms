class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
       return LinkedList<Int>().apply{
           add(asteroids[0])
           outer@for(i in 1 until asteroids.size){
               if(isEmpty() || isSameSign(last(), asteroids[i])){
                   add(asteroids[i])
                   continue
               } else {
                    while(isNotEmpty() && isCollision(last(), asteroids[i])){
                        if(Math.abs(last()) > Math.abs(asteroids[i])){
                            continue@outer
                        }
                        if(Math.abs(last()) == Math.abs(asteroids[i])){
                            removeLast()
                            continue@outer
                        }
                        removeLast()
                    }
                    add(asteroids[i])   
               }
           }
       }.toIntArray()
    }
    
    fun isSameSign(f: Int, s: Int): Boolean = f < 0 && s < 0 || f > 0 && s > 0
    
    fun isCollision(f: Int, s: Int): Boolean = f > 0 && s < 0
}
