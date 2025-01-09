import java.util.*;
import java.io.*;

public class Main {

	public static int V;
	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			adj.add(new ArrayList<>());
		}

		StringTokenizer st;
		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());

			int node = Integer.parseInt(st.nextToken());

			while (true) {
				int a = Integer.parseInt(st.nextToken());

				if (a == -1) {
					break;
				}
				int b = Integer.parseInt(st.nextToken());


				adj.get(node).add(new Edge(a, b));
			}
		}

		Edge edge1 = dfs(1);
		Edge edge2 = dfs(edge1.e);

		System.out.println(edge2.w);
	}

	public static Edge dfs(int index) {
		int resultI = 0;
		int max = 0;

		boolean[] visit = new boolean[V + 1];

		Stack<Edge> myS = new Stack<>();
		myS.add(new Edge(index, 0));

		while(!myS.isEmpty()) {
			Edge edge = myS.pop();
			int e = edge.e;
			int w = edge.w;

			if (visit[e]) {
				continue;
			}
			visit[e] = true;

			if (w > max) {
				resultI = e;
				max = w;
			}

			for (Edge tmpE : adj.get(e)) {
				myS.add(new Edge(tmpE.e, w + tmpE.w));
			}
		}

		return new Edge(resultI, max);
	}



	public static class Edge {
		int e;
		int w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
}

// 트리의 정점 수 V
// V개의 줄에 간선 정보
// 트리의 지름? dfs 두번해서 가장 깊은 곳에서 반대 끝

// 깊이
