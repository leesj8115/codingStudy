import java.util.Arrays;

public class Solution {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;                   // 리스트가 비었다면 작업 종료
        }

        int count = 0;                  // 중복 원소를 제외한 갯수
        int lastElement = nums[0]-1;    // 중복이 아닌 값. 처음에는 초기값으로 num[0]-1 로 하여, 원소와 겹치지 않도록 함

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != lastElement) {
                // 현재 위치와 탐색중인 원소가 다를 경우
                lastElement = nums[i];      // lastElement 갱신
                nums[count] = lastElement;  // 앞에서부터 중복 원소를 제외하고 정렬되도록 nums[count] 입력
                count++;                    // 중복 아닌 원소 갯수 +1
            }
        }

        nums = Arrays.copyOf(nums, count);  // new int[count] 크기로 재할당, count 이후의 불필요 메모리는 제거

        return nums.length;
    }
}

