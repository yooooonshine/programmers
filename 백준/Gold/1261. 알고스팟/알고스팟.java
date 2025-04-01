import java.util.*;
import java.io.*;

public class Main {

	public static int N, M; // 가로, 세로
	public static int[][] road;
	public static int MAX = 10000000;

	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		road = new int[M + 2][N + 2];
		for (int r = 1; r <= M; r++) {
			int[] tmp = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (int c = 1; c <= N; c++) {
				road[r][c] = tmp[c - 1];
			}
		}
		for (int r = 0; r <= M + 1; r++) {
			road[r][0] = 2;
			road[r][N + 1] = 2;
		}

		for (int c = 0; c <= N + 1; c++) {
			road[0][c] = 2;
			road[M + 1][c] = 2;
		}

		System.out.println(dfs());
	}

	public static int dfs() {
		PriorityQueue<Room> pq = new PriorityQueue<>();
		pq.add(new Room(1, 1, 0));

		int[][] dist = new int[M + 2][N + 2];
		for (int r = 1; r <= M; r++) {
			for (int c = 1; c <= N; c++) {
				dist[r][c] = MAX;
			}
		}
		dist[1][1] = 0;

		while (!pq.isEmpty()) {
			Room room = pq.poll();
			int r = room.r;
			int c = room.c;

			for (int i = 0; i <= 3; i++) {
				int tmpR = r + rs[i];
				int tmpC = c + cs[i];

				if (road[tmpR][tmpC] == 2) {
					continue;
				}

				if (dist[tmpR][tmpC] > dist[r][c] + road[tmpR][tmpC]) {
					dist[tmpR][tmpC] = dist[r][c] + road[tmpR][tmpC];
					pq.add(new Room(tmpR, tmpC, dist[tmpR][tmpC]));
				}
			}
		}

		return dist[M][N];
	}

	public static class Room implements Comparable<Room> {
		int r;
		int c;

		int d;

		public Room(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Room r) {
			return this.d - r.d;
		}
	}
}

// 미로는 N * M 크기
// 빈 방  or 벽
// 벽은 부수기
// AOJ로 벽 부수기
// 11에서 N,M으로 이동 위해 최소 벽 부수는 개수


// 다익스트라
// 거리 배열
// PQ
// 인접근처
// 거리 갱신
// 현재 인접까지 거리 > 현재 방문 + 가중치
