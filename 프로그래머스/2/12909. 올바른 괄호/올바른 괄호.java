import java.util.*;
import java.io.*;

class Solution {
    boolean solution(String s) {
        char[] arr = s.toCharArray();
        
        Stack<Character> myS = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                myS.add(arr[i]);
            } else {
                if (myS.isEmpty()) {
                    return false;
                }
                myS.pop();
            }
        }
        if (myS.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}