

import java.util.*;
import java.io.*;

public class Main {

	public static int N, M, X, Y;
	public static int[] dist;
	public static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		List<List<Node>> adj = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Node(b, c));
			adj.get(b).add(new Node(a, c));
		}

		dist = new int[N];
		Arrays.fill(dist, MAX);
		dist[Y] = 0;

		dijkstra(Y, adj);

		// 왕복 거리 계산
		List<Integer> roundTrips = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (i == Y) continue;

			if (dist[i] == MAX) {
				System.out.println(-1);
				return;
			}

			int rt = dist[i] * 2;
			if (rt > X) {
				System.out.println(-1);
				return;
			}
			roundTrips.add(rt);
		}

		Collections.sort(roundTrips);

		int days = 0;
		int today = 0;
		for (int rt : roundTrips) {
			if (today + rt > X) {
				days++;
				today = 0;
			}
			today += rt;
		}
		if (today > 0) days++;

		System.out.println(days);
	}

	public static void dijkstra(int start, List<List<Node>> adj) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int u = cur.e;

			if (visited[u]) continue;
			visited[u] = true;

			for (Node next : adj.get(u)) {
				if (dist[next.e] > dist[u] + next.d) {
					dist[next.e] = dist[u] + next.d;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}
	}

	public static class Node implements Comparable<Node> {
		int e, d;

		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.d, o.d);
		}
	}
}
