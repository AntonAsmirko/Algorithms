class Solution {
    
    var count = 0
    
    fun numIslands(grid: Array<CharArray>): Int {
        count = 0
        val visited = Array(grid.size){Array(grid[0].size){false}}
        for(i in grid.indices){
            for(j in grid[i].indices){
                check(i, j, visited, grid)
            }
        }
        return count
    }
    
    fun check(i: Int, j:Int, visited: Array<Array<Boolean>>, grid: Array<CharArray>){
        if(visited[i][j] || grid[i][j] == '0'){
            return
        }
        count++
        mark(i, j, visited, grid)
    }
    
    fun mark(i: Int, j:Int, visited: Array<Array<Boolean>>, grid: Array<CharArray>){
        visited[i][j] = true
        if(i > 0 && grid[i - 1][j] == '1' && !visited[i - 1][j]){
            mark(i - 1, j, visited, grid)
        }
        if(j > 0 && grid[i][j - 1] == '1' && !visited[i][j - 1]){
            mark(i, j - 1, visited, grid)
        }
        if(i < visited.size - 1 && grid[i + 1][j] == '1' && !visited[i + 1][j]){
            mark(i + 1, j, visited, grid)
        }
        if(j < visited[0].size - 1 && grid[i][j + 1] == '1' && !visited[i][j + 1]){
            mark(i, j + 1, visited, grid)
        }
    }
}
