
import java.util.*;
import java.io.*;

public class Main {

	public static int N, M; // N명, 놀이기구 1~M
	public static int[] runTime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N명
		M = Integer.parseInt(st.nextToken());	 // 놀이기구 M

		runTime = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			runTime[i] = Integer.parseInt(st.nextToken());
		}

		long maxT = 60000000000L;
		long s = 0;
		long e = maxT;

		while (s <= e) {
			long m = (s + e) / 2;

			int tmp = calc(m);
			if (tmp == - 1) {
				e = m - 1;
			} else if (tmp == -2) {
				s = m + 1;
			} else {
				System.out.println(tmp);
				break;
			}
		}
	}
	public static int calc(long m) {
		// System.out.println(m);
		long sumMin = 0L;
		long sumMax = 0L;

		List<Integer> list = new ArrayList<>();

		for (int i  = 1; i < runTime.length; i++) {
			if (m % (long)runTime[i] == 0L) {
				sumMin += m / (long)runTime[i];
				list.add(i);
			} else {
				sumMin += m / (long)runTime[i] + 1L;
			}

			sumMax += m / (long)runTime[i] + 1L;
		}

		if (sumMin < N && N <= sumMax) {
			int index = (int)(N - sumMin) - 1; // 0번부터
			return list.get(index);
		} else if(N <= sumMin) {
			return -1;
		} else {
			// sumMax보다 많을 때 -> 시간 늘려야 함.
			return -2;
		}
	}
}