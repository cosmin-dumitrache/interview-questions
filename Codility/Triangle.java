/*
An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
Triplet (0, 2, 4) is triangular.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

For example, given array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
the function should return 1, as explained above. Given array A such that:

  A[0] = 10    A[1] = 50    A[2] = 5
  A[3] = 1
the function should return 0.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
*/

import java.util.*;

/**
 * Codility Triangle - 100% / O(N*log(N)) solution
 */
class Solution {
    public int solution(int[] A) {
        if (A.length < 3) return 0; // can't form triangle with less than 3 sides
        Arrays.sort(A);
        for (int i=0; i<A.length-2; i++) { // scan all 3 consecutive sorted elements
            double p = A[i];
            double q = A[i+1];
            double r = A[i+2];
            // Idea: because of sorting we know p < q < r =>
            // p + q > r ? (we don't know yet)
            // q + r > p ? (yes - because sorting)
            // r + p > q ? (yes - because sorting)
            if (p + q > r) { // p and q are largest possible candidates for p + q > r (because sorting)
                return 1;
            }
        }
        return 0;
    }
}
