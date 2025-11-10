import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Edge>> adj;
	public static int N;
	public static int M;
	public static int A;
	public static int B;
	public static int C;

	public static int min = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 교차로 수
		M = Integer.parseInt(st.nextToken()); // 골목 수
		A = Integer.parseInt(st.nextToken()); // 시작
		B = Integer.parseInt(st.nextToken()); // 끝
		C = Integer.parseInt(st.nextToken()); // 돈

		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
		}

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj.get(v1).add(new Edge(v2, c));
			adj.get(v2).add(new Edge(v1, c));
		}

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[A] = 0;
		PriorityQueue<Edge2> pq = new PriorityQueue<>();
		pq.add(new Edge2(A, 0, 0));

		while (!pq.isEmpty()) {
			Edge2 now = pq.poll();
			int nowE = now.e;
			int nowC = now.c;

			if (now.d > C) {
				continue;
			}

			for (Edge next : adj.get(nowE)) {
				int nextE = next.e;
				int nextC = next.c;

				int maxC = Math.max(nowC, nextC);
				if (dist[nextE] > maxC && now.d + nextC <= C) {
					dist[nextE] = maxC;
					pq.add(new Edge2(nextE, maxC, now.d + nextC));
				}
			}
		}

		if (dist[B] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dist[B]);
		}
	}

	public static class Edge {
		int e;
		int c;

		public Edge(int e, int c) {
			this.e = e;
			this.c = c;
		}
	}

	public static class Edge2 implements Comparable<Edge2> {
		int e;
		int c;
		int d;

		public Edge2(int e, int c, int d) {
			this.e = e;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Edge2 o) {
			return this.c - o.c;
		}
	}
}

// N개의 교차로 (1 ~ N), 비용존재
// M 개의 골목, 양방향, 하나만 존재

// A -> B로 C원으로

// 가장 많이 낸 돈을 적게

// 한 골목의 최대 요금의 최솟값
// 갈수 없으면 -1

// 갈 수 있는 경로 중에서 -> 그 경로의 최댓값이 최소인 값

// dfs일거가은데
// 백트래킹

// 다익스트라로 비용을 모두 저장하며
// 다익스트라 거리배열에너는 최대를 저장?