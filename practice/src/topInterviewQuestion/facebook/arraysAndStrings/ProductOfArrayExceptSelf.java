package topInterviewQuestion.facebook.arraysAndStrings;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3016/
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        //Output: [24,12,8,6]
        var obj = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(obj.productExceptSelf(nums)));
    }

    //Approach 1: Left and Right product lists
    //Time: O(N)
    //Space: O(N)
    public int[] productExceptSelf(int[] nums) {
        // The length of the input array
        int length = nums.length;
        // The left and right arrays as described in the algorithm
        int[] L = new int[length];
        int[] R = new int[length];
        // Final answer array to be returned
        int[] answer = new int[length];
        // L[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so L[0] would be 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            // L[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            L[i] = nums[i - 1] * L[i - 1];
        }
        // R[i] contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R[length - 1] would be 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            // R[i + 1] already contains the product of elements to the right of 'i + 1'
            // Simply multiplying it with nums[i + 1] would give the product of all
            // elements to the right of index 'i'
            R[i] = nums[i + 1] * R[i + 1];
        }
        // Constructing the answer array
        for (int i = 0; i < length; i++) {
            // For the first element, R[i] would be product except self
            // For the last element of the array, product except self would be L[i]
            // Else, multiple product of all elements to the left and to the right
            answer[i] = L[i] * R[i];
        }
        return answer;
    }

    //Approach 2: O(1) Space approach
    //Time: O(N)
    //Space: O(1)
    public int[] productExceptSelfSpaceO1(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for(int i = 1; i < length; i++) {
            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] *   answer[i - 1];
        }
        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for(int i = length - 1; i >= 0; i--) {
            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }

}
