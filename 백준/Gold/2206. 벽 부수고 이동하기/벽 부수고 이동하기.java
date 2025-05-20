
import java.util.*;
import java.io.*;

public class Main {

	public static int[] rs = {1,-1,0,0};
	public static int[] cs = {0,0,1,-1};

	public static int N;
	public static int M;

	public static boolean[][] map;
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // r
		M = Integer.parseInt(st.nextToken()); // c

		map = new boolean[N + 2][M + 2];
		for (int r = 1; r <= N; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 1; c <= M; c++) {
				if (tmp[c - 1].equals("0")) {
					map[r][c] = false;
				} else {
					map[r][c] = true;
				}
			}
		}

		bfs();

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(result);
	}

	public static void bfs() {
		boolean[][][] visit = new boolean[N + 2][M + 2][2];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(1, 1, 1, true));
		visit[1][1][0] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			if (node.r == N && node.c == M) {
				result = Math.min(result, node.count);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = node.r + rs[i];
				int nc = node.c + cs[i];

				if (nr < 1 || nr > N || nc < 1 || nc > M) continue;

				if (!visit[nr][nc][node.canBreak ? 0 : 1]) {
					if (!map[nr][nc]) {
						queue.add(new Node(nr, nc, node.count + 1, node.canBreak));
						visit[nr][nc][node.canBreak ? 0 : 1] = true;
					} else if (node.canBreak) {
						queue.add(new Node(nr, nc, node.count + 1, false));
						visit[nr][nc][1] = true;
					}
				}
			}
		}
	}

	public static class Node {
		int r;
		int c;
		int count;
		boolean canBreak;

		public Node(int r, int c, int count, boolean canBreak) {
			this.r = r;
			this.c = c;
			this.count = count;
			this.canBreak = canBreak;
		}
	}
}