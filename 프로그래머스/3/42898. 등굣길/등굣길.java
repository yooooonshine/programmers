class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // m x n(가로, 세로)
        int answer = 0;
        
        int[][] roads = new int[n + 1][m + 1]; // 세로, 가로
        for (int i = 0; i < puddles.length; i++) {
            int r = puddles[i][1]; // 세로
            int c = puddles[i][0]; // 가로
            
            roads[r][c] = -1;
        }
        roads[1][1] = 1;
        
        for (int r = 1; r <= n; r++) {
            
            for (int c = 1; c <= m; c++) {
                if (r == 1 && c == 1) {
                    continue;
                }
                
                if (roads[r][c] == -1) {
                    continue;
                }
                
                int up = 0;
                if (roads[r - 1][c] != -1) {
                    up = roads[r - 1][c];
                }
                
                int left = 0;
                if (roads[r][c - 1] != -1) {
                    left = roads[r][c - 1];
                }
                
                roads[r][c] = (left + up) % 1000000007;
            }
        }
        
        answer = roads[n][m];
        return answer % 1000000007;
    }
}


// puddles
// 최단 경로의 개수를 10000000007로 나누기

// 물에 잠긴 지역은10개 이하

// 오른쪽과 아래로만 이동
// dfs하면? 경우에수가 무진장 많나보다.

// mn = 10000
// 2^10000
// 안된다.
// 그럼 dp는 될 거 같아
// 그리디?

// 일단 현재로 미래 예측이 될 거 같다.