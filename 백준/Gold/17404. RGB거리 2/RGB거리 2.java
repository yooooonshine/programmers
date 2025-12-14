
import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 1][3];
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			arr[n][0] = Integer.parseInt(st.nextToken());
			arr[n][1] = Integer.parseInt(st.nextToken());
			arr[n][2] = Integer.parseInt(st.nextToken());
		}

		int min = 100000000;

		// 처음이 0 인경우
		int[][] dp = new int[N + 1][3];
		dp[1][0] = arr[1][0];
		dp[1][1] = 100000000;
		dp[1][2] = 100000000;

		for (int n = 2; n <= N; n++) {
			dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + arr[n][0];
			dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + arr[n][1];
			dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + arr[n][2];
		}

		min = Math.min(min, Math.min(dp[N][1],  dp[N][2]));

		// 처음이 1 인경우
		dp = new int[N + 1][3];
		dp[1][0] = 100000000;
		dp[1][1] = arr[1][1];
		dp[1][2] = 100000000;

		for (int n = 2; n <= N; n++) {
			dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + arr[n][0];
			dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + arr[n][1];
			dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + arr[n][2];
		}
		min = Math.min(min, Math.min(dp[N][0],  dp[N][2]));

		// 처음이 2 인경우
		dp = new int[N + 1][3];
		dp[1][0] = 100000000;
		dp[1][1] = 100000000;
		dp[1][2] = arr[1][2];

		for (int n = 2; n <= N; n++) {
			dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + arr[n][0];
			dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + arr[n][1];
			dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + arr[n][2];
		}
		min = Math.min(min, Math.min(dp[N][0],  dp[N][1]));

		System.out.println(min);
	}
}

// 집 n개
// 1번부터 n번집
// 빨강, 초록, 파랑 집
// 각 집마다 빨강, 초록, 파랑 비용 존재
// 칠하는 최소 비용

// 단 양 옆과 색이 같지 않아야 한다.

// i번째가 0 일떄 최대,
// i번쨰가 1일떄 최대
// i번쨰가 2일떄 최대를
// 구하고

// dp[i][0] = Max(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];