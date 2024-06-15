import java.util.*;
import java.io.*;

class Main {

	public static long inf = 999999999L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		long[][] adj = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				adj[i][j] = Long.parseLong(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adj[i][j] == 0L) {
					adj[i][j] = inf;
				}
			}
		}

		for (int k = 1; k <= N; k++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {
					if (adj[s][e] > adj[s][k] + adj[k][e]) {
						adj[s][e] = adj[s][k] + adj[k][e];
					}
				}
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adj[i][j] == inf) {
					bw.write(0 + " ");
				} else {
					bw.write(1 + " ");
				}
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}
