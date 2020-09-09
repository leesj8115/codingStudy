function solution(board, moves) {
    var answer = 0;
    var index;
    var temp = [];

    for (var i = 0; i < moves.length; i++) {
        index = moves[i] - 1;

        for (var k = 0; k < board.length; k++) {
            if (board[k][index] !== 0) {
                temp.push(board[k][index]);
                board[k][index] = 0;
                break;
            }
        }
        
    }

    console.log("temp = " + temp);

    for (var i = 0; i< temp.length-1; i++) {
        if (temp[i] === temp[i+1]) {
                console.log("i = " + i);
                console.log("temp[i] = " + temp[i]);
                console.log("temp[i+1] = " + temp[i+1]);
                answer+=2;

                temp.splice(i, 2);

                console.log("found");
                console.log("temp = " + temp);

                i = -1;
        }
    }
    return answer;
}

var b = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]];
var m = [1,5,3,5,1,2,1,4];

console.log(solution(b, m));
