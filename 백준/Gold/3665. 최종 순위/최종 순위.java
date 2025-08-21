
import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 팀의 수

			int[] lastRate = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				lastRate[n] = Integer.parseInt(st.nextToken()); // 마지막 순위
			}

			boolean[][] adj = new boolean[N + 1][N + 1];
			for (int n = 1; n <= N; n++) {
				for (int j = n + 1; j <= N; j++) {
					adj[lastRate[n]][lastRate[j]] = true; // 초기화
				}
			}

			int[] teamIn = new int[N + 1];
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (adj[r][c]) {
						teamIn[c]++;
					}
				}
			}

			int M = Integer.parseInt(br.readLine()); // 순위 정보의 개수
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 팀 A
				int b = Integer.parseInt(st.nextToken()); // 팀 B

				swap(teamIn, a, b, adj);
			}

			topologySort(adj, teamIn);

		}
	}

	public static void swap(int[] degree, int a, int b, boolean[][] adj) {
		if (adj[a][b]) {
			// a -> b
			adj[a][b] = false;
			adj[b][a] = true;
			degree[a]++;
			degree[b]--;
		} else {
			// b -> a
			adj[b][a] = false;
			adj[a][b] = true;
			degree[b]++;
			degree[a]--;
		}
	}

	public static void topologySort(boolean[][] adj, int[] teamIn) {

		boolean[] visited = new boolean[teamIn.length];

		Queue<Integer> resultQ = new LinkedList<>();
		Queue<Integer> myQ = new LinkedList<>();

		for (int n = 1; n <= N; n++) {
			if (teamIn[n] == 0) {
				myQ.add(n);
			}
		}

		while (!myQ.isEmpty()) {
			if (myQ.size() > 1) {
				System.out.println("?");
				return;
			}

			int node = myQ.poll();
			resultQ.add(node);

			visited[node] = true;

			for (int index = 1; index <= N; index++) {
				if (adj[node][index]) {
					teamIn[index]--;

					if (teamIn[index] == 0) {
						myQ.add(index);
					}
				}
			}
		}

		for (int n = 1; n <= N; n++) {
			if (!visited[n]) {
				System.out.println("IMPOSSIBLE");
				return;
			}
		}

		while (!resultQ.isEmpty()) {
			System.out.print(resultQ.poll() + " ");
		}
		System.out.println();
	}
}