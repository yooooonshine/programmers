import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        List<Tuple> table = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            table.add(new Tuple(data[i]));
        }
        
        Collections.sort(table, (Tuple t1, Tuple t2) -> {
            if (t1.c[col - 1] > t2.c[col - 1]) {
                return 1;
            } else if (t1.c[col - 1] < t2.c[col - 1]) {
                return -1;
            } else {
                return t2.c[0] - t1.c[0]; 
            }
        });
        
        int SiSum = 0;
        for (int i = row_begin - 1; i <= row_end - 1; i++) { //첫번째는 이미 함.
            Tuple tuple = table.get(i); // 0부터
            
            SiSum = SiSum ^ tuple.getSi(i + 1); // 1부터 시작
        }
        

        
        return SiSum;
    }
    
    public class Tuple {
        int[] c;
        
        public Tuple(int[] c) {
            this.c = c;
        }
        
        public int getSi(int i) {
            int sum = 0;
            for (int index = 0; index < this.c.length; index++) {
                sum += this.c[index] % i;
            }

            return sum;
        }
    }
}

// 열 칼럼, 행 튜플
// 첫 행 - 기본 키 - 중복 x
// col번째 컬럼의 값으로 오름차순 정렬
// 동일하면 기본키의 값으로 내림 차순

// S_i = 각 컬럼의 값을 i로 나눈 나머지의 합
// row_begin <= 행 <= row_end 에 대해 S_i 누적해 bitwise XOR 값.