
import java.util.*;
import java.io.*;

public class Main {

	public static int N, M, L;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 현재 휴게소 수
		int M = Integer.parseInt(st.nextToken()); // 추가 휴게소
		int L = Integer.parseInt(st.nextToken()); // 고속도로 길이

		int[] nowRest = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nowRest[i] = Integer.parseInt(st.nextToken());
		}
		nowRest[N + 1] = L;
		Arrays.sort(nowRest);

		int s = 1;
		int e = L;
		while (s <= e) {
			int m = (s + e) / 2; // 최대 길이
			int remainRest = M;
			int madeCount = 0;

			for (int i = 0; i <= N; i++) {
				int length = nowRest[i + 1] - nowRest[i];
				int cut = length / m;

				madeCount += cut;

				if (length % m == 0) {
					cut -= 1;
				}
				remainRest -= cut;
			}

			if (remainRest == 0) {
				if (min > m) {
					min = m;
				}
				e = m - 1;
			} else if (remainRest < 0) {

				s = m + 1;
			} else {
				if (madeCount > remainRest) {
					min = Math.min(min, m);
					e = m - 1;
				} else {
					e = m - 1;
				}
			}
		}

		System.out.println(min);
	}
}
