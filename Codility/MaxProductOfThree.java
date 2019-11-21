/*
A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].
*/

/**
 * Codility MaxProductOfThree - 100% / O(N) solution
 */
class Solution {
    public int solution(int[] A) {
        int largest1 = Integer.MIN_VALUE;
        int largest2 = Integer.MIN_VALUE;
        int largest3 = Integer.MIN_VALUE;
        int smallest1 = Integer.MAX_VALUE;
        int smallest2 = Integer.MAX_VALUE;
        // compute largest 3 and smallest 2 numbers in linear time
        for (int e: A) {
            if (e > largest1) {
                largest3 = largest2;
                largest2 = largest1;
                largest1 = e;
            } else if (e > largest2) {
                largest3 = largest2;
                largest2 = e;
            } else if (e > largest3) {
                largest3 = e;
            }
            if (e < smallest1) {
                smallest2 = smallest1;
                smallest1 = e;
            } else if (e < smallest2) {
                smallest2 = e;
            }
        }
        // max is product of largest 3 (all + or all -), or smallest 2 and largest 1 (if mixture of + and -)
        return Math.max(largest2*largest3*largest1, smallest1*smallest2*largest1);
    }
}
