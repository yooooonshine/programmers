import java.util.*;
import java.io.*;

class Solution {
    
    public int solution(int n, int[] tops) {
        int[] a = new int[n];
        int[] b = new int[n];
        
        if (tops[0] == 1) {
            a[0] = 3;
            b[0] = 1;
        } else {
            a[0] = 2;
            b[0] = 1;
        }
        
        
        for (int i = 1; i < n; i++) {
            if(tops[i] == 1) {
                a[i] = 3 * a[i - 1] + 2 * b[i - 1];
                b[i] = a[i - 1] + b[i - 1];
            } else {
                a[i] = 2 * a[i - 1] + 1 * b[i - 1];
                b[i] = a[i - 1] + b[i - 1];
            }
            
            a[i] %= 10007;
            b[i] %= 10007;
        }
        
        return (a[n - 1] + b[n - 1]) %  10007;
    }
}

// 위가 있는 경우
// a[n] -> 끝이 없는 경우
// b[n] -> 끝이 있는 경우
// a[n + 1] = 3 * a[n] + 2 * b[n]
// b[n + 1] = 1 * a[n] + 1 * b[n]

// 위가 없는 경우
// a[n + 1] = 2 * a[n] + 1 * b[n]
// b[n + 1] = 1 * a[n] + 1 * b[n]