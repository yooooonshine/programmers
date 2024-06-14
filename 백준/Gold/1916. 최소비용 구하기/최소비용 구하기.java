import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); //도시 개수
		int M = Integer.parseInt(br.readLine()); //버스 개수

		List<List<Edge>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj.get(start).add(new Edge(end, dist));
		}

		boolean[] visit = new boolean[N + 1];
		int[] distance = new int[N + 1];
		Arrays.fill(distance, 999999999);

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(adj, visit, distance, start);

		System.out.println(distance[end]);

	}

	public static void dijkstra(List<List<Edge>> adj, boolean[] visit, int[] distance, int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
			int dist1 = o1.getDist();
			int dist2 = o2.getDist();

			if (dist1 > dist2) {
				return 1;
			} else if (dist1 == dist2) {
				return 0;
			} else {
				return -1;
			}
		});

		pq.add(new Edge(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			int nowNode = now.getNode();
			int nowDist = now.getDist();

			if (visit[nowNode]) {
				continue;
			}
			visit[nowNode] = true;
			for (Edge i : adj.get(nowNode)) {
				int visitNode = i.getNode();
				int visitDist = i.getDist();

				if (distance[visitNode] > nowDist + visitDist) {
					distance[visitNode] = nowDist + visitDist;
					pq.add(new Edge(visitNode, distance[visitNode]));
				}
			}
		}
	}
}

class Edge {
	private int node;
	private int dist;

	public Edge(int node, int dist) {
		this.node = node;
		this.dist = dist;
	}

	public int getNode() {
		return this.node;
	}

	public int getDist() {
		return this.dist;
	}
}