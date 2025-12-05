
import java.util.*;
import java.io.*;

public class Main {

	public static boolean[][] canMoves;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken()); // 격자판
		int C = Integer.parseInt(st.nextToken()); // 격자판

		canMoves = new boolean[R + 1][C + 1];

		int[][] board = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 직사각형 정보
		st = new StringTokenizer(br.readLine());
		int RR = Integer.parseInt(st.nextToken());
		int RC = Integer.parseInt(st.nextToken());

		// 출발지
		int SR = Integer.parseInt(st.nextToken());
		int SC = Integer.parseInt(st.nextToken());

		// 도착지
		int FR = Integer.parseInt(st.nextToken());
		int FC = Integer.parseInt(st.nextToken());

		// bfs (도착 가능한지 체크)
		Queue<Node> myQ = new LinkedList<>();
		myQ.add(new Node(SR, SC, 0));

		boolean[][] visited = new boolean[R + 1][C + 1];

		// 방문 가능한지 체크
		while (!myQ.isEmpty()) {
			Node now = myQ.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowCount = now.count;

			if (nowR == FR && nowC == FC) {
				System.out.println(nowCount);
				return;
			}

			if (visited[nowR][nowC]) {
				continue;
			}
			visited[nowR][nowC] = true;

			// 주위
			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				int endR = nextR + RR - 1;
				int endC = nextC + RC - 1;

				// 벗어났는지 체크
				if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
					continue;
				}
				if (endR < 1 || endR > R || endC < 1 || endC > C) {
					continue;
				}

				// 테두리 닳는지 체크
				boolean canMove = true;

				if (rs[i] == -1 && canMove) {
					for (int c = nextC; c <= endC; c++) {
						if (board[nextR][c] == 1) {
							canMove = false;
							break;
						}
					}
				}

				if (rs[i] == 1 && canMove) {
					if (canMoves[nextR][nextC]) {
						continue;
					}

					for (int c = nextC; c <= endC; c++) {
						if (board[endR][c] == 1) {
							canMove = false;
							break;
						}
					}
				}

				if (cs[i] == -1 && canMove) {
					if (canMoves[nextR][nextC]) {
						continue;
					}

					for (int r = nextR; r <= endR; r++) {
						if (board[r][nextC] == 1) {
							canMove = false;
							break;
						}
					}

				}

				if (cs[i] == 1 && canMove) {
					if (canMoves[nextR][nextC]) {
						continue;
					}

					for (int r = nextR; r <= endR; r++) {
						if (board[r][endC] == 1) {
							canMove = false;
							break;
						}
					}
				}

				if (canMove) {
					canMoves[nextR][nextC] = true;
				}

				if (!canMove) {
					continue;
				}

				// 통과한 경우
				myQ.add(new Node(nextR, nextC, nowCount + 1));
			}
		}

		System.out.println(-1);
	}

	public static int[] rs = {-1, 1, 0, 0};
	public static int[] cs = {0, 0, -1, 1};

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

// 크기 N x M인 격자판
// 크기 H x W인 직사각형
// 가장 왼쪽 위는 1,1 오른쪽 아래는 N, M
// 직사각형의 가장 왼쪽 위는 Sr, Sc
// 이를 Fr, Fc로 이동시키기 위한 최소 이동 횟수

// 격자판 각 칸은 빈 칸 혹은 벽
// 직사각형은 격자판을 벗어날 수 없고, 벽이 있는칸에 있을 수 없음.

// 특정방향으로 이동할 때 테두리에 1이 있는지 봐야할듯