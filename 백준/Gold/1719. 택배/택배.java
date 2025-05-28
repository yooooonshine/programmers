import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Edge>> adj;

	public static int[][] result;

	public static int N; // 잡하장 개수
	public static int M; // 경로 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 잡하장 개수
		M = Integer.parseInt(st.nextToken()); // 경로 개수

		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		// 길 초기화
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(e, d));
			adj.get(e).add(new Edge(s, d));
		}

		// 결과 배열 생성
		result = new int[N + 1][N + 1];

		// 다익스트라
		for (int n = 1; n <= N; n++) {
			djk(n);
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == c) {
					bw.write("- ");
				} else {
					bw.write(result[r][c] + " ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
	}

	public static void djk(int start) {
		int[] before = new int[N + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N + 1];
		int[] dist = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			if (n != start) {
				dist[n] = 1000000;
			}
		}

		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int nowE = edge.e;

			if (visit[nowE]) {
				continue;
			}
			visit[nowE] = true;

			for (Edge next : adj.get(nowE)) {
				if (dist[next.e] > dist[nowE] + next.d) {
					before[next.e] = nowE;
					dist[next.e] = dist[nowE] + next.d;
					pq.add(new Edge(next.e, dist[next.e]));
				}
			}
		}

		for (int n = 1; n <= N; n++) {
			if (n == start) {
				continue;
			}

			int now = n;
			while (before[now] != start) {
				now = before[now];
			}
			result[start][n] = now;
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


// 경로표로 정리
// 한 잡화장에서 다른 잡화장으로 최단 경로로 이동하기 위해 가장 먼저 거쳐야하는 잡화장

// 모든 지점에서 모든 지점 이동 -> 이는플로이드 워셜인데
// 이건 절대 경로를 알 수 없어...

// 다익스트라?
// 시간복잡도 O(elog(v)) 10000 * 200
// 모든 출발점 다익스트라 사용해도 괜찮다.

// 이전에 방문한 곳을 저장
// 이를 통해 for문을 돌면서,
// for에 index에 대해, 계속 들어가면서 다음이 시작 start이면 그것을
// result(start, index) 에 기록