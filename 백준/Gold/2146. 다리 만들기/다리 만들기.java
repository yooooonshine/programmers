import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static int[][] land;
	public static int[][] team;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 지도 크기
		land = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				land[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 유니온 파인드
		team = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				team[r][c] = getIndex(r, c);
			}
		}

		boolean[][] visit = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visit[r][c] && land[r][c] == 1) {
					dfs(visit, r, c);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		// 찾기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (land[r][c] != 0) {
					int tmp = bfs(r, c);

					if (tmp < min) {
						min = tmp;
					}
				}
			}
		}

		System.out.println(min);
	}

	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, 1, -1};

	public static int bfs(int r, int c) {
		Queue<DNode> s = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];

		s.add(new DNode(r, c, 0));

		while (!s.isEmpty()) {
			DNode now = s.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowD = now.d;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;


			if (land[nowR][nowC] != 0 && team[nowR][nowC] != team[r][c]) {
				return nowD - 1;
			}


			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR < 0 || nextR > N - 1 || nextC < 0 || nextC > N - 1) {
					continue;
				}

				if (team[nextR][nextC] == team[r][c]) {
					continue;
				}

				s.add(new DNode(nextR, nextC, nowD + 1));
			}
		}

		return 1111111111;
	}

	public static class DNode {
		int r;
		int c;
		int d;

		public DNode(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}

	public static void dfs(boolean[][] visit, int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));

		while (!q.isEmpty()) {
			Node now = q.poll();
			int nowR = now.r;
			int nowC = now.c;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			if (find(getIndex(r, c)) != find(getIndex(nowR, nowC))) {
				union(getIndex(r, c), getIndex(nowR, nowC));
			}


			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR < 0 || nextR > N - 1 || nextC < 0 || nextC > N - 1) {
					continue;
				}

				if (!visit[nextR][nextC] && land[nextR][nextC] == 1) {
					q.add(new Node(nextR, nextC));
				}
			}
		}
	}

	public static void union(int v1, int v2) {
		int vR1 = find(v1);
		int vR2 = find(v2);

		int r1 = vR1 / N;
		int c1 = vR1 % N;
		int r2 = vR2 / N;
		int c2 = vR2 % N;

		if (vR1 > vR2) {
			team[r1][c1] = vR2;
		} else if (vR1 < vR2) {
			team[r2][c2] = vR1;
		}
	}

	public static int find(int v) {
		int r = v / N;
		int c = v % N;

		if (team[r][c] == v) {
			return v;
		}

		return team[r][c] = find(team[r][c]);
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static int getIndex(int r, int c) {
		return N * r + c;
	}
}

// 다리 하나(가장 짧게)
// 섬 -> 육지덩어리

// 탐색인가?

// 만약 bfs하면? 10^4 이고, 이를 10^4 ->? 10^8

// 각섬들의 위치는 r * n + c

// 만약 0부터 시작하면, % 10, / 10 -
