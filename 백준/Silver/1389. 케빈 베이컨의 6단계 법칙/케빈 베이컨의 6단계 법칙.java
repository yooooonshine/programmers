import java.util.*;
import java.io.*;

class Main {

	public static long inf = 999999999L;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] adj = new long[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			Arrays.fill(adj[i], inf);
		}

		for (int i = 0; i <= N; i++) {
			adj[i][i] = 0;
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			adj[start][end] = 1;
			adj[end][start] = 1;
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

		long min = inf;
		int minNode = 0;
		for (int i = 1; i <= N; i++) {
			long sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += adj[i][j];
			}

			if (sum < min) {
				min = sum;
				minNode = i;
			}
		}

		System.out.println(minNode);
	}
}
