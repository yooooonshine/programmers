import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj.get(s).add(e);
			adj.get(e).add(s);
		}

		int[] parent = new int[N + 1];
		boolean[] visit = new boolean[N + 1];

		dfs(adj, visit, parent, 1);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 2; i <= N; i++) {
			bw.write(parent[i] + "\n");
		}

		bw.flush();
		bw.close();
	}

	public static void dfs(List<List<Integer>> adj, boolean[] visit, int[] parent, int start) {
		if (visit[start]) {
			return;
		}

		visit[start] = true;

		List<Integer> adjNodes = adj.get(start);
		for (int node : adjNodes) {
			if (visit[node]) {
				continue;
			}

			parent[node] = start;
			dfs(adj, visit, parent, node);
		}
	}
}
