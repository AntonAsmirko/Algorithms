func romanToInt(s string) (result int) {
    for i:= 0; i < len(s); i++{
        switch s[i]{
            case 'I':
            if i != len(s) - 1 && s[i + 1] == 'V'{
                i++
                result += 4
            } else if i != len(s) - 1 && s[i + 1] == 'X'{
                i++
                result += 9
            } else {
                result += 1
            }
            case 'V':
            result += 5
            case 'X':
            if i != len(s) - 1 && s[i + 1] == 'L'{
                i++
                result += 40
            } else if i != len(s) - 1 && s[i + 1] == 'C'{
                i++
                result += 90
            } else {
                result += 10
            }    
            case 'L':
                result += 50
            case 'C':
            if i != len(s) - 1 && s[i + 1] == 'D'{
                i++
                result += 400
            } else if i != len(s) - 1 && s[i + 1] == 'M'{
                i++
                result += 900
            } else {
                result += 100
            }
            case 'D':
            result += 500
            case 'M':
            result += 1000
        }
    }
    return
}