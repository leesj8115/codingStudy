/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function(nums, val) {
  let index = 0;
  while(true) {
    index = nums.indexOf(val, index);
    console.log("index = " + index);
    if (index == -1) {
      break;
    }
    else {
      nums.splice(index, 1);
    }

    console.log("nums = " + nums);
  }

  return nums.length;
};

let Enums = [3, 2, 2, 3];
let Eval = 3;
removeElement(Enums, Eval);
