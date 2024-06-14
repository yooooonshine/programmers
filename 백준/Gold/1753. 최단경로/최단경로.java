import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		List<List<Edge>> adj = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			adj.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj.get(u).add(new Edge(v, w));
		}

		int[] distances = new int[V + 1];
		Arrays.fill(distances, 999999999);
		distances[start] = 0;

		boolean visit[] = new boolean[V + 1];
		dikstra(adj, visit, distances, start);

		for (int i = 1; i <= V; i++) {
			if (distances[i] == 999999999) {
				System.out.println("INF");
			} else {
				System.out.println(distances[i]);
			}
		}
	}

	public static void dikstra(List<List<Edge>> adj, boolean[] visit, int[] distances, int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
			int node1 = o1.getNode();
			int dist1 = o1.getDist();
			int node2 = o2.getNode();
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

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int now = edge.getNode();
			int nowDist = edge.getDist();

			if (visit[now]) {
				continue;
			}

			visit[now] = true;

			for (Edge adjEdge : adj.get(now)) {
				int adjNode = adjEdge.getNode();
				int adjDist = adjEdge.getDist();

				if (distances[adjNode] >  adjDist + nowDist) {
					distances[adjNode] = adjDist +  nowDist;
					pq.add(new Edge(adjNode, distances[adjNode]));
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
		return node;
	}

	public int getDist() {
		return dist;
	}
}