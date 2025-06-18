import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;
	public static int T;

	public static int[][] arr;

	public static int gramT = Integer.MAX_VALUE;

	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // R 세로
		M = Integer.parseInt(st.nextToken()); // C
		T = Integer.parseInt(st.nextToken()); // T시간 이하로 도착


		int gramR = 0;
		int gramC = 0;
		arr = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());

				if (arr[r][c] == 2) {
					gramR = r;
					gramC = c;
				}
			}
		}

		int result = bfs(1, 1);

		if (gramT != Integer.MAX_VALUE) {
			int gramToPrincess = gramT + (N - gramR) + (M - gramC);
			result = Math.min(result == -1 ? Integer.MAX_VALUE : result, gramToPrincess);
		}

		if (result > T || result == -1) {
			System.out.println("Fail");
		} else {
			System.out.println(result);
		}
	}

	public static int bfs(int r, int c) {
		Queue<Vertex> q = new LinkedList<>();
		q.add(new Vertex(r, c, 0));
		boolean[][] visit = new boolean[N + 1][M + 1];

		int result = -1;

		while (!q.isEmpty()) {
			Vertex v = q.poll();
			int nowR = v.r;
			int nowC = v.c;
			int nowT = v.t;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			if (nowR == N && nowC == M) {
				result = nowT;
				break;
			}

			if (arr[nowR][nowC] == 2 && gramT > nowT) {
				gramT = nowT;
			}

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];
				if (nextR <= 0 || nextR > N || nextC <= 0 || nextC > M) {
					continue;
				}

				if (arr[nextR][nextC] != 1) {
					q.add(new Vertex(nextR, nextC, nowT + 1));

					if (arr[nextR][nextC] == 2 && gramT > nowT + 1) {
						gramT = nowT + 1;
					}
				}
			}
		}

		return result;
	}

	public static class Vertex {
		int r;
		int c;
		int t;

		public Vertex(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}

// (N, M) 크기의 성
// 시작 (1, 1)
// 마법벽 피해 N, M
// T시간 이내(이하)

// 한칸이동 1시간

// 그람을 얻으면 벽 다부수고

// 0, 2 이동가능
// case1 그람 무관 최단 거리
// case2 용사 -> 그람 -> 공주

// 도착이 그람이면 바로 bfs 최신