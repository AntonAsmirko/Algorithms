import "strings"

type Stack struct {
	Size int
	Data []byte
}

func (s *Stack) push(b byte) {
	if len(s.Data) == s.Size {
		s.Data = append(s.Data, b)
	} else {
		s.Data[s.Size] = b
	}
	s.Size++
}

func (s *Stack) pop() (result byte) {
	if s.Size <= 0 {
		return '-'
	}
	result = s.Data[s.Size-1]
	s.Size = s.Size - 1
	return
}

func isValid(s string) bool {
	var st Stack = Stack{
		Size: 0,
		Data: make([]byte, 0)}
	for i := 0; i < len(s); i++ {
		if s[i] == ')' && st.pop() != '(' || s[i] == '}' && st.pop() != '{' || s[i] == ']' && st.pop() != '[' {
			return false
		} else if s[i] == '(' || s[i] == '{' || s[i] == '[' {
			st.push(s[i])
		}
	}
	return st.Size == 0
}

var result []string

func makeAll(open int, closed int, str string) {
	if open < 0 || closed < 0 {
		return
	}
	if open == 0 && closed == 0 && isValid(str) {
		result = append(result, str)
		return
	}
	makeAll(open-1, closed, str+"(")
	makeAll(open, closed-1, str+")")
}

func generateParenthesis(n int) []string {
	result = make([]string, 0)
	makeAll(n, n, "")
	return result
}