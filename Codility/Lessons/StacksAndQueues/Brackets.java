/*
A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

S is empty;
S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, the string "{[()()]}" is properly nested but "([)()]" is not.

Write a function:

class Solution { public int solution(String S); }

that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..200,000];
string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
*/

import java.util.*;

/**
 * Codility Brackets - 100% / O(N) solution
 */
class Solution {
    
    char getMatchingChar(char c) {
        switch (c) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                throw new IllegalArgumentException("Unexpected character: "+c);
        }
    }
    
    boolean isOpeningChar(char c) {
        return c=='(' || c=='[' || c=='{';
    }
    
    public int solution(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<S.length(); i++) {
            char currentChar = S.charAt(i);
            if (!stack.empty() && getMatchingChar(stack.peek()) == currentChar) {
                stack.pop(); // match found
            } else if(isOpeningChar(currentChar)) {
                stack.push(currentChar); // wait for possible match later
            } else {
                return 0; // no match possible, abort now
            }
        }
        return stack.empty() ? 1 : 0;
    }
    
}
