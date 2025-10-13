
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 건물의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		long expense = 0L;
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			long d = Long.parseLong(st.nextToken());

			expense += d;
			pq.add(new Edge(a, b, d));
		}

		// 유니온 파인드 배열 초기화
		int[] team = new int[N + 1];
		for (int n = 0; n <= N; n++) {
			team[n] = n;
		}

		long sum = 0L;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int s = edge.a;
			int e = edge.b;
			long d = edge.d;

			if (find(team, s) == find(team, e)) {
				continue;
			} else {
				union(team, s, e);
				sum += d;
			}
		}

		// 연결 다 안되었으면 -1
		for (int n = 1; n < N; n++) {
			if (find(team, n) != find(team, n + 1)) {
				System.out.println("-1");
				return;
			}
		}

		System.out.println(expense - sum + " ");
	}

	public static void union(int[] team, int a, int b) {
		int rA = team[a];
		int rB = team[b];

		if (rA > rB) {
			team[rA] = rB;
		} else if (rA < rB) {
			team[rB] = rA;
		}
	}

	public static int find(int[] team, int v) {
		if (team[v] == v) {
			return v;
		}

		return team[v] = find(team, team[v]);
	}

	public static class Edge implements Comparable<Edge> {
		int a;
		int b;
		long d;

		public Edge(int a, int b, long d) {
			this.a = a;
			this.b = b;
			this.d = d;
		}

		@Override
		public int compareTo(Edge edge) {
			return (int)(this.d - edge.d);
		}
	}
}

// 양방향 도로
// 공사 비용 최소화
// 모든 건물이 도로 연결, 최소 도로 -> MST

// 얼마 절약되는지(총 - 최소)
// long써야겠네

// 연결 안되면 - 1
