
import java.util.*;
import java.io.*;

public class Main {
	public static int[][] arr;

	public static int C;
	public static int R;

	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열

		arr = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1;  c <= C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int age = 0;

		while (true) {
			// 빙하가 있는지, 조각 체크
			boolean hasIce = false;

			int count = 0;
			boolean[][] visit2 = new boolean[R + 1][C + 1];
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					if (arr[r][c] != 0 && !visit2[r][c]) {
						bfs(visit2, r, c);
						hasIce = true;
						count++;
					}
				}
			}

			if (!hasIce) {
				System.out.println(0);
				break;
			} else if (count >= 2) {
				System.out.println(age);
				break;
			}

			age++;

			boolean[][] visit = new boolean[R + 1][C + 1];
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					if (arr[r][c] != 0) {
						visit[r][c] = true;

						int melt = 0;
						for (int i = 0; i <= 3; i++) {
							int nextR = r + rs[i];
							int nextC = c + cs[i];

							if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
								continue;
							}

							if (arr[nextR][nextC] == 0 && !visit[nextR][nextC]) {
								melt++;
							}
						}

						arr[r][c] = Math.max(0, arr[r][c] - melt);
					}
				}
			}
		}
	}

	public static void bfs(boolean[][] visit, int r, int c) {
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

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
					continue;
				}

				if (arr[nextR][nextC] != 0) {
					q.add(new Node(nextR, nextC));
				}
			}
		}
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}

// 주의
// 2^64로 나눈 나머지 출력

// 사탕 M개
// N명의 친구들에게 나눠줌
// 각자 원하는 사탕 개수 조냊
// 못받을 시 못 받은 사탕 제곱만큼 분노

// 최소 분노의 합

// 빙하가 없으면 끝
// 2조각 분리되며 끝
