var permutations [][]int

func cloneI(arr []int) (b []int){
    b = make([]int, len(arr))
    copy(b, arr)
    return
}

func cloneB(arr []bool) (b []bool){
    b = make([]bool, len(arr))
    copy(b, arr)
    return
}


func makePerm(indexes, cur []int, was []bool){
    if len(cur) == len(indexes){
        permutations = append(permutations, cloneI(cur))
        return
    }
    for _, index := range indexes{
        if !was[index]{
            nw := cloneB(was)
            nw[index] = true
            makePerm(indexes, append(cloneI(cur), index), nw)
        }
    }
}

func permute(nums []int) [][]int {
    permutations = make([][]int, 0)
    indexes := make([]int, len(nums))
    for i:=0; i < len(nums); i++{
        indexes[i] = i
    }
    makePerm(indexes, make([]int, 0), make([]bool, len(indexes)))
    res := make([][]int, 0)
    for _, iPerm := range permutations{
        resPerm := make([]int, len(iPerm))
        for i:=0; i < len(iPerm);i++{
            resPerm[i] = nums[iPerm[i]]
        }
        res = append(res, resPerm)
    }
    return res
}