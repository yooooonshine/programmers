import java.util.*;
import java.io.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<Edge> adj = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj.add(new Edge(s, e, w));
		}

		Collections.sort(adj, (o1, o2) -> {
			int w1 = o1.getW();
			int w2 = o2.getW();

			if (w1 > w2) {
				return 1;
			} else if (w1 == w2) {
				return 0;
			} else {
				return -1;
			}
		});

		int[] group = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			group[i] = i;
		}

		int count = 0;
		int sum = 0;
		for (Edge edge : adj) {
			if (count >= V - 1) {
				break;
			}
			int s = edge.getS();
			int e = edge.getE();
			int w = edge.getW();

			if (find(group, s) == find(group, e)) {
				continue;
			}
			union(group, s, e);
			sum += w;
			count++;
		}

		System.out.println(sum);
	}

	public static int find(int[] group, int node) {
		if (group[node] == node) {
			return node;
		} else {
			return group[node] = find(group, group[node]);
		}
	}

	public static void union(int[] group, int node1, int node2) {
		int p1 = find(group, node1);
		int p2 = find(group, node2);

		if (p1 == p2) {
			return;
		} else if (p1 < p2) {
			group[p2] = p1;
		} else {
			group[p1] = p2;
		}
	}
}

class Edge {
	private int s;
	private int e;
	private int w;

	public Edge (int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}

	public int getS() {
		return s;
	}

	public int getE() {
		return e;
	}

	public int getW() {
		return w;
	}
}
