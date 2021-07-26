class Solution {
    func numOfMinutes(_ n: Int, _ headID: Int, _ manager: [Int], _ informTime: [Int]) -> Int {
        var hashManagers: [Int:[Int]] = [:]
        for index in 0..<manager.count {
            let item = manager[index]
            if index != headID {
                if let ifContains = hashManagers[item] {
                    hashManagers[item]!.append(index)
                } else {
                    hashManagers[item] = [index]
                }
            }
        }
        return dfs(node: headID, tree: hashManagers, sum: 0, informTime: informTime)
    }
    
    func dfs(node: Int, tree: [Int:[Int]], sum:Int, informTime: [Int])-> Int {
        if let curEmploes = tree[node]{
            var maxSum = 0
            for emploe in tree[node]! {
                let time = dfs(node :emploe,tree: tree,sum: sum + informTime[node],informTime: informTime)
                if time > maxSum{
                    maxSum = time
                }
            }
            return maxSum
        } else {
            return sum
        }
    }
}
