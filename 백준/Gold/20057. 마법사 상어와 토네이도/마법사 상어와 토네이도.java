import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	// 각각 동서남북
	public static int[] direction = {1, 2, 3, 4};

	public static int[] rs = {0, 0, 0, 1, -1};
	public static int[] cs = {0, 1, -1, 0, 0};

	public static int[][] sand;

	public static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // r,c크기
		sand = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= N; c++) {
				sand[r][c] = Integer.parseInt(st.nextToken());
			}
		}


		// 스택으로 위치 정하기
		Stack<Node> road = new Stack<>();

		boolean[][] visit = new boolean[N + 1][N + 1];

		int nowR = 1;
		int nowC = 1;
		int d = 1;
		while (true) {
			// 1. 방문처리
			// 2. 노드 넣기
			// 3. 다음 위치 정하기
			if (visit[nowR][nowC]) {
				break;
			}

			visit[nowR][nowC] = true;
			road.add(new Node(nowR, nowC, getReverse(d)));

			int nextR = nowR + rs[d];
			int nextC = nowC + cs[d];

			if (nextR > N || nowR < 1 || nextC > N || nextC < 1) {
				d = getNext(d);
				nowR += rs[d];
				nowC += cs[d];
			} else if (visit[nextR][nextC]) {
				d = getNext(d);
				nowR += rs[d];
				nowC += cs[d];
			} else {
				nowR = nextR;
				nowC = nextC;
			}
		}

		move(road);

		System.out.println(result);
	}


	public static void move(Stack<Node> road) {
		while(!road.isEmpty()) {
			Node now = road.pop();
			int nowR = now.r;
			int nowC = now.c;
			int nowD = now.d;

			spread(nowR, nowC, nowD);
		}
	}

	public static void spread(int r, int c, int d) {
		int nextR = r + rs[d];
		int nextC = c + cs[d];

		if (nextR < 1 || nextR > N || nextC < 1 || nextC > N) {
			return;
		}

		int percent10 = (int)(sand[nextR][nextC] * 0.1);
		int percent7 = (int)(sand[nextR][nextC] * 0.07);
		int percent5 = (int)(sand[nextR][nextC] * 0.05);
		int percent2 = (int)(sand[nextR][nextC] * 0.02);
		int percent1 = (int)(sand[nextR][nextC] * 0.01);
		int remain = sand[nextR][nextC] - (percent10 * 2 + percent7 * 2 + percent5 + percent2 * 2 + percent1 * 2);

		// 현재 칸 비우기
		sand[nextR][nextC] = 0;

		int tmpR = 0;
		int tmpC = 0;
		// 앞
		tmpR = nextR + rs[d];
		tmpC = nextC + cs[d];

		if (isOut(tmpR, tmpC)) {
			result += remain;
		} else {
			sand[tmpR][tmpC] += remain;
		}

		// 앞앞
		tmpR = nextR + rs[d] * 2;
		tmpC = nextC + cs[d] * 2;
		if (isOut(tmpR, tmpC)) {
			result += percent5;
		} else {
			sand[tmpR][tmpC] += percent5;
		}

		// 앞좌, 앞우
		if (d == 1 || d == 2) {
			tmpR = nextR + rs[d] + rs[3];
			tmpC = nextC + cs[d] + cs[3];
			if (isOut(tmpR, tmpC)) {
				result += percent10;
			} else {
				sand[tmpR][tmpC] += percent10;
			}

			tmpR = nextR + rs[d] + rs[4];
			tmpC = nextC + cs[d] + cs[4];
			if (isOut(tmpR, tmpC)) {
				result += percent10;
			} else {
				sand[tmpR][tmpC] += percent10;
			}
		} else {
			tmpR = nextR + rs[d] + rs[1];
			tmpC = nextC + cs[d] + cs[1];
			if (isOut(tmpR, tmpC)) {
				result += percent10;
			} else {
				sand[tmpR][tmpC] += percent10;
			}

			tmpR = nextR + rs[d] + rs[2];
			tmpC = nextC + cs[d] + cs[2];
			if (isOut(tmpR, tmpC)) {
				result += percent10;
			} else {
				sand[tmpR][tmpC] += percent10;
			}
		}

		// 우, 좌
		if (d == 1 || d == 2) {
			tmpR = nextR + rs[4];
			tmpC = nextC + cs[4];
			if (isOut(tmpR, tmpC)) {
				result += percent7;
			} else {
				sand[tmpR][tmpC] += percent7;
			}

			tmpR = nextR + rs[3];
			tmpC = nextC + cs[3];
			if (isOut(tmpR, tmpC)) {
				result += percent7;
			} else {
				sand[tmpR][tmpC] += percent7;
			}
		} else {
			tmpR = nextR + rs[1];
			tmpC = nextC + cs[1];
			if (isOut(tmpR, tmpC)) {
				result += percent7;
			} else {
				sand[tmpR][tmpC] += percent7;
			}

			tmpR = nextR + rs[2];
			tmpC = nextC + cs[2];
			if (isOut(tmpR, tmpC)) {
				result += percent7;
			} else {
				sand[tmpR][tmpC] += percent7;
			}
		}

		// 좌좌, 우우
		if (d == 1 || d == 2) {
			tmpR = nextR + rs[4] * 2;
			tmpC = nextC + cs[4] * 2;
			if (isOut(tmpR, tmpC)) {
				result += percent2;
			} else {
				sand[tmpR][tmpC] += percent2;
			}

			tmpR = nextR + rs[3] * 2;
			tmpC = nextC + cs[3] * 2;
			if (isOut(tmpR, tmpC)) {
				result += percent2;
			} else {
				sand[tmpR][tmpC] += percent2;
			}
		} else {
			tmpR = nextR + rs[1] * 2;
			tmpC = nextC + cs[1] * 2;
			if (isOut(tmpR, tmpC)) {
				result += percent2;
			} else {
				sand[tmpR][tmpC] += percent2;
			}

			tmpR = nextR + rs[2] * 2;
			tmpC = nextC + cs[2] * 2;
			if (isOut(tmpR, tmpC)) {
				result += percent2;
			} else {
				sand[tmpR][tmpC] += percent2;
			}
		}

		// 뒤좌, 뒤우
		if (d == 1 || d == 2) {
			tmpR = r + rs[4];
			tmpC = c + cs[4];
			if (isOut(tmpR, tmpC)) {
				result += percent1;
			} else {
				sand[tmpR][tmpC] += percent1;
			}

			tmpR = r + rs[3];
			tmpC = c + cs[3];
			if (isOut(tmpR, tmpC)) {
				result += percent1;
			} else {
				sand[tmpR][tmpC] += percent1;
			}
		} else {
			tmpR = r + rs[1];
			tmpC = c + cs[1];
			if (isOut(tmpR, tmpC)) {
				result += percent1;
			} else {
				sand[tmpR][tmpC] += percent1;
			}

			tmpR = r + rs[2];
			tmpC = c + cs[2];
			if (isOut(tmpR, tmpC)) {
				result += percent1;
			} else {
				sand[tmpR][tmpC] += percent1;
			}
		}
	}

	public static boolean isOut(int r, int c) {
		return r < 1 || r > N || c < 1 || c > N;
	}

	// 동, 남, 서, 북 반복
	public static int getNext(int d) {
		if (d == 1) {
			return 3;
		} else if (d == 2) {
			return 4;
		} else if (d == 3) {
			return 2;
		} else {
			return 1;
		}
	}

	public static int getReverse(int d) {
		if (d == 1) {
			return 2;
		} else if (d == 2) {
			return 1;
		} else if (d == 3) {
			return 4;
		} else {
			return 3;
		}
	}

	public static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}



// Ap[r][c] = (r,c)에 있는 모래양
// 가운데부터 반시계방향 이동

// y에 있는 모래가
// 사진 비율대로 흩뿌려짐.
// 토네이도 이동 앞칸 -> 전체 흩뿌리고 남은양
// 모래 있는 칸은 더하기

// 격자 밖으로 나간 모래양 구하기

// 동그랗게 이동하기
// 해당 이동에 대해 모래 위치시키기
// 벗어나는 거 주의!

// N은 홀숫
// 스택으로 위치 채울까?
// 스택 뽑으면서, 방향 구하기, 방향에 따라 모래 뺴기