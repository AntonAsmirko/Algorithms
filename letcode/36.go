func isValidSudoku(board [][]byte) bool {
    var dummy struct{}
    for i:=0; i < 9; i++{
        mapp := make(map[byte]struct{})
        for j:=0; j < 9; j++{
            if board[i][j] == '.'{
                continue
            }
            if _, found := mapp[board[i][j]]; found{
                return false
            } else {
                mapp[board[i][j]] = dummy
            }
        }
    }
    for j:=0; j < 9; j++{
        mapp := make(map[byte]struct{})
        for i:=0; i < 9; i++{
            if board[i][j] == '.'{
                continue
            }
            if _, found := mapp[board[i][j]]; found{
                return false
            } else {
                mapp[board[i][j]] = dummy
            }
        }
    }
    tmp := [][]int{[]int{0, 0}, []int{0, 3}, []int{0, 6},
                                []int{3, 0}, []int{3, 3}, []int{3, 6},
                              []int{6, 0}, []int{6, 3}, []int{6, 6}}
    for u:=0; u < len(tmp); u++ {
        pair := tmp[u]
        mapp := make(map[byte]struct{})
        for i:= 0; i < 3; i++{
            for j:=0; j <3; j++{
                if board[pair[0] + i][pair[1] + j] == '.'{
                    continue
                }
                if _, found := mapp[board[pair[0] + i][pair[1] + j]]; found{
                    return false
                } else {
                    mapp[board[pair[0] + i][pair[1] + j]] = dummy
                }   
            }
        }
    }
    return true
}