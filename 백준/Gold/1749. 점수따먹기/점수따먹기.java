
import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 행
		int M = Integer.parseInt(st.nextToken()); // 열

		int[][] arr = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] sum = new int[N + 1][M + 1];
		sum[1][1] = arr[1][1];

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				sum[r][c] = sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1] + arr[r][c];
			}
		}

		int max = Integer.MIN_VALUE;

		for (int r1 = 1; r1 <= N; r1++) {
			for (int r2 = r1; r2 <= N; r2++) {
				for (int c1 = 1; c1 <= M; c1++) {
					for (int c2 = c1; c2 <= M; c2++) {
						int total = sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];

						if (total > max) {
							max = total;
						}
					}
				}
			}
		}

		System.out.println(max);
	}
}

// N * M 행렬
// 각 칸 -10000 ~ 10000 점수
// 부분행렬을 그려 그 안에적힌 정수의 합

// 정수의 합이 최대가 되는 부분행렬 구하기


// 그리디, 누접합, 이분탐색,



// 누적합
// 200C2 -> 20000 * 20000 -> 4 * 10^8
