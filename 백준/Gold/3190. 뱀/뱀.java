
import java.util.*;
import java.io.*;

public class Main {

	public static Set<Node> apples;

	public static boolean[][] road;

	public static int N;

	public static Queue<Node> snake;

	public static int nowC;
	public static int nowR;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 보드 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 수

		road = new boolean[N + 1][N + 1];

		apples = new HashSet<>();

		for (int k = 1; k <= K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			apples.add(new Node(r, c));
		}

		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환
		int[][] turn = new int[L + 1][2];
		for (int l = 1; l <= L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			turn[l][0] = Integer.parseInt(st.nextToken());
			if (st.nextToken().equals("L")) {
				turn[l][1] = 0; // 왼
			} else {
				turn[l][1] = 1; // 오
			}
		}

		snake = new LinkedList<>();
		snake.add(new Node(1, 1));
		int d = 0;
		int nowT = 0;

		nowC = 1;
		nowR = 1;
		road[1][1] = true;

		for (int l = 1; l <= L; l++) {
			int t = turn[l][0];
			int nextd = turn[l][1];

			while (nowT < t) {
				if (!move(nowR, nowC, d)) {
					System.out.println(nowT + 1);
					return;
				}
				nowT++;
			}

			// 방향 바꾸기
			if (nextd == 0) {
				if (d == 0) {
					d = 3;
				} else if (d == 1) {
					d = 2;
				} else if (d == 2) {
					d = 0;
				} else {
					d = 1;
				}
			} else {
				if (d == 0) {
					d = 2;
				} else if (d == 1) {
					d = 3;
				} else if (d == 2) {
					d = 1;
				} else {
					d = 0;
				}
			}
		}

		while (true) {
			if (!move(nowR, nowC, d)) {
				break;
			}
			nowT++;
		}

		System.out.println(nowT + 1);
	}

	// public int[] d = {0, 1, 2, 3}; // 동시남북
	public static int[] rs = {0, 0, 1, -1};
	public static int[] cs = {1, -1, 0, 0};

	public static boolean move(int hr, int hc, int d) {
		int nextR = hr + rs[d];
		int nextC = hc + cs[d];
		if (nextR < 1 || nextR > N || nextC < 1 || nextC > N) {
			return false;
		}

		if (road[nextR][nextC]) {
			return false;
		}

		// 끝부분자르기
		if (!apples.contains(new Node(nextR, nextC))) {
			Node poll = snake.poll();
			road[poll.r][poll.c] = false;
		} else {
			apples.remove(new Node(nextR, nextC));
		}

		// 앞 넣기
		snake.add(new Node(nextR, nextC));
		road[nextR][nextC] = true;
		nowR = nextR;
		nowC = nextC;
		return true;
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			Node now = (Node)o;

			if (this.r == now.r && this.c == now.c) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return r + c;
		}
	}
}

// 벽 또는 자신의 몸과 부딪히면 끝
// N x N 정사각
// 사과 -> 여러개
// 좌측 위에서 시작, 길이는 1
// 오른쪽으로 처음 이동

// 매초마다 길이 늘어남, 머리를 다음칸
// 이동한 칸에 사과가 있으면 꼬리 그대로
// 이동한 칸에 없으면 꼬리 줄이기
//

// 시간까지 이동
// 방향바꿈