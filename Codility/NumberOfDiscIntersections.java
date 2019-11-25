/*
We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0

Sample diagram: https://codility-frontend-prod.s3.amazonaws.com/media/task_static/number_of_disc_intersections/static/images/auto/0eed8918b13a735f4e396c9a87182a38.png

There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].
*/

import java.util.*;

/**
 * Codility NumberOfDiscIntersections - 100% / O(N*log(N)) solution
 * 
 * Followed the basic elegant idea expressed here: https://qr.ae/TaXPS0
 */
class Solution {
    public int solution(int[] A) {
        long[] startPoints = new long[A.length];
        long[] endPoints = new long[A.length];
        
        // Compute all the disc start/end points in sorted order
        for (int j=0; j<A.length; j++) {
            // cast to long to avoid int overflow
            startPoints[j] = (long) j - (long) A[j];
            endPoints[j] = (long) j + (long) A[j];
        }
        Arrays.sort(startPoints);
        Arrays.sort(endPoints);
        
        int openDiscs = 0;
        int intersectionCount = 0;
        int startPointIndex = 0;
        int endPointIndex = 0;
        
        // Idea: go from left to right through all points and keep track how many discs open at a given point
        while (startPointIndex < startPoints.length) {
           // figure out if the next point is a start or end of a disc
           if (startPoints[startPointIndex] <= endPoints[endPointIndex]) { // disc start (new disc enters the scene)
               intersectionCount += openDiscs; // record intersection with currently open discs
               if (intersectionCount > 10000000) return -1;
               openDiscs++; // add this disc to the currently open discs
               startPointIndex++; // go to next smallest start point
           } else { // disc end (a disk exits the scene)
               openDiscs--; // remove disc from currently open discs
               endPointIndex++; // go to next smallest end point
           }
        }
        
        return intersectionCount;
    }
}


