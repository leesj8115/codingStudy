/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function(grid) {
    var result = 0;             // 결과 갯수
    var m = grid.length;     // rows 원소
    var n = grid[0].length;  // cols원소 갯수

    // 가장 기본적인 방법으로 탐색
    for (var i = 0; i < m; i++) {
        for (var k = 0; k < n; k++) {
            // 한 줄씩 비교하여, cols의 원소가 음수라면 이후 나올 cols도 음수로 판단
            if (grid[i][k] < 0) {
                result += n - k;
                break;
            }
        }
    }

    return result;
};

var input = [[4,3,2,-1], [3,2,1,-1], [1,1,-1,-2], [-1,-1,-2,-3]];
console.log("result = " + countNegatives(input)); 
