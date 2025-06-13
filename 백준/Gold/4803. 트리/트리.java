
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

			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adj.add(new ArrayList<>());
			}

			for (int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				adj.get(s).add(e);
				adj.get(e).add(s); // 무방향 그래프이므로 양쪽에 추가
			}

			int result = 0;
			boolean[] visit = new boolean[N + 1];
			for (int n = 1; n <= N; n++) {
				// 루트노드만 탐색
				boolean isTree = bfs(n, adj, visit);

				if (isTree) {
					result++;
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

	public static boolean bfs(int s, List<List<Integer>> adj, boolean[] visit) {
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(0, s));

		while (!q.isEmpty()) {
			Edge edge = q.poll();
			int nowS = edge.s;
			int nowN = edge.e;

			if (visit[nowN]) {
				return false;
			}
			visit[nowN] = true;

			for (Integer next : adj.get(nowN)) {
				if (nowS == next) {
					continue; // 자기 자신으로 돌아가는 간선은 무시
				}
				q.add(new Edge(nowN, next));
			}
		}
		return true;
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

