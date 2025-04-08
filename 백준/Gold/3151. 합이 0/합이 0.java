import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int[] A;
	public static long count = 0; // 조합 수가 많을 수 있으니 long

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		for (int i = 0; i < N - 2; i++) {
			twoPointer(i, i + 1, N - 1);
		}
		System.out.println(count);
	}

	public static void twoPointer(int s, int m, int e) {
		while (m < e) {
			int sum = A[s] + A[m] + A[e];

			if (sum < 0) {
				m++;
			} else if (sum > 0) {
				e--;
			} else {
				if (A[m] == A[e]) {
					int len = e - m + 1;
					count += (long) len * (len - 1) / 2;
					break;
				}

				int lv = A[m];
				int rv = A[e];
				int lc = 0;
				int rc = 0;

				while (m < e && A[m] == lv) {
					lc++;
					m++;
				}
				while (e >= m && A[e] == rv) {
					rc++;
					e--;
				}

				count += (long) lc * rc;
			}
		}
	}
}