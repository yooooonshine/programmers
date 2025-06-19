import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int V;
	public static int E;

	public static int[] distA;
	public static int[] distB;
	public static List<List<Edge>> adj;

	public static int MAX = 10000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 팀원의 수
		V = Integer.parseInt(st.nextToken()); // 장소의 수
		E = Integer.parseInt(st.nextToken()); // 도로의 수

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()); // Kist 위치
		int B = Integer.parseInt(st.nextToken()); // 씨알푸드 위치

		distA = new int[V + 1];
		distB = new int[V + 1];
		for (int v = 0; v <= V; v++) {
			distA[v] = MAX;
			distB[v] = MAX;
		}

		st = new StringTokenizer(br.readLine());
		int[] teamHome = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			teamHome[n] = Integer.parseInt(st.nextToken()); // 팀원 위치
		}

		adj = new ArrayList<>();
		for (int v = 0; v <= V; v++) {
			adj.add(new ArrayList<>());
		}

		for (int e = 1; e <= E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(a, b, i));
			adj.get(b).add(new Edge(b, a, i));
		}

		djk(A, distA);
		djk(B, distB);

		int sum = 0;
		for (int n = 1; n <= N; n++) {
			int d1 = distA[teamHome[n]];
			int d2 = distB[teamHome[n]];

			if (d1 == MAX) {
				d1 = -1;
			}
			if (d2 == MAX) {
				d2 = -1;
			}

			sum += d1 + d2;
		}
		System.out.println(sum);
	}

	public static void djk(int s, int[] dist) {
		boolean[] visit = new boolean[V + 1];
		dist[s] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, s, 0));

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
					pq.add(new Edge(nowE, next.e, dist[next.e]));
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int d;

		public Edge(int s, int e, int d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.d - edge.d;
		}
	}
}

// 각 장소 노드
// 연결된 도로 간선
// N명 집 노드 번호, kist, 씨알푸드 노드 번호
// di = kist거리 + 씨알푸드 거리
// 각각 도달 불가시 -1
//
