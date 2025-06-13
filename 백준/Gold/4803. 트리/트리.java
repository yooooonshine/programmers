
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int index = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점의 개수
			M = Integer.parseInt(st.nextToken()); // 간선의 개수

			if (N == 0 && M == 0) {
				break;
			}

			List<Edge> edges = new ArrayList<>();
			for (int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				edges.add(new Edge(s, e));
			}

			int[] parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i; // 초기화
			}

			for (Edge edge : edges) {
				int s = edge.s;
				int e = edge.e;

				if (find(s, parent) == find(e, parent)) {
					// 사이클이 발생하는 경우
					parent[find(s, parent)] = 0; // 사이클이 발생한 노드의 루트는 0으로 설정
				} else {
					union(s, e, parent); // 사이클이 없는 경우 union
				}
			}
			int result = 0;
			Set<Integer> uniqueRoots = new HashSet<>();
			for (int n = 1; n <= N; n++) {
				if (find(n,parent) != 0) { // 사이클이 없는 노드만 고려
					int root = find(n, parent);
					if (!uniqueRoots.contains(root)) {
						uniqueRoots.add(root);
						result++;
					}
				}
			}

			if (result == 0) {
				bw.write("Case " + index + ": No trees.\n");
			} else if (result == 1) {
				bw.write("Case " + index + ": There is one tree.\n");
			} else {
				bw.write("Case " + index + ": A forest of " + result + " trees.\n");
			}


			index++;
		}

		bw.flush();
	}

	public static void union(int a, int b, int[] parent) {
		int rA = find(a, parent);
		int rB = find(b, parent);

		if (rA > rB) {
			parent[rA] = rB;
		} else if (rA < rB) {
			parent[rB] = rA;
		}
	}

	public static int find(int a, int[] parent) {
		if (parent[a] == 0) {
			return 0; // 사이클이 발생한 노드의 루트는 0
		}
		
		if (parent[a] == a) {
			return a;
		}

		return find(parent[a], parent);
	}

	public static class Edge {
		int s;
		int e;

		public Edge(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}

