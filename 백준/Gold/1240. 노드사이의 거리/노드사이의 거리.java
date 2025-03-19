import java.util.*;
import java.io.*;

public class Main {
	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 노드의 수
		int M = Integer.parseInt(st.nextToken()); // 노드 쌍의 개수

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj.get(node1).add(new Edge(node2, dist));
			adj.get(node2).add(new Edge(node1, dist));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			int result = bfs(node1, node2, N);

			bw.write(result + "\n");
		}
		bw.flush();
	}

	public static int bfs(int s, int e, int N) {
		boolean[] visit = new boolean[N + 1];

		Queue<Edge> myQ = new LinkedList<>();
		myQ.add(new Edge(s, 0));

		while(!myQ.isEmpty()) {
			Edge nEdge = myQ.poll();

			int nE = nEdge.e;
			int nD = nEdge.d;

			if (nE == e) {
				return nD;
			}

			if (visit[nE]) {
				continue;
			}
			visit[nE] = true;

			for (Edge next : adj.get(nE)) {
				myQ.add(new Edge(next.e, nD + next.d));
			}
		}

		return 0;
	}

	public static class Edge {
		int e;
		int d;

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}
	}
}

