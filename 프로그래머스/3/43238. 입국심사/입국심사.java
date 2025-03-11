import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        int max = times[times.length - 1];
        long timeMax = (long)max * (long)n; // 이진탐색 끝
        
        long s = 0;
        long e = timeMax;
        long mid = (s + e) / 2;
        long lastCan = mid;
        while (s <= e) {
            mid = (s + e) / 2;
            
            if (can(times, mid, n)) {
                e = mid - 1;
                lastCan = mid;
            } else {
                s = mid + 1;
            }
        }
        
        long answer = lastCan;
        return answer;
    }
    
    // 리턴이 long이다. long 사용4
    
    public boolean can(int[] times, long nTime, int n) {
        
        
        long count = 0;
        
        for (int i = 0; i < times.length; i++) {
            count += nTime / times[i];
        }
        
        if (count >= (long)n) {
            return true;
        } else {
            return false;
        }
    }
}

// n명
// 비어있는 심사대 or 더 빨리 끝나는 심사대 있으면 대기
// 심사 받는데 걸리는 시간최소

// 마지막 빼고는 바로바로 넣는게 최대시간이 단축되지 않나

// 결국 max(7 * (n - k), 10 * k)가 최소가 되는 k값
// 이분탐색이 필요하겠네.
// 이 k에 대한 이분탐색

// 1 2 3 4 5 6 7
// 35 28 30 40 50 60

// 결론적으로는 n을 적당히 배분해서 max가 적게 나와야 돼

// 5 11 23
// 5x 2x 1x

// 이분 탐색
// 최대 시간과 0분 사이에서 이진탐색하기
// n이 안된다면 오른쪽
// n이 된다면 왼쪽
// 