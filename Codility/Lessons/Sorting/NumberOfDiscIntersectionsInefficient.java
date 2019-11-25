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
 * Codility NumberOfDiscIntersections - O(N*log(N)) solution
 * 
 * My first attempt at this. The solution is not as efficient and more complicated than need be.
 */
class Disc implements Comparable<Disc> {
    long leftExtreme;
    long rightExtreme;
    Disc(long centerX, long radius) {
        this.leftExtreme = centerX - radius;
        this.rightExtreme = centerX + radius;
    }
    @Override
    public String toString() {
        return String.format("[%d,%d]", this.leftExtreme, this.rightExtreme);
    }
    long getLeftExtreme() {
        return this.leftExtreme;
    }
    long getRightExtreme() {
        return this.rightExtreme;
    }
    @Override
    public int compareTo(Disc o) {
        return Long.compare(this.leftExtreme, o.leftExtreme);
    }
}

class Solution { 
    public int solution(int[] A) {
        List<Disc> leftSortedDiscs = new ArrayList<>();
        for (int i=0; i<A.length; i++)
            leftSortedDiscs.add(new Disc(i, A[i]));
        Collections.sort(leftSortedDiscs);
        long intersectionCount = 0;
        for (int i=0; i<leftSortedDiscs.size(); i++) {
            Disc j = leftSortedDiscs.get(i);
            Disc k = new Disc(j.getRightExtreme(), 0);
            List<Disc> candidates = leftSortedDiscs.subList(i+1, leftSortedDiscs.size());
            int insertionIndex = Collections.binarySearch(candidates, k);
            while (insertionIndex >= 0 &&
                   candidates.size() > insertionIndex+1 &&
                   candidates.get(insertionIndex).getLeftExtreme() == candidates.get(insertionIndex+1).getLeftExtreme()) {
                insertionIndex++;
            }
            int count = insertionIndex < 0 ? (-1) * (insertionIndex+1) : insertionIndex + 1;
            intersectionCount += count;
            if (intersectionCount > 10000000) return -1;
        }
        return (int) intersectionCount;
    }
}