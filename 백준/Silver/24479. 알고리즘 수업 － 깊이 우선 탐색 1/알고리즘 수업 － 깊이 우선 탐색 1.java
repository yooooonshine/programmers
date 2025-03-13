import java.util.*;
import java.io.*;

public class Main {

	public static int count = 1;
	public static int[] order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // M개의 간선
		int R = Integer.parseInt(st.nextToken()); // 시작 정점

		List<List<Edge>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(b));
			adj.get(b).add(new Edge(a));
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adj.get(i));
		}

		order = new int[N + 1];

		dfs(adj, R, N);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= N; i++) {
			bw.write(order[i] + " \n");
		}
		bw.flush();
	}

	public static void dfs(List<List<Edge>> adj, int v, int N) {
		Stack<Edge> stack = new Stack<>();
		stack.push(new Edge(v));

		boolean[] visit = new boolean[N + 1];

		while (!stack.isEmpty()) {
			Edge e = stack.pop();
			int cur = e.e;

			if (visit[cur]) {
				continue;
			}

			visit[cur] = true;
			order[cur] = count;
			count++;

			for (int i = adj.get(cur).size() - 1; i >= 0; i--) {
				Edge next = adj.get(cur).get(i);

				if (!visit[next.e]) {
					stack.push(next);
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int e;

		public Edge(int e) {
			this.e = e;
		}

		@Override
		public int compareTo(Edge o) {
			return this.e - o.e;
		}
	}
}

// N개의 정점
// M개의 간선
// 정점 R에서 깊이 우선