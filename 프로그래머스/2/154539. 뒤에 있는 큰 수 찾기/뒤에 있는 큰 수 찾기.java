import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> s = new Stack<>();
        int end = numbers.length;
        
        int[] answer = new int[end];
        for (int i = end - 1; i >= 0; i--) {
            if (s.isEmpty()) {
                answer[i] = -1;
                s.add(numbers[i]);
            } else {
                while (!s.isEmpty() && s.peek() <= numbers[i]) {
                    s.pop();
                }
                if (s.isEmpty()) {
                    answer[i] = -1;
                    s.add(numbers[i]);
                    continue;
                } 
                answer[i] = s.peek();
                s.add(numbers[i]);
            }
        }
        
        return answer;
    }
}

//뒤에서 접근?
//자료?
//스택. 큐
//pq
//무조건 
//투포인트?

// 1 2 3 5 6 9
// 2 6 4 3 5 1
// 우선순위 큐?

//일단 자료형 문제야
//스택?4
//맨 뒤부터 반복문
//1. 뒤에서부터 숫자를 넣는다.
//2. 비어있으면 넣고 -1
//3. 안 비어있으면 맨 위에 확인
//4. 만약 스택 맨 위가 현재 인덱스 값보다 크면 그 값을 결과에 넣고, 스택에 넣는다.