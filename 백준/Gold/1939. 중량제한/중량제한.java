
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;
	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 섬
		M = Integer.parseInt(st.nextToken()); // 다리수

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(b, d));
			adj.get(b).add(new Edge(a, d));
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // 시작 섬
		int e = Integer.parseInt(st.nextToken()); // 도착 섬

		System.out.println(djk(s, e));
	}

	public static int djk(int s, int e) {
		boolean[] visit = new boolean[N + 1];
		int[] dist = new int[N + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 2000000000));

		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowE = now.e;
			int nowD = now.d;

			if (visit[nowE]) {
				continue;
			}
			visit[nowE] = true;

			for (Edge next : adj.get(nowE)) {
				int nextD = next.d;
				int nextE = next.e;


				if (dist[nextE] < Math.max(dist[nextE], Math.min(nowD, nextD))) {
					dist[nextE] = Math.max(dist[nextE], Math.min(nowD, nextD));
					pq.add(new Edge(nextE, dist[nextE]));
				}
			}

		}

		return dist[e];
	}

	public static class Edge implements Comparable<Edge> {
		int e;
		int d;

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Edge e) {
			return e.d - this.d; // 내림차순
		}
	}
}


// 이분탐색

// 모든 경로에서 최대?

// 이분탐색
// 다익스트라
// 큐?
// 인접리스트 최소값
// 큐에 추가
// 최대값을 갱신하는 경우에 추가
// 모든 경로는 0으로 시작.