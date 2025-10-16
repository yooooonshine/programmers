import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 집의 수
		int M = Integer.parseInt(st.nextToken()); // 길의 수

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pq.add(new Edge(a, b, c));
		}

		// 유니온파인드 배열
		int[] team = new int[N + 1];
		for (int n = 0; n <= N; n++) {
			team[n] = n;
		}

		// mst
		int findCount = 0;
		int sum = 0;

		while (!pq.isEmpty()) {
			if (findCount == N - 2) {
				// 두 집단 까지. n - 1이 전체

				break;
			}


			Edge now = pq.poll();
			int a = now.a;
			int b = now.b;
			int d = now.d;

			if (find(team, a) != find(team, b)) {
				union(team, a, b);
				sum += d;
				findCount++;
			}
		}

		System.out.println(sum);
	}

	public static void union(int[] arr, int v1, int v2) {
		int rV1 = find(arr, v1);
		int rV2 = find(arr, v2);

		if (rV1 > rV2) {
			arr[rV1] = rV2;
		} else if (rV1 < rV2) {
			arr[rV2] = rV1;
		}
	}

	public static int find(int[] arr, int v) {
		if (arr[v] == v) {
			return v;
		}

		return arr[v] = find(arr, arr[v]);
	}

	public static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int d;

		public Edge(int a, int b, int d) {
			this.a = a;
			this.b = b;
			this.d = d;
		}

		@Override
		public int compareTo(Edge e) {
			return this.d - e.d;
		}
	}
}

// N개의 집
// M개의 길(양방향), 유지비, 경로 항상 존재

// 집이 하나 이상, 두 집 사이 경로는 항상 존재

// mst
