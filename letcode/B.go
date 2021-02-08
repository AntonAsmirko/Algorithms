func lengthOfLongestSubstring(s string) int {
	if len(s) == 0 {
		return 0
	}
	var charSet map[byte]bool = make(map[byte]bool)
	var maxLen int = 1
	var curLen int = 1
	var (
		i int = 0
		j int = 0
	)

	charSet[s[0]] = true

	for i+1 < len(s) {
		i++
		_, ok := charSet[s[i]]
		if !ok {
			curLen++
			if maxLen < curLen {
				maxLen = curLen
			}
		} else {
			it := j
			for s[it] != s[i] {
				delete(charSet, s[it])
				it++
			}
			delete(charSet, s[it])
			j = it + 1
			curLen = i - j + 1
		}
		charSet[s[i]] = true
	}
	return maxLen
}