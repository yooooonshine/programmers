import java.util.*;
import java.io.*;

class Solution {
    public int solution(String s) {
        
        int N = s.length();
        
        if (N == 1) {
            return 1;
        }
        
        int end = s.length() / 2;
        
        String[] resultArr = new String[end + 1];
        resultArr[0] = s;
        
        for (int i = 1; i <= end; i++) {
            String result = "";
            
            String before = s.substring(0, i);
            Integer count = 1;
            
            for (int j = i; j <= N; j = j + i) {
                if (j + i > N) {
                    // 마지막 처리
                    if (count == 1) {
                        result += before;
                    } else {
                        result += count.toString() + before;
                    }

                    if (j < N) {
                        result += s.substring(j, N);
                    }
                    
                    break;
                }
                
                String tmp = s.substring(j, j + i);
                if (tmp.equals(before)) {
                    count++;
                } else {
                    if (count == 1) {
                        result += before;
                    } else {
                        result += count.toString() + before;
                    }
                    
                    before = tmp;
                    count = 1;
                }
            }
        
            
            resultArr[i] = result;
        }
        
        int min = Integer.MAX_VALUE;
        String fr = "";
        
        for (int i = 0; i <= end; i++) {
            
            if (resultArr[i].length() < min) {
                fr = resultArr[i];
                min = resultArr[i].length();
            }
        }
        
        
        return min;
    }
    

}

// 0 1 2
// 3글자 

// subString end 제외

// equals 사용, 중간 문자열을 어떻게 빼오지

// 한글자씩 반복이 있으면 넣는다.
// 두 글자씩 반복이 있으면 넣는다.
// 세 글자씩 반복이 있으면 넣는다.
// 글자수 절반까지

// 포인터 k씩 띄어가기
// 이전 단어 기억.
// 이전 단어와 같으면 count++
// 이전 단어와 다르면 count = 1이면 이전 단어 출력, count 더 크면 숫자 + 이전단어

// 압축을 통해 가장 짧은 것의 길이
//숫자 + 반복단어
// 단어를 기준으로 반복
// 문자 개수를 통해 반복

// 글자 수 만큼 띄어서 진행


