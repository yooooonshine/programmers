import java.util.*;
import java.io.*;

public class Main {

	public static int n;
	public static int[][] arr;
	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, 1, -1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 2][n + 2];
		// 바꾸는 게 없으면 0반환

		for (int r = 1; r <= n; r++) {
			int[] tmp = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (int c = 1; c <= n; c++) {
				arr[r][c] = tmp[c - 1];
			}
		}
		// 1은 흰 방
		// 검은 방 0

		System.out.println(djk());
	}

	public static int djk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 1, 0));

		boolean[][] visit = new boolean[n + 2][n + 2];
		int[][] dist = new int[n + 2][n + 2];
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				dist[r][c] = 100000000;
			}
		}
		dist[1][1] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int r = node.r;
			int c = node.c;
			int d = node.d;
			if (r < 1 || r > n || c < 1 || c > n) {
				continue;
			}

			if (visit[r][c]) {
				continue;
			}
			visit[r][c] = true;

			for (int i = 0; i <= 3; i++) {
				int nextR = r + rs[i];
				int nextC = c + cs[i];

				if (nextR < 1 || nextR > n || nextC < 1 || nextC > n) {
					continue;
				}

				if (arr[nextR][nextC] == 1) {
					if (dist[nextR][nextC] > dist[r][c]) {
						dist[nextR][nextC] = dist[r][c];
						pq.add(new Node(nextR, nextC, dist[nextR][nextC]));
					}
				} else {
					if (dist[nextR][nextC] > dist[r][c] + 1) {
						dist[nextR][nextC] = dist[r][c] + 1;
						pq.add(new Node(nextR, nextC, dist[nextR][nextC]));
					}
				}
			}
		}
		return dist[n][n];
	}

	public static class Node implements Comparable<Node> {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Node node) {
			return this.d - node.d;
		}
	}

	// 일부분은 검은, 나머지는 흰,
	// 검은 방은 사면이 벽
	// 방의 색을 바꾸기

	//

	// 힌방의 비용을 0, 검은방 비용 1
	// 검은방 카운트
}

// pq사용
// 방문배열
// 기존거리가 > 갱신이면 추가