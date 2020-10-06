function solution(numbers) {
    var answer = [];
    var temp;

    numbers.sort(function (a, b) {
        return a - b;
    });

    for (var i = 0; i < numbers.length; i++) {
        for (var k = i + 1; k < numbers.length; k++) {
            temp = numbers[i] + numbers[k];

            if (answer.indexOf(temp) === -1) {
                answer.push(temp);
            }
        }
    }
    
    answer.sort(function (a, b) {
        return a - b;
    });
    
    return answer;
}

var input = [2, 1, 3, 4, 1];
//var input = [5,0,2,7];

console.log(solution(input));
