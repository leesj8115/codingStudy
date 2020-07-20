/**
 * @param {number[]} nums
 * @return {number}
 */

var removeDuplicates = function(nums) {
  var count = 0;                    // 순환한 갯수
  var max = nums[nums.length-1];    // 최대값 = 마지막 원소
  var min = nums[0];                // 최소값 = 처음의 원소

  while (count !== nums.length) {
    // 첫 원소와 다음 원소가 값이 같은지 확인

    if (nums[0] === nums[1]) {
      // 같다면 
      nums.shift(); // 중복 원소 삭제
      count = 0;    // 중복 원소를 찾았으므로 count를 초기화
    }

    else {
      // 다르다면
      nums.push(nums[0]);  // 중복이 아닌 원소이므로 push를 통해 보존
      nums.shift();        // 중복 원소 삭제
      count++;             // 반복 작업 횟수++
    }
  }

  while((min !== nums[0]) || (max !== nums[nums.length-1])) {
    // 다시 정렬된 배열이 되도록 Shift
    nums.push(nums[0]);
    nums.shift();
  }

  return nums.length;
};

var nums = [0,0,1,1,1,2,2,3,3,4];
//var nums = [-3,-1,0,0,0,3,3];
removeDuplicates(nums);
