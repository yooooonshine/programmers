import java.util.*;
import java.io.*;

class Solution {
    public List<String> tmpR; // 문자열
    public Map<String, Integer> result; // 각 문자열 개수
    public List<List<String>> ordersL;
    
    public String[] solution(String[] orders, int[] course) {
        int ordersN = orders.length; // 입력 orders 크기
        ordersL = new ArrayList<>(); // 리스트로 변환
        for (int i = 0; i < ordersN; i++) {
            String[] tmpA = orders[i].split("");
            List<String> tmp = new ArrayList<>();
            for (int k = 0; k < tmpA.length; k++) {
                tmp.add(tmpA[k]);
            }
            
            Collections.sort(tmp);
            
            ordersL.add(tmp); // 이거 하는방버,..
        }
        
        List<String> r = new ArrayList<>(); // 찐 결과 배열
        tmpR = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            result = new HashMap<>();
            
            for (int j = 0; j < ordersL.size(); j++) {
                func(course[i], 0, ordersL.get(j));
            }
            
            int max = 0;
            
            for (String key : result.keySet()) {
                if (max < result.get(key)) {
                    max = result.get(key);
                }
            }
            
            if (max > 1) {
                for (String key : result.keySet()) {
                    if (max == result.get(key)) {
                        r.add(key);
                    }
                }
            }
        }
        
        Collections.sort(r);
        
        String[] answer = new String[r.size()];
        
        for (int i = 0; i < r.size(); i++) {
            answer[i] = r.get(i);
        }
        return answer;
    }
    
    public void func(int n, int index, List<String> order) {

        
        if (tmpR.size() == n) {
            String tmpS = "";
            for (int i = 0; i < n; i++) {
                tmpS += tmpR.get(i);
            }
            
            if (!result.containsKey(tmpS)) {
                result.put(tmpS, 1);
            } else {
                result.replace(tmpS, result.get(tmpS) + 1);
            }
            
            return;
        }
        
                if (index >= order.size()) {
            return;
        }
        
        // 선택한다
        //  결정 배열에 추가한다.
        // 인덱스를 1늘려 재귀 돌린다.
        // 선택하지 않는다.
        // 인덱스를 1늘려 재귀 돌린다.
        
        tmpR.add(order.get(index));
        func(n, index + 1, order);
        tmpR.remove(tmpR.size() - 1);
        
        func(n, index + 1, order);
    }
}

// 코스요리
// 함께 주문한 단품메뉴들
// 코스는 2가지 이상 단품
// 2명이상으로 주문된 조합이 후보

// Map을 사용한다.
// 2개의 조합 중 최빈을 어떻게 구할까
// 각 조합을 모두 넣어?
// 손님 주문 단품 배열 -> orders
// 추가하고 싶은 코스요리를 구성하는 단품메뉴들 개수 배열 -> course

// 구성이 여러 개면 모두 담아 리턴, 오름차순이어야 함, 없으면 추가 x,(최소 두명이상)

// 방법 1
// 각 단품메뉴들에서 n개를 선택(재귀)
// n개 선택은 모두 Map에 넣는다.(있으면 카운트 증가)
// 카운트가 가장 많은 것을 선택한다.

// 선택 재귀
// 인덱스가 범위를 넘어가면 리턴
// 결정 배열의 크기가 n이면 map에 저장하고 리턴

// 선택한다
//  결정 배열에 추가한다.
// 인덱스를 1늘려 재귀 돌린다.
// 선택하지 않는다.
// 인덱스를 1늘려 재귀 돌린다.

// orders가 오름차순이 아니다. 2차원 배열로 만들고, 다 쪼개야하나