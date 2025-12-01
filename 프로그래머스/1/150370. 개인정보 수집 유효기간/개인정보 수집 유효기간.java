import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 오늘 세팅
        int[] todayParts = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();
        MyDate todayDate = new MyDate(todayParts[0], todayParts[1], todayParts[2]);
        
        // 약관 Dict
        Map<String, Integer> types = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] termParts = terms[i].split(" ");
            String type = termParts[0];
            int duration = Integer.parseInt(termParts[1]);
            
            types.put(type, duration);
        }
        
        // 개인정보 세팅
        List<PersonalInfo> ps = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] tmp = privacies[i].split(" ");
            int[] dateParts = Arrays.stream(tmp[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
            String type = tmp[1];
            
            ps.add(new PersonalInfo(dateParts[0], dateParts[1], dateParts[2], type, i + 1));
        }
        
        // 정답 , 파기할 개인정보 변호
        List<Integer> answerList = new ArrayList<>();
        
        // 유효기간 계산 & 파기 계산
        for (PersonalInfo p : ps) {
            int duration = types.get(p.type);
            MyDate end = calcEnd(p.y, p.m, p.d, duration);
            
            // 파기 됐으면, 당일도 파기 x
            if (end.compareTo(todayDate) < 0) {
                answerList.add(p.i);
            }
        }
        
        int[] answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(answer);
        return answer;
    }
    
    public class MyDate implements Comparable<MyDate> {
        int y;
        int m;
        int d;
        
        public MyDate(int y, int m, int d) {
            this.y = y;
            this.m = m;
            this.d = d;
        }
        
        @Override
        public int compareTo(MyDate my) {
            if (this.y > my.y) {
                return 1;
            } else if (this.y < my.y) {
                return -1;
            }
            
            // y가 같을 경우
            if (this.m > my.m) {
                return 1;
            } else if (this.m < my.m) {
                return -1;
            }
            
            // m이 같을 경우
            if (this.d > my.d) {
                return 1;
            } else if (this.d < my.d) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    
    public MyDate calcEnd(int y, int m, int d, int addM) {
        // addM을 1년 단위로
        int addY = addM / 12;
        addM = addM % 12; // 더해야하는 나머지 달
        
        // 년 더하기
        y += addY;
        
        // 달 더하기
        if (m + addM > 12) {
            // 24 등
            if ((m + addM) % 12 == 0) {
                addY = (m + addM) / 12 - 1;
                m = 12;
            } else {
                addY = (m + addM) / 12;

                y += addY;
                m = (m + addM) % 12;
            }
            

        } else {
            m += addM;
        }
        
        // 1일 빼기
        if (d == 1) {
            if (m == 1) {
                // 1월이면
                y--;
                m = 12;
                d = 28;
            } else {
                // 1월이 아니면
                m--;
                d = 28;
            }
        } else {
            d--;
        }
        
        return new MyDate(y, m, d);
    }
    
    public class PersonalInfo {
        int y;
        int m;
        int d;
        String type;
        int i;
        
        public PersonalInfo(int y, int m, int d, String type, int index) {
            this.y = y;
            this.m = m;
            this.d = d;
            this.type = type;
            this.i = index;
        }
    }
}

// 1 ~ n 번으로 분류되는 개인정보 n개
// 약관 종류 다양
// 각 약관마다 유효기간 존재
// 유효기간 후 파기
// 모든 달은 28일까지 존재
// x 달 후 -1일까지 유효
// 파기해얗ㄹ 개인정보 번호를 오름차순 1차원 정수배열

// 각 개인정보별로 유효기간 계산
// 각 개인정보별로 파기인지 계산