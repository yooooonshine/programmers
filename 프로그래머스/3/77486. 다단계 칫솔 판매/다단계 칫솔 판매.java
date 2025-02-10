import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> myM = new HashMap<>();
        myM.put("-", 1);
        
        int index = 2;
        for (int i = 0; i < enroll.length; i++) {
            myM.put(enroll[i], index);
            index++;
        }
        
        int[][] parent = new int[enroll.length + 2][2]; // 1번은 center
        for (int i = 0; i < enroll.length; i++) {
            int meI = myM.get(enroll[i]);
            int refI = myM.get(referral[i]);
            
            parent[meI][0] = refI;
        }
        
        
        for (int i = 0; i < seller.length; i++) {
            int meI = myM.get(seller[i]);
            int pay = amount[i] * 100;
            
            while (parent[meI][0] != 0) {
                int remain = pay / 10;
                
                parent[meI][1] += pay - remain;
                meI = parent[meI][0];
                
                pay = remain;
            }
            
            parent[meI][1] += pay;
        }
        
        int[] answer = new int[enroll.length]; 
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = parent[i + 2][1];
        }
        
        return answer;
    }
}

// 민호는 center
// 참여시킨 추천인에게 배분여시킨 추천인에게 배분, 나머지는 자기꺼
// 배분된 10 %
// 주는 것 -> x / 10

// 판매원 이름 enroll
// 다른 판매원 이름 referral. 추천 x -> -
// referral에 있는 것은 enroll에 이미 등장했음을 보장

// 판매원 이름 seller
// 판매량 amount

// 트리를 구성해야 한다.

// 각 노드는 자신의 부모노드를 저장한다.
// 특정 배열에는 번 돈을 저장한다.

// 각 판매원 이익 return

// 각 직원에 인덱스를 부여해야 돼