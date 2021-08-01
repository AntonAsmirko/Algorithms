"use strict";
const dataSize = 5;

const runProgram = (program, input) => {
    let pos = 0;
    let inputPos = 0;
    const data = new Array(dataSize).fill(0)

    for (let i = 0; i < program.length; i++) {
        switch (program[i]) {
            case '>':
                pos++;
                break;
            case '<': a
                pos--;
                if (pos < 0) {
                    throw new Error("data index out from bounds")
                }
                break;
            case '+':
                data[pos]++;
                if (pos >= dataSize) {
                    throw new Error("data index out from bounds")
                }
                break;
            case '-':
                data[pos]--;
                break;
            case '.':
                console.log(data[pos]);
                break;
            case ',':
                data[pos] = input[inputPos];
                inputPos++;
                break;
            case '[':
                if (data[pos] === 0) {
                    i = nextRectBracketPos(program, i);
                }
                break;
            case ']':
                if (data[pos] !== 0) {
                    i = prevRectBracketPos(program, i) - 1;
                }
                break;
        }
    }
}

const nextRectBracketPos = (program, pos) => {
    let i = pos + 1;
    let closeOpenDiff = 1;
    for (; i < program.length; i++) {
        if (program[i] === '[') closeOpenDiff++;
        if (program[i] === ']') closeOpenDiff--;
        if (program[i] === ']' && closeOpenDiff === 0) {
            break;
        }
    }
    if (i >= program.length) {
        throw new Error("wrong bracket sequence!!!");
    }
    return i;
}

const prevRectBracketPos = (program, pos) => {
    let i = pos - 1;
    let closeOpenDiff = 1
    for (; i >= 0; i--) {
        if (program[i] === '[') closeOpenDiff--;
        if (program[i] === ']') closeOpenDiff++;
        if (program[i] === '[' && closeOpenDiff === 0) {
            break;
        }
    }

    if (i < 0) {
        throw new Error("wrong bracket sequence!!!");
    }
    return i;
}

runProgram("++[++.]", "1001");