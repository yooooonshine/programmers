import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}

		int[] dp1 = new int[N + 1];
		int[] dp2 = new int[N + 1];

		Arrays.fill(dp1, 1);
		Arrays.fill(dp2, 1);
		for (int n = 1; n <= N; n++) {
			for (int x = 1; x <= n - 1; x++) {
				if (arr[n] > arr[x] && dp1[n] < dp1[x] + 1) {
					dp1[n] = dp1[x] + 1;
				}
			}
		}

		for (int n = N; n >= 1; n--) {
			for (int x = N; x >= n + 1; x--) {
				if (arr[n] > arr[x] && dp2[n] < dp2[x] + 1) {
					dp2[n] = dp2[x] + 1;
				}
			}
		}

		int max = 0;
		for (int n = 1; n <= N; n++) {
			if (max < dp1[n] + dp2[n] - 1) {
				max = dp1[n] + dp2[n] - 1;
			}
		}

		System.out.println(max);
	}
}


// 가장 긴 증가하는 수열 고르면 될듯
// 왼쪽 방향
// 오른쪽 방향해서.
// 그 둘의 합이 최대인거 선택
