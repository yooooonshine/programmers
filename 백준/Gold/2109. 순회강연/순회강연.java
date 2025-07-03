import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Class> list = new ArrayList<>();
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			list.add(new Class(p, d));
		}

		Collections.sort(list);

		PriorityQueue<Class> pq = new PriorityQueue<>((a, b) -> {
			return a.p - b.p; // 우선순위 큐는 p 기준으로 오름차순 정렬
		});

		int nowD = 0;
		for (int i = 0; i < N; i++) {
			Class c = list.get(i);

			int p = c.p;
			int d = c.d;

			if (nowD >= d) {
				if (nowD == d) {
					if (pq.peek().p < p) {
						pq.poll();
						pq.add(c);
					}
				}

				continue;
			}

			nowD++;
			pq.add(c);
		}

		int sum = 0;
		while (!pq.isEmpty()) {
			Class c = pq.poll();
			sum += c.p;
		}
		System.out.println(sum);
	}

	public static class Class implements Comparable<Class> {
		int p;
		int d;

		public Class(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Class c) {
			if (this.d == c.d) {
				return c.p - this.p;
			} else {
				return this.d - c.d;
			}
		}
	}
}