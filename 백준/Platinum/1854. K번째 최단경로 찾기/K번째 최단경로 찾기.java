import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 수
		int K = Integer.parseInt(st.nextToken()); // K번쨰

		List<List<Edge>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(e, d));
		}

		PriorityQueue<Integer>[] dist = new PriorityQueue[N + 1];
		for (int n = 1; n <= N; n++) {
			dist[n] = new PriorityQueue<>((a, b) -> {
				return b - a;
			});
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0)); // 시작 도시 1, 거리 0
		dist[1].add(0);

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curCity = cur.e; // 현재 도시
			int curDist = cur.d; // 현재 도시까지의 거리

			for (Edge next : adj.get(curCity)) {
				int nextCity = next.e; // 다음 도시
				int nextDist = curDist + next.d; // 다음 도시까지의 거리

				if (dist[nextCity].size() < K) {
					dist[nextCity].add(nextDist);
					pq.add(new Edge(nextCity, nextDist));
				} else if (dist[nextCity].peek() > nextDist) {
					dist[nextCity].poll();
					dist[nextCity].add(nextDist);
					pq.add(new Edge(nextCity, nextDist));
				}
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= N; i++) {
			if (dist[i].size() < K) {
				bw.write("-1\n");
			} else {
				bw.write(dist[i].peek() + "\n");
			}
		}
		bw.flush();
	}

	public static class Edge implements Comparable<Edge> {
		int e; // 도착 도시
		int d; // 거리

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.d, o.d);
		}
	}
}
