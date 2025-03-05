import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0L;
        
        for (int i = 0 ; i < queue1.length; i++) {
            sum += (long)queue1[i];
        }
        for (int i = 0 ; i < queue2.length; i++) {
            sum += (long)queue2[i];
        }
        long half = sum / 2L;
        
        // 홀수면 불가능
        if (sum % 2 == 1) {
            return -1;
        }

        long sum1 = 0L;
        Queue<Long> q1 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            q1.add((long)queue1[i]);
            sum1 += (long)queue1[i];
        }
        
        long sum2 = 0L;
        Queue<Long> q2 = new LinkedList<>();
        for (int i = 0; i < queue2.length; i++) {
            q2.add((long)queue2[i]);
            sum2 += (long)queue2[i];
        }
        
        int end = (queue1.length + queue2.length) * 2;
        int count = 0;
        while (sum1 != sum2) {
            
            if (sum1 > sum2) {
                long tmp = q1.poll();
                q2.add(tmp);
                sum1 -= tmp;
                sum2 += tmp;
            } else {
                long tmp = q2.poll();
                q1.add(tmp);
                sum1 += tmp;
                sum2 -= tmp;
            }
            count ++;
            
            if (count > end) {
                return -1;
            }
        }
        return count;
    }
}

// 케이스
// A B // 0
// A AB // A값
// AB B2 // A + B2 값
// A A1BA3 // A1 + A + A1 + B
// A BA2 // B + A
// A1B B1A // B1 + A1
// AB A // A
// ABA1 A2 // A + B + A + A2