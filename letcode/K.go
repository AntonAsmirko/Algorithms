var dpArray [][][]bool

func dp(i int, j int, s string) bool {
	if dpArray[i][j][1] {
		return dpArray[i][j][0]
	}
	if j-i == 0 {
		dpArray[i][j][0] = true
		dpArray[i][j][1] = true
		return true
	} else if j-i < 0 {
		dpArray[i][j][0] = false
		dpArray[i][j][1] = true
		return false
	} else if j-i == 1 {
		dpArray[i][j][0] = true
		dpArray[i][j][1] = true
		return true
	}
	var midl bool = false
	if s[i] == s[j-1] {
		midl = dp(i+1, j-1, s)
		if midl {
			dpArray[i][j][0] = true
			dpArray[i][j][1] = true
			return true
		}
	}
	dp(i, j-1, s)
	dp(i+1, j, s)
	dpArray[i][j][0] = false
	dpArray[i][j][1] = true
	return false
}

func longestPalindrome(s string) string {
	dpArray = make([][][]bool, len(s))
	for i := 0; i < len(s); i++ {
		dpArray[i] = make([][]bool, len(s)+1)
	}
	for i := 0; i < len(s); i++ {
		for j := 0; j < len(s)+1; j++ {
			dpArray[i][j] = make([]bool, 2)
			dpArray[i][j][0] = false
			dpArray[i][j][1] = false
		}
	}
	maxI := 0
	maxJ := 0
	dp(0, len(s), s)
	for i := 0; i < len(s); i++ {
		for j := 0; j < len(s)+1; j++ {
			if dpArray[i][j][0] && j-i > maxJ-maxI {
				maxJ = j
				maxI = i
			}
		}
	}
	if maxJ-maxI <= 0 {
		return ""
	}
	buffer := make([]byte, maxJ-maxI)
	for u, v := maxI, 0; u < maxJ; u, v = u+1, v+1 {
		buffer[v] = s[u]
	}
	return string(buffer)
}
