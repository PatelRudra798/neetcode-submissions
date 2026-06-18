class Solution {
    public int[] productExceptSelf(int[] nums) {
        int numsTotal = 1;
        int[] output = new int[nums.length];

        //Get multiplication total from nums
        for(int i = 0; i < nums.length; i++){
            numsTotal *= nums[i];
        }

        //If numsTotal doesn't equal 0, set default values to equal numsTotal
        if(numsTotal != 0){
            for(int i = 0; i < output.length; i++){
                output[i] = numsTotal;
            }
        }
        
        if(numsTotal != 0){
            for(int i = 0; i < output.length; i++){
                output[i] /= nums[i];
            }
        }else{
            int zeroPosition = 0;
            int numsExcludeZero = 1;
            while(nums[zeroPosition] != 0){ zeroPosition++; }

            for(int i = 0; i < output.length; i++){
                if(i == zeroPosition) continue;
                numsExcludeZero *= nums[i];
            }
            output[zeroPosition] = numsExcludeZero;
        }
        
        return output;
    }
}  
