import java.util.*;
import java.io.*;

public class Main {

	public static int M;
	public static int N;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int cityCount = Integer.parseInt(st.nextToken()); // 도시의 수
		int busCount = Integer.parseInt(st.nextToken()); // 버스 노선

		List<Edge> edges = new ArrayList<>();
		for (int m = 1; m <= busCount; m++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long d = Integer.parseInt(st.nextToken());
			edges.add(new Edge(s, e, d));
		}

		long[] dist = new long[cityCount + 1];
		for (int i = 1; i <= cityCount; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;

		for (int i = 1; i < cityCount; i++) {
			for (Edge edge : edges) {
				int s = edge.s;
				int e = edge.e;
				long d = edge.d;

				if (dist[s] == Integer.MAX_VALUE) {
					continue;
				}

				if (dist[s] + d < dist[e]) {
					dist[e] = dist[s] + d;
				}
			}
		}

		long[] result1 = new long[cityCount + 1];
		for (int i = 1; i <= cityCount; i++) {
			result1[i] = dist[i];
		}

		for (Edge edge : edges) {
			int s = edge.s;
			int e = edge.e;
			long d = edge.d;

			if (dist[s] == Integer.MAX_VALUE) {
				continue;
			}

			if (dist[s] + d < dist[e]) {
				dist[e] = dist[s] + d;
			}
		}

		boolean correct = true;
		for (int i = 1; i <= cityCount; i++) {
			if (result1[i] != dist[i]) {
				correct = false;
			}
		}

		if (!correct) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= cityCount; i++) {
				if (dist[i] == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else {
					System.out.println(dist[i]);
				}
			}
		}
	}

	public static class Edge {
		int s;
		int e;
		long d;

		public Edge(int s, int e, long d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}
	}
}
// 무한대 변경 -> -1
// 아니면 경로