import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 배열 생성 및 초기화 N + 1 까지
        int[][] arr = new int[rows + 1][columns + 1];
        
        int index = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                arr[i][j] = index;
                index++;
            }
        }
        
        int[] result = new int[queries.length];
        
        // 쿼리 실행
        for (int q = 0; q <  queries.length; q++) {
            int x1 = queries[q][0];
            int y1 = queries[q][1];
            int x2 = queries[q][2];
            int y2 = queries[q][3];
            
            int min = 10000000;
            
            int tmp1 = arr[x1][y1];
            int tmp2 = 0;
            for (int y = y1; y <= y2 - 1; y++) {
                tmp2 = arr[x1][y + 1];
                arr[x1][y + 1] = tmp1;
                if (tmp1 < min) {
                    min = tmp1;
                }
                tmp1 = tmp2;
            }
            
            for (int x = x1; x <= x2 - 1; x++) {
                tmp2 = arr[x + 1][y2];
                arr[x + 1][y2] = tmp1;
                if (tmp1 < min) {
                    min = tmp1;
                }
                tmp1 = tmp2;
            }
            for (int y = y2; y >= y1 + 1; y--) {
                tmp2 = arr[x2][y - 1];
                arr[x2][y - 1] = tmp1;
                if (tmp1 < min) {
                    min = tmp1;
                }
                tmp1 = tmp2;
            }

            for (int x = x2; x >= x1 + 1; x--) {
                tmp2 = arr[x - 1][y1];
                arr[x - 1][y1] = tmp1;
                if (tmp1 < min) {
                    min = tmp1;
                }
                tmp1 = tmp2;
            }

            
            result[q] = min;
        }
        
        // 상, 우, 하, 좌
        // 최소 따로 저장
        // 상 돌릴 때 다음 값 tmp에 저장하고, 기존 값을 이동
        
        return result;
    }
}

// 테두리 숫자 시계방향 회전
// (x1, y1, x2, y2) -> 시계방향 회줜
// 실제 회전시키고 최소 구하는 방법밖