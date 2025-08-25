
import java.util.*;
import java.io.*;

public class Main {

	public static int V;
	public static int[] dist;
	public static int INF = 500000000;
	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		int P = Integer.parseInt(st.nextToken()); // 건위 위치

		adj = new ArrayList<>();
		for (int v = 0; v <= V; v++) {
			adj.add(new ArrayList<>());
		}

		for (int e = 1; e <= E; e++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}

		dist = new int[V + 1];
		djk(1);
		int dist1 = dist[V];
		int dist2 = dist[P];

		dist = new int[V + 1];
		djk(P);
		int dist3 = dist[V];


		if (dist1 == dist2 + dist3) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}

	public static void djk(int s) {
		for (int v = 0; v <= V; v++) {
			dist[v] = INF;
		}
		dist[s] = 0;


		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowE = now.e;
			int nowD = now.d;

			for (Edge next : adj.get(nowE)) {
				if (dist[next.e] > dist[nowE] + next.d) {
					dist[next.e] = dist[nowE] + next.d;
					pq.add(new Edge(next.e, dist[next.e]));
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
			return this.e - e.e;
		}
	}
}
