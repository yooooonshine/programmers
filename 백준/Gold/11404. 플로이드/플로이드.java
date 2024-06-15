import java.util.*;
import java.io.*;

class Main {

	public static long inf = 999999999L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		long[][] adj = new long[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(adj[i], inf);
		}
		for (int i = 0; i <= n; i++) {
			adj[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long dist = Long.parseLong(st.nextToken());

			if (adj[start][end] > dist) {
				adj[start][end] = dist;
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int s = 1; s <= n; s++) {
				for (int e = 1; e <= n; e++) {
					if (adj[s][e] > adj[s][k] + adj[k][e]) {
						adj[s][e] = adj[s][k] + adj[k][e];
					}
				}
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (adj[i][j] == inf) {
					bw.write(0 + " ");
				} else {
					bw.write(adj[i][j] + " ");
				}
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}
