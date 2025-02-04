import java.util.*;
import java.io.*;

public class Main {
	public static int[] result;
	public static int[] T;
	public static int[] P;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		T = new int[N + 1];
		P = new int[N + 1];

		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		result = new int[N + 2];
		for (int i = N; i >= 1; i--) {

			int a = 0;
			if (!(i + T[i] - 1 > N)) {
				a = result[i + T[i]] + P[i];
			}
			int b = result[i + 1];

			result[i] = Math.max(a, b);
		}

		System.out.println(result[1]);
	}
}

// 오늘을 선택한 것의 최대 vs 오늘 선택 x