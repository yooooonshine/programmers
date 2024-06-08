

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //노드 개수
		int M = Integer.parseInt(st.nextToken()); //에지 개수
		int V = Integer.parseInt(st.nextToken()); //탐색 시작 노드

		boolean[][] adj = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1][node2] = true;
			adj[node2][node1] = true;
		}

		List<Integer> dfsResults = new ArrayList<>();
		boolean[] visit = new boolean[N + 1];
		dfs(adj, N, visit, V, dfsResults);


		List<Integer> bfsResults = new ArrayList<>();
		visit = new boolean[N + 1];
		bfs(adj, N, visit, V, bfsResults);


		for (int i = 0; i < dfsResults.size(); i++) {
			if (i + 1 == dfsResults.size()) { // 마지막 i일 때만
				System.out.print(dfsResults.get(i));
			} else {
				System.out.print(dfsResults.get(i)+" ");
			}
		}
		System.out.println("");
		for (int i = 0; i < bfsResults.size(); i++) {
			if (i + 1 == bfsResults.size()) { // 마지막 i일 때만
				System.out.print(bfsResults.get(i));
			} else {
				System.out.print(bfsResults.get(i)+" ");
			}
		}
	}

	public static void dfs(boolean[][] adj, int N, boolean[] visit, int node, List<Integer> results) {

		if (visit[node]) {
			return;
		}
		visit[node] = true;
		results.add(node);

		for (int i = 1; i <= N; i++) {
			if (adj[node][i] && !visit[i]) {
				dfs(adj, N, visit, i, results);
			}
		}
	}

	public static void bfs(boolean[][] adj, int N, boolean[] visit, int node, List<Integer> results) {
		Queue<Integer> myQueue = new LinkedList<>();

		myQueue.add(node);

		while (!myQueue.isEmpty()) {
			int tmpNode = myQueue.poll();

			if (visit[tmpNode]) {
				continue;
			}
			visit[tmpNode] = true;
			results.add(tmpNode);

			for (int i = 1; i <= N; i++) {
				if (adj[tmpNode][i] && !visit[i]) {
					myQueue.add(i);
				}
			}
		}
	}
}

