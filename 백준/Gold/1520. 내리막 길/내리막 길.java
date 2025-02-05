import java.util.*;
import java.io.*;

public class Main {
	public static int[] xs = {1, -1, 0, 0};
	public static int[] ys = {0, 0, 1, -1};

	public static int[][] arr;
	public static long[][] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 세로
		int N = Integer.parseInt(st.nextToken()); // 가로

		arr = new int[M + 2][N + 2];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i <= N + 1; i++) {
			arr[0][i] = 10001;
			arr[M + 1][i] = 10001;
		}
		for (int i = 0; i <= M + 1; i++) {
			arr[i][0] = 10001;
			arr[i][N + 1] = 10001;
		}


		// 배열 초기화
		result = new long[M + 2][N + 2];
		for (int i = 0; i <= M + 1; i++) {
			Arrays.fill(result[i], -1L);
		}
		for (int i = 0; i <= N + 1; i++) {
			result[0][i] = 0L;
			result[M + 1][i] = 0L;
		}
		for (int i = 0; i <= M + 1; i++) {
			result[i][0] = 0L;
			result[i][N + 1] = 0L;
		}
		result[1][1] = 1L;

		System.out.println(calc(M, N));
	}

	public static long calc(int x, int y) {
		if (result[x][y] != -1L) {
			return result[x][y];
		}

		long tmp = 0;
		for (int i = 0; i <= 3; i++) {
			if (arr[x + xs[i]][y + ys[i]] > arr[x][y]) {
				tmp += calc(x + xs[i], y + ys[i]);
			}
		}
		result[x][y] = tmp;

		return tmp;
	}
}


// 항상 높이가 더 낮은 지점으로만 이동
// 그래프?

// 내리막길로만 이동하는 경로의 개수
// 탐색?

// 지도의 세로 M, 가로 N
// M개의 줄 N개
// 경우의 수가 10억 이하? long으로 해야겠군

// 각 지점은 상하좌우 나보다 높은 곳들의 합

// 이거 모든 입력 케이스를 세면 시간초과구나
// dp일거같다.

//
// 1,1은 1
// 2,1은 1
// 1,2는 1

// 그 주위의 더 높은 숫자만 찾는다.
// calc(x,y) = calc(x + 1, y) calc(x-1,y) ....
// 단 위는 현재보다 숫자가 더 큰지 체크 후 더하기

// 처음에는 -1로 채우기
// calc 계산되면 업데이트하기