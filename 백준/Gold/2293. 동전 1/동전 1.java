import java.util.*;
import java.io.*;

public class Main {
	public static int[] arr;
	public static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 동전 수
		int k = Integer.parseInt(st.nextToken()); // k원

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		dp = new int[k + 1][n];
		for (int r = 1; r <= k; r++) {
			for (int c = 0; c < n; c++) {
				dp[r][c] = -1;
			}
		}

		for (int c = 0; c < n; c++) {
			dp[0][c] = 1;
		}

		System.out.println(recursion(k, n - 1)); // 최대 금액
	}

	public static int recursion(int p, int index) {
		if (index == 0) {
			if (p % arr[index] == 0) {
				return 1;
			} else {
				return 0;
			}
		}

		if (dp[p][index] != -1) {
			return dp[p][index];
		}

		int pTemp = arr[index];

		int nowIndex = 0;

		int sum = 0;
		while (p - pTemp * nowIndex > 0) {
			sum += recursion(p - pTemp * nowIndex, index - 1);
			nowIndex++;
		}
		if (p - pTemp * nowIndex == 0) {
			sum++;
		}

		dp[p][index] = sum;
		return sum;
	}
}
