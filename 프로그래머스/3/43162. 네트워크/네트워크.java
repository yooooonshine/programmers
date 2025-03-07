import java.util.*;
import java.io.*;

class Solution {
    public int[] net;
    
    public int solution(int n, int[][] computers) {
        net = new int[n]; // 0 - n-1
        for (int i = 0; i < n; i++) {
            net[i] = i;
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (computers[r][c] == 1) {
                    union(r, c);
                }
            }
        }
        
        Set<Integer> myS = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int tmp = find(net[i]);
            
            if (!myS.contains(tmp)) {
                myS.add(tmp);
            }
        }
        
        int answer = myS.size();
        return answer;
    }
    
    public void union(int a, int b) {
        int ar = find(a);
        int br = find(b);
        
        if (ar > br) {
            net[ar] = br;
        } else if (ar < br) {
            net[br] = ar;
        }
    }
    
    public int find(int a) {
        if (a == net[a]) {
            return a;
        }
        
        return net[a] = find(net[a]);
    }
}

// n 컴퓨터 수 (0~n-1)
// 네트워크의 개수를 리턴


//
    
// 유니온파인드 가능할거같아
    
// 모두 유니온 파인드 한 후
// 결과배열에서 Set의 개수로