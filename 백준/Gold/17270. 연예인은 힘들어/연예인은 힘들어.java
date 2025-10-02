import java.util.*;
import java.io.*;

public class Main {

	public static int V;
	public static int M;
	public static List<List<Edge>> adj;

	public static final int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 약속 장소 후보 수
		M = Integer.parseInt(st.nextToken()); // 약속장소 총 길의 수

		adj = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			adj.add(new ArrayList<>());
		}

		// 경로
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}

		// 출발지점들
		st = new StringTokenizer(br.readLine());
		int s1 = Integer.parseInt(st.nextToken());
		int s2 = Integer.parseInt(st.nextToken());

		int[] dist1 = new int[V + 1];
		djk(s1, dist1);

		int[] dist2 = new int[V + 1];
		djk(s2, dist2);

		// 최소인 경로들 찾기
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int minSum = Integer.MAX_VALUE;
		for (int v = 1; v <= V; v++) {
			if (v == s1 || v == s2) continue;
			if (dist1[v] == INF || dist2[v] == INF) continue;
			minSum = Math.min(minSum, dist1[v] + dist2[v]);
		}

		for (int v = 1; v <= V; v++) {
			if (v == s1 || v == s2) continue;
			if (dist1[v] == INF || dist2[v] == INF) continue;
			if (dist1[v] + dist2[v] == minSum && dist1[v] <= dist2[v]) {
				pq.add(new Node(v, dist1[v], dist1[v] + dist2[v]));
			}
		}

		if (pq.isEmpty()) {
			System.out.println("-1");
		} else {
			System.out.println(pq.poll().v);
		}
	}

	public static void djk(int s, int[] dist) {
		Arrays.fill(dist, INF);
		dist[s] = 0;
		boolean[] visit = new boolean[V + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			int nowE = now.e;

			if (visit[nowE]) {
				continue;
			}
			visit[nowE] = true;

			for (Edge next : adj.get(nowE)) {
				if (dist[next.e] > dist[nowE] + next.d) {
					dist[next.e] = dist[nowE] + next.d;
					pq.add(new Edge(next.e, dist[next.e]));
				}
			}
		}
	}

	public static class Node implements Comparable<Node> {
		int v;
		int sd;
		int d;

		public Node(int v, int sd, int d) {
			this.v = v;
			this.sd = sd;
			this.d = d;
		}

		@Override
		public int compareTo(Node n) {
			if (this.d != n.d) {
				return this.d - n.d;
			} else {
				if (this.sd != n.sd) {
					return this.sd - n.sd;
				} else {
					return this.v - n.v;

				}
			}
		}
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
			return this.d - e.d;
		}
	}
}

// 장소를 바꾸려함
// 장소는 1번부터 차례로
// 두 명의 출발장소는 도착장소 x
// 지헌과, 성하의 최단 시간 합이 최소
// 지헌이가 같거나, 먼저

// 여러 개면, 지헌이로부터 가장가까운 곳, 그것도 여러개면 번호가 가장 작은장소

// 다익스트라 두 번 후
// 출발지 제외 모든 노드
// 지헌 <= 성하인 장소 고르기
// pq(시간, 그리고, 번호순)
// 없으면 -1
