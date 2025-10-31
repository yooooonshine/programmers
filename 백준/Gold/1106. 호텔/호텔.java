import java.util.*;
import java.io.*;

public class Main {

	public static int C; // 최소 C명 늘리기 위한 최솟값
	public static int N; // 도시 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int minExpense = Integer.MAX_VALUE;
		int minIndex = 0;

		int[] dpArr = new int[1000 + 1];
		Arrays.fill(dpArr, -1);
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());

			int expense = Integer.parseInt(st.nextToken());
			int customerCount = Integer.parseInt(st.nextToken());
			if (dpArr[customerCount] == -1) {
				dpArr[customerCount] = expense;
			} else {
				dpArr[customerCount] = Math.min(expense, dpArr[customerCount]);
			}

			if (minExpense > expense) {
				minExpense = expense;
				minIndex = customerCount;
			}
		}

		for (int n = 1;  n <= minIndex; n++) {
			dpArr[n] = minExpense;
		}

		for (int n = minIndex + 1; n <= C; n++) {
			if (dpArr[n] == -1) {
				int tmpMin = Integer.MAX_VALUE;

				for (int i = 1; i < n; i++) {
					if (tmpMin > dpArr[i] + dpArr[n - i]) {
						tmpMin = dpArr[i] + dpArr[n - i];
					}
				}

				dpArr[n] = tmpMin;
			} else {
				int tmpMin = dpArr[n];

				for (int i = 1; i < n; i++) {
					if (tmpMin > dpArr[i] + dpArr[n - i]) {
						tmpMin = dpArr[i] + dpArr[n - i];
					}
				}

				dpArr[n] = tmpMin;
			}
		}

		System.out.println(dpArr[C]);
	}
}

// dp[100]은
// dp[100]이 -1이면
// dp[1] + dp[99] ~~ dp[2] + dp[98]
// 대신 미리 채워두기
// 아니면 dp[100]도 비교


// 홍보할 수 있는도시,
// 도시별로 홍보 비용 -> 늘어나는 고객 수

// 돈의 정수배 투자

// 최소 C명 늘리기 위해 투자하는 돈의 최솟값

// 무조건 가성비 넘치는 것로 먼저 채우기


// DP, 그리디,

// 11명 채우기
// 5원에 3명
// 1원에 1명
// 4원에 2명

// 그리디 하지 않네


// dp 인데

// 1명부터 채우면 되지 않을까?

// 49 -> 49원
// 1 -> 2원
// 2 -> 3월

// 50명은 49 + 1
// 100명은 49 + 49 + 2
