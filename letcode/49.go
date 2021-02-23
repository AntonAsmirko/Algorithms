import "sort"
import "math"

type pair struct{
    str string
    byteRep []byte
}

func groupAnagrams(strs []string) [][]string {
    pairs := make([]*pair, len(strs))
    for i:=0; i< len(strs);i++{
        pairs[i] = new(pair)
        pairs[i].str = strs[i]
        l := len(strs[i])
        br := make([]byte, l)
        for j:=0; j< l; j++{
            br[j] = strs[i][j]
        }
        sort.SliceStable(br, func(i, j int) bool {
            return br[i] < br[j]
        })
        pairs[i].byteRep = br
    }
    
    cmp := func(F, S *pair) bool{
        f, s := F.byteRep, S.byteRep
        for u := 0; u < int(math.Min(float64(len(f)), float64(len(s)))); u++{
            if f[u] < s[u]{
                return true
            } else if f[u] > s[u]{
                return false
            }
        }
        if len(f) < len(s){
            return true
        } else {
            return false
        }
    }
    
    sort.SliceStable(pairs, func(i, j int) bool{
        return cmp(pairs[i], pairs[j])
    })
    groups := [][]*pair{[]*pair{pairs[0]}}
    for i:= 1; i < len(pairs); i++{
        if !cmp(pairs[i], groups[len(groups) - 1][len(groups[len(groups) - 1]) - 1]) && !cmp(groups[len(groups) - 1][len(groups[len(groups) - 1]) - 1], pairs[i]){
            groups[len(groups) - 1] = append(groups[len(groups) - 1], pairs[i])
        } else {
            groups = append(groups, []*pair{pairs[i]})
        }
    }
    res := make([][]string, 0)
    for i:= 0; i < len(groups); i++{
        group := make([]string, len(groups[i]))
        for j := 0; j < len(groups[i]); j++{
            group[j] = groups[i][j].str
        }
        if len(group) != 0{
            res = append(res, group)
        }
    }
    return res
}