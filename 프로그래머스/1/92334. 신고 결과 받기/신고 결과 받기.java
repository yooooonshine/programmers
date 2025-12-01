import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Set<Report> rs = new HashSet<>();

        
        // id 인덱스 구하기
        Map<String, Integer> id_index = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            id_index.put(id_list[i], i);
        }
            
        // 각 유저별로 리포트 수 구하기
        int[] reportCounts = new int[id_list.length];
        for (int i = 0; i < report.length; i++) {
            String[] tmp = report[i].split(" ");
            String s = tmp[0];
            String r = tmp[1];
            
            if (!rs.contains(new Report(id_index.get(s), id_index.get(r)))) {
                // 기존 신고 없으면 추가
                reportCounts[id_index.get(r)]++;
                rs.add(new Report(id_index.get(s), id_index.get(r)));
            }
        }
        
        // 차단자 구하기
        Set<Integer> blackList = new HashSet<>();
        for (int i = 0; i < reportCounts.length; i++) {
            if (reportCounts[i] >= k) {
                blackList.add(i);
            }
        }
            
        // 이메일 구하기
        int[] emailCounts = new int[id_list.length];
        for (Report r : rs) {
            int tmpS = r.s;
            int tmpR = r.r;
            
            if (blackList.contains(tmpR)) {
                emailCounts[tmpS]++;
            }
        } 
        
        
        // 결과
        return emailCounts;
    }
    
    public class Report {
        int s; // sender
        int r; // receiver
        
        public Report(int s, int r) {
            this.s = s;
            this.r = r;
        }
        
        @Override
        public boolean equals(Object o) {
            Report r = (Report)o;
            return this.s == r.s && this.r == r.r;
        }
        
        @Override
        public int hashCode() {
            return s + r;
        }
    }
}


// 특정 신고는 set으로 관리
// set에 없으면 신고수 추가
// 신고수가 k 넘는 신고들에 대해 보낸이  count++
// 이름 별 인덱스 저장
// 이름 별 메일 횟수 저장



// ㅂ줄량 이용자 신고 및 처리 결과 메일 발송
// 각 유저는 한 번에 한 명 유저 신고
// 신고 횟수 제한 x
// 한 유저 여러번 가능하지만 이 신고 횟수는 1회로 처리
// k번 이상은 정지 -> 정지 사실을 메일 발송

// 각 유저별로 메일 받은 횟수
