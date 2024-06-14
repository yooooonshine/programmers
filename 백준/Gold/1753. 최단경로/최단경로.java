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
		dikstra(adj, visit, distances, V);

		for (int i = 1; i <= V; i++) {
			if (distances[i] == 999999999) {
				System.out.println("INF");
			} else {
				System.out.println(distances[i]);
			}
		}
	}

	public static void dikstra(List<List<Edge>> adj, boolean[] visit, int[] distances, int V) {
		while(true) {
			int min = 999999999;
			int node = 0;
			for (int i = 1; i <= V; i++) {
				if (!visit[i] && distances[i] < min) {
					min = distances[i];
					node = i;
				}
			}
			if (node == 0) {
				return;
			}

			visit[node] = true;

			for (Edge edge : adj.get(node)) {
				int adjNode = edge.getNode();
				int adjDist = edge.getDist();

				if (distances[adjNode] >  adjDist + distances[node]) {
					distances[adjNode] = adjDist + distances[node];
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
