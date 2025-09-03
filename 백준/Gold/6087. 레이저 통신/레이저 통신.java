import java.util.*;
import java.io.*;

public class Main {

	public static String[][] arr;
	public static int R;
	public static int C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int sr = 0;
		int sc = 0;
		int er = 0;
		int ec = 0;
		int tmpC = 0;

		arr = new String[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 1; c <= C; c++) {
				arr[r][c] = tmp[c - 1];

				if (arr[r][c].equals("C")) {
					if (tmpC == 0) {
						sr = r;
						sc = c;
						tmpC++;
					} else {
						er = r;
						ec = c;
					}
				}
			}
		}

		bfs(sr, sc, er, ec);
	}

	public static int[] rs = {0, 0, 1, -1};
	public static int[] cs = {1, -1, 0, 0};

	public static void bfs(int sr, int sc, int er, int ec) {
		boolean[][] visit = new boolean[R + 1][C + 1];

		Queue<Node> myQ = new LinkedList<>();
		myQ.add(new Node(sr, sc, 0));

		while (!myQ.isEmpty()) {
			Node now = myQ.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowCount = now.count;


			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			if (nowR == er && nowC == ec) {
				System.out.println(nowCount - 1);
				break;
			}

			for (int i = 0; i <= 3; i++) {
				int dr = rs[i];
				int dc = cs[i];

				int nextR = nowR;
				int nextC = nowC;
				while (true) {
					nextR += dr;
					nextC += dc;

					if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
						break;
					}

					if (arr[nextR][nextC].equals("*")) {
						break;
					}

					myQ.add(new Node(nextR, nextC, nowCount + 1));
				}
			}
		}

	}

	public static class Node {
		int r;
		int c;
		int count;

		public Node(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}

// 지도 R x C

// 지도 빈칸 or 벽
// 두칸은 c로 표시되어 있는칸
// 거울의 최소값

// 최단거리? bfs일거같은데