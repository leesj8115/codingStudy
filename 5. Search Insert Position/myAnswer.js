var searchInsert = function(nums, target) {
    var i = nums.indexOf(target);       // indexOf 함수로 해당 값이 있는지 확인
    if (i !== -1)
        return i;       // 있다면 바로 반환

    else if (target < nums[0])
        return 0;       // 처음 값보다 작다면 첫 위치 반환

    else if (target > nums[nums.length-1])
        return nums.length;     // 가장 큰 값보다 크다면 문자열 길이 (맨 끝 위치) 반환


    // target이 사이값이라면, 대소를 비교
    for (i = 0; i < nums.length-1; i++) {
        if ((nums[i] < target) && (nums[i+1] > target))
            return i+1;
    }

};
