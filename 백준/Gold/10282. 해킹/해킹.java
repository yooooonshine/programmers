import java.util.*;
import java.io.*;

public class Main {

	public static int MAX = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 컴퓨터 수
			int d = Integer.parseInt(st.nextToken()); // 의존성 수
			int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터

			List<List<Edge>> adj = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				adj.add(new ArrayList<>());
			}

			for (int i = 1; i <= d; i++) {
				st = new StringTokenizer(br.readLine());
				int e = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				adj.get(s).add(new Edge(e, time));
			}

			Result result = djk(adj, n, c);

			System.out.println(result.number + " " + result.time);
		}
	}

	public static Result djk(List<List<Edge>> adj, int n, int c) {
		int[] dist = new int[n + 1]; // 거리
		boolean[] visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			dist[i] = MAX;
		}
		dist[c] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(c, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int nE = edge.e;
			int nD = edge.d;

			if (visit[nE]) {
				continue;
			}
			visit[nE] = true;

			for (Edge adjE : adj.get(nE)) {
				if (dist[adjE.e] > dist[nE] + adjE.d) {
					dist[adjE.e] = dist[nE] + adjE.d;

					pq.add(new Edge(adjE.e, dist[adjE.e]));
				}
			}
		}

		int max = 0;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] != MAX) {
				count++;
			}
			if (dist[i] > max && dist[i] != MAX) {
				max = dist[i];
			}
		}

		return new Result(count, max);
	}

	public static class Result {
		int number;
		int time;

		public Result(int n, int t) {
			this.number = n;
			this.time = t;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int e;
		int d;

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Edge e) {
			return this.d - e.d;
		}
	}
}

// 의존하는 컴퓨터 전염. 의존하는 쪽이 감염당한다.

// 걸리는 시간 및 총 몇대 감염??

// T 테스트케이스 수
// n d c(컴퓨터 개수, 의존성 개수, 해킹당한 컴퓨터 번호)
// d개의 줄
// a b s(a가 b에 의존 a <- b) s초 후 a 감염

// 2 <- 1
// \
// 3 <-

// 감염시간 == 이동시간.

// 다익스트라
// 우선순위 큐 빌 때까지
// 큐에서 빼서 주위 둘러보고 dist 더 작으면 갱신)