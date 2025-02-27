
import java.util.*;
import java.io.*;

public class Main {
	public static int[][] wDist;
	public static int[] aDist;

	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());// V수
		int M = Integer.parseInt(st.nextToken()); // 오솔길 수

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(e, d * 2));
			adj.get(e).add(new Edge(s, d * 2));
		}

		wDist = new int[N + 1][2];
		aDist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			wDist[i][0] = 1000000000;
			wDist[i][1] = 1000000000;
		}

		Arrays.fill(aDist, 1000000000);
		wDist[1][0] = 0; // 느린 상태로 도착, 다음 빠름
		aDist[1] = 0;

		wD();
		aD();

		int count = 0;
		for (int i = 2; i <= N; i++) {
			if (wDist[i][0] > aDist[i] && wDist[i][1] > aDist[i]) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static void wD() {
		PriorityQueue<Wolf> pq = new PriorityQueue<>();
		pq.add(new Wolf(1, 0, true));

		while (!pq.isEmpty()) {
			Wolf wolf = pq.poll();

			int nE = wolf.e;
			boolean fast = wolf.fast;

			if (fast) {
				if (wDist[nE][0] < wolf.d) {
					continue;
				}
			} else {
				if (wDist[nE][1] < wolf.d) {
					continue;
				}
			}

			for (Edge edge : adj.get(nE)) {
				if (fast) {
					if (wDist[edge.e][1] > wDist[nE][0] + edge.d / 2) {
						wDist[edge.e][1] = wDist[nE][0] + edge.d / 2;
						pq.add(new Wolf(edge.e, wDist[edge.e][1], false));
					}
				} else {
					if (wDist[edge.e][0] > wDist[nE][1] + edge.d * 2) {
						wDist[edge.e][0] = wDist[nE][1] + edge.d * 2;
						pq.add(new Wolf(edge.e, wDist[edge.e][0], true));
					}
				}
			}
		}
	}

	// 여우 다익스트라
	public static void aD() {
		PriorityQueue<Ari> pq = new PriorityQueue<>();
		pq.add(new Ari(1,0));

		while (!pq.isEmpty()) {
			Ari ari = pq.poll();

			int nE = ari.e;

			if (aDist[nE] < ari.d) {
				continue;
			}

			for (Edge edge : adj.get(nE)) {
				if (aDist[edge.e] > aDist[nE] + edge.d) {
					aDist[edge.e] = aDist[nE] + edge.d;
					pq.add(new Ari(edge.e, aDist[edge.e]));
				}
			}
		}
	}

	public static class Edge {
		int e;
		int d;

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}
	}

	public static class Wolf implements Comparable<Wolf> {
		int e;
		int d;
		boolean fast;

		public Wolf(int e, int d, boolean fast) {
			this.e = e;
			this.d = d;
			this.fast = fast;
		}

		@Override
		public int compareTo(Wolf wolf) {
			return (this.d - wolf.d);
		}
	}

	public static class Ari implements Comparable<Ari> {
		int e;
		int d;

		public Ari(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Ari ari) {
			return (this.d - ari.d);
		}
	}
}
