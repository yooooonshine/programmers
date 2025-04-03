import java.util.*;
import java.io.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            if (map.containsKey(tangerine[i])) {
                map.put(tangerine[i], map.get(tangerine[i]) + 1);
            } else {
                map.put(tangerine[i], 1);
            }
        }
        
        List<Tangerine> ts = new ArrayList<>();
        for (Integer key : map.keySet()) {
            ts.add(new Tangerine(key, map.get(key)));
        }
        Collections.sort(ts);
        
        int count = 0;
        for (Tangerine t : ts) {
            k -= t.count;
            count++;
            
            if (k <= 0) {
                break;
            }
        }
        
        int answer = count;
        return answer;
    }
    
    public static class Tangerine implements Comparable<Tangerine> {
        int size;
        int count;
        
        public Tangerine(int size, int count) {
            this.size = size;
            this.count = count;
        }
        
        @Override
        public int compareTo(Tangerine t) {
            return t.count - this.count;
        }
    }
}
// 귤 중 k 고르기
// 서로 다른 종류의 수를 최소화
// 서로 다른 종류의 수 최솟값
// 1. 각 크기의 개수를 찾는다.
// 2. 개수가 많은 순대로 담는다.

// 1 = 1
// 2 = 2
// 3 = 2
// 4 = 1
// 5 = 2

// 1. Map에 귤 크기, 개수로 저장한다.
// 2. 모든 값들을 가져와 배열에 넣는다.
// 3. 배열을 정렬한다.