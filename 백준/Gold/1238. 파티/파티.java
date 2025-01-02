import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Edge>> adj;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생
		int M = Integer.parseInt(st.nextToken()); // 도로
		int X = Integer.parseInt(st.nextToken()); // 파티 장소

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(e, w));
		}

		int[] min =  new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}

			int[] tmp = dj(i);
			min[i] = tmp[X];
		}

		int[] tmp2 = dj(X);
		for (int i = 1; i <= N; i++) {

			min[i] += tmp2[i];
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (max < min[i]) {
				max = min[i];
			}
		}

		System.out.println(max);
	}

	public static int[] dj(int s) {
		boolean[] visit = new boolean[N + 1];
		int[] dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));

		while (!pq.isEmpty()) {
			Edge tmp = pq.poll();

			int e = tmp.e;

			if (visit[e]) {
				continue;
			}
			visit[e] = true;

			for (Edge edge : adj.get(e)) {

				if (dist[edge.e] > dist[e] + edge.w) {
					dist[edge.e] = dist[e] + edge.w;
					pq.add(new Edge(edge.e, dist[edge.e]));
				}
			}
		}

		return dist;
	}

	public static class Edge implements Comparable<Edge> {
		int e;
		int w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}

		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
}


// N개의 마을
//  X번 마을에서 파티
// 단방향 도로 M개
// i번쨰 길은 Ti
// 그래프네
// 오고 가는데 가장많이 시간을 소비하는 학생
// N, X
// M번줄동안 시작점, 끝점, 소모시간

// 플로이드 워셜은 안되네..??
// 벨만은 O(VE)
// 다익스트라 O(VlongV) 가능 여러번도 가능하네
// 이짓 n번 더 할 수 있다.
// 파티장소에서 모든 다른 곳까지 -> nlogn

// 다익스트라
// 거리, 자신 빼고 무한대
// 방문 체크
// 우선순위 큐
// 큐빌때까지
// 방문 체크
// 인접노드에 대해서
// 만약 s e 거리가 s m과 w거리 비교

