import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 미로의 크기
		int M = Integer.parseInt(st.nextToken()); // 열쇠의 개수

		List<int[]> nodes = new ArrayList<>(); // S + K 들의 좌표 저장
		String[][] road = new String[N][N];

		for (int r = 0; r < N; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 0; c < N; c++) {
				road[r][c] = tmp[c];
				if (road[r][c].equals("S") || road[r][c].equals("K")) {
					nodes.add(new int[]{r, c});
				}
			}
		}

		int size = nodes.size(); // M + 1
		int[][] adj = new int[size][size]; // 거리 저장
		for (int i = 0; i < size; i++) Arrays.fill(adj[i], Integer.MAX_VALUE);

		// 각 복제 지점에서 다른 지점까지 거리 계산
		for (int i = 0; i < size; i++) {
			bfs(i, nodes, road, adj, N);
		}

		// 간선 구성
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (adj[i][j] != Integer.MAX_VALUE) {
					edges.add(new Edge(i, j, adj[i][j]));
				} else {
					System.out.println("-1");
					return;
				}
			}
		}

		Collections.sort(edges);

		// 크루스칼
		int[] parent = new int[size];
		for (int i = 0; i < size; i++) parent[i] = i;

		int sum = 0;
		for (Edge edge : edges) {
			if (find(parent, edge.s) != find(parent, edge.e)) {
				union(parent, edge.s, edge.e);
				sum += edge.d;
			}
		}

		System.out.println(sum);
	}

	static void bfs(int idx, List<int[]> nodes, String[][] road, int[][] adj, int N) {
		int[][] dist = new int[N][N];
		for (int[] row : dist) Arrays.fill(row, -1);

		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();

		int[] start = nodes.get(idx);
		q.add(start);
		visited[start[0]][start[1]] = true;
		dist[start[0]][start[1]] = 0;

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visited[nr][nc] || road[nr][nc].equals("1")) continue;

				visited[nr][nc] = true;
				dist[nr][nc] = dist[r][c] + 1;
				q.add(new int[]{nr, nc});
			}
		}

		for (int j = 0; j < nodes.size(); j++) {
			if (j == idx) continue;
			int[] target = nodes.get(j);
			int d = dist[target[0]][target[1]];
			if (d != -1) adj[idx][j] = d;
		}
	}

	static class Edge implements Comparable<Edge> {
		int s, e, d;

		public Edge(int s, int e, int d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.d, o.d);
		}
	}

	static int find(int[] parent, int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent, parent[x]);
	}

	static void union(int[] parent, int a, int b) {
		int rootA = find(parent, a);
		int rootB = find(parent, b);
		if (rootA != rootB) parent[rootB] = rootA;
	}
}