import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int count = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());

			if (N == 0) {
				break;
			}

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			distance[0][0] = arr[0][0];

			boolean[][] visit = new boolean[N][N];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(0,0,arr[0][0]));

			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				int r = edge.r;
				int c = edge.c;

				if (visit[r][c]) {
					continue;
				}
				visit[r][c] = true;

				if (r + 1 <= N - 1) {
					if (distance[r + 1][c] > distance[r][c] + arr[r + 1][c]) {
						distance[r + 1][c] = distance[r][c] + arr[r + 1][c];
						pq.add(new Edge(r + 1, c, distance[r + 1][c]));
					}
				}

				if (r - 1 >= 0) {
					if (distance[r - 1][c] > distance[r][c] + arr[r - 1][c]) {
						distance[r - 1][c] = distance[r][c] + arr[r - 1][c];
						pq.add(new Edge(r - 1, c, distance[r - 1][c]));
					}
				}

				if (c - 1 >= 0) {
					if (distance[r][c - 1] > distance[r][c] + arr[r][c - 1]) {
						distance[r][c - 1] = distance[r][c] + arr[r][c - 1];
						pq.add(new Edge(r, c - 1, distance[r][c - 1]));
					}
				}

				if (c + 1 <= N - 1) {
					if (distance[r][c + 1] > distance[r][c] + arr[r][c + 1]) {
						distance[r][c + 1] = distance[r][c] + arr[r][c + 1];
						pq.add(new Edge(r, c + 1, distance[r][c + 1]));
					}
				}
			}

			System.out.println("Problem " + count + ": " + distance[N - 1][N - 1]);
			count++;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int r;
		int c;
		int dist;

		public Edge(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		public int compareTo(Edge e) {
			return this.dist - e.dist;
		}
	}
}

// 0,0 에서 N-1, N-1로 이동해야 한다.
// 잃는 최소 금액 -> 최단 거리

// 여러번의 tc
// N
// N개의 줄 배열
// 끝은 0

// 출력 형식 제한!

// 다익스트라
// pq
// 방문 배열
// pq 빌 때까지
// 빼오기
// 방문 확인
// 인접 상하좌우
// distance[e] > distance[s] + w
