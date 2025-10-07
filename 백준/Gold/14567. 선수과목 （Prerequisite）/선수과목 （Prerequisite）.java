
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;
	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 과목의 수
		M = Integer.parseInt(st.nextToken()); // 선수 조건의 수

		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		int[] in = new int[N + 1];
		int[] result = new int[N + 1];

		// 선수과목받기
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(s, e));
			in[e]++;
		}
		// 0으로 시작하기 위해 세팅
		for (int n = 1; n <= N; n++) {
			adj.get(0).add(new Edge(0, n));
			in[n]++;
		}

		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(0, 0));
		while (!q.isEmpty()) {
			Edge now = q.poll();
			int nowS = now.s;
			int nowE = now.e;

			if (nowE != 0) {
				result[nowE] = result[nowS] + 1;
			}

			for (Edge next : adj.get(nowE)) {
				in[next.e]--;

				if (in[next.e] == 0) {
					q.add(new Edge(nowE, next.e));
				}
			}
		}

		for (int n = 1; n <= N; n++) {
			System.out.print(result[n] + " ");
		}
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