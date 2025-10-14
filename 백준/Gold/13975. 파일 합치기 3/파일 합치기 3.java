import java.util.*;
import java.io.*;

public class Main {

	public static int MAX = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			int K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			PriorityQueue<Long> pq = new PriorityQueue<>();

			for (int k = 1; k <= K; k++) {
				int v = Integer.parseInt(st.nextToken());
				pq.add((long)v);
			}

			long result = 0L;
			while (pq.size() != 1) {
				long v1 = pq.poll();
				long v2 = pq.poll();

				result += (long) v1;
				result += (long) v2;

				pq.add(v1 + v2);
			}

			System.out.println(result + " ");
		}
	}
}

// 합치기 비용 두 파일의 합
// 합치기 최소 비용

// 그리디 or dp
// 이분탛색
