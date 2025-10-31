import java.util.*;
import java.io.*;

public class Main {

	public static long[] A;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		long max = Long.MIN_VALUE;

		A = new long[N + 1];
		for (int n = 1; n <= N; n++) {
			A[n] = Long.parseLong(br.readLine());

			if (max < A[n]) {
				max = A[n];
			}
		}

		System.out.println(recursion(1, N, max));
	}

	public static long recursion(int s, int e, long beforeMax) {

		boolean flat = true;

		long max = Long.MIN_VALUE;
		for (int n = s; n <= e; n++) {
			if (max < A[n]) {
				max = A[n];
			}
		}

		// 평평
		for (int n = s; n < e; n++) {
			if (A[n] != A[n + 1]) {
				flat = false;
			}
		}

		// 평평하면
		if (flat) {
			return beforeMax - max;
		}

		// 평평하지 않으면

		// 이전과 같으면 ++
		// 이전과 다르면
		// 재귀, ms, me 초ㄱ화
		// 다른 지점 찾기

		long result = beforeMax - max;

		for (int n = s; n <= e; n++) {
			if (A[n] == max) {
				continue;
			} else {

				int ts = n;
				int te = n;

				for (int t = n + 1; t <= e; t++) {
					if (A[t] == max) {
						break;
					}
					te++;
				}

				result += recursion(ts, te, max);
				n = te;
			}
		}

		return result;
	}
}