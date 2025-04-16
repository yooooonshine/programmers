import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] scoville, int K) {
        long lK = K;
        
        int count = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.add((long)scoville[i]);
        }
        
        while (true) {
            if (pq.peek() >= lK) {
                break;
            }
            
            long m1 = pq.poll();
            if (pq.isEmpty()) {
                return -1;
            }
            long m2 = pq.poll();
            
            pq.add(m1 + 2l * m2);
            count++;
        }
        
        return count;
    }
}
// 모든 음식 스코빌 지수 K이상이 목적
// 새로운 음식 = 가장 맵지 않은 음식의 스코빌 지수 + 2 * 두번쨰

// 섞어야 하는 최소 횟수
// 맨 앞이 k 이상인지 체크
// 각각 뽑을 때 비어있는지 체크 -> 비어있다 -> -1 리턴
// pq 사용, 최소 두 개 뽑고 섞는다. -> 다시 pq에 넣는다. count ++

// long 사용
