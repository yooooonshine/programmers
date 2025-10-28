import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		String[][] arr = new String[R + 1][C + 1];

		Queue<Wall> fire = new LinkedList<>();

		int sR = 0;
		int sC = 0;

		// 미로 위치
		for (int r = 1; r <= R; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 1; c <= C; c++) {
				arr[r][c] = tmp[c - 1];

				if (arr[r][c].equals("J")) {
					sR = r;
					sC = c;
				}

				if (arr[r][c].equals("F")) {
					fire.add(new Wall(r, c, 0));

				}
			}
		}

		// -1 이면 불x, 0 이상이면 불,
		int[][] wallList = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				wallList[r][c] = Integer.MAX_VALUE;
			}
		}

		// 불 채우기
		boolean[][] fireVisit = new boolean[R + 1][C + 1];

		while (!fire.isEmpty()) {
			Wall now = fire.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowT = now.t;

			if (fireVisit[nowR][nowC]) {
				continue;
			}
			fireVisit[nowR][nowC] = true;

			wallList[nowR][nowC] = nowT;

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
					continue;
				}

				if (Objects.equals(arr[nextR][nextC], "#")) {
					continue;
				}

				fire.add(new Wall(nextR, nextC, nowT + 1));
			}
		}

		// bfs
		Queue<Wall> myQ = new LinkedList<>();
		myQ.add(new Wall(sR, sC, 0));

		boolean[][] myVisit = new boolean[R + 1][C + 1];

		while (!myQ.isEmpty()) {
			Wall now = myQ.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowT = now.t;

			if (myVisit[nowR][nowC]) {
				continue;
			}
			myVisit[nowR][nowC] = true;

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];


				if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
					System.out.println(nowT + 1 + "");
					return;
				}

				if (Objects.equals(arr[nextR][nextC], "#") || Objects.equals(arr[nextR][nextC], "F")) {
					continue;
				}

				if (wallList[nextR][nextC] <= nowT + 1) {
					continue;
			    }

				myQ.add(new Wall(nextR, nextC, nowT + 1));
			}
		}

		System.out.println("IMPOSSIBLE");
	}

	public static int[] rs = {0, 0, -1, 1};
	public static int[] cs = {1, -1, 0, 0};

	public static class Wall {

		int r;
		int c;
		int t;

		public Wall(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public boolean equals(Object o) {
			Wall tmp = (Wall)o;
			if (this.r == tmp.r && this.c == tmp.c && this.t == tmp.t) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Integer.valueOf(r + "" + c + "" + t).hashCode();
		}
	}
}

// 미로 탈출
// 불에타기전에 탈출 여부
// 지훈, 불은 한칸, 수평 수직이동

// 불은 네방향으로 확산
// 가장 자리에서 탈출

// #은 벽
// . 지나갈수 있는공간
// J 초기 위치
// F 불 공간

// 도달 실패시 IMPOSSIBLE
// 탈출 가능시 탈출 시간

// bfs로 가장자리 가면 끝
// 지훈 먼저 이동, 그 다음 불(벽)

// 불의 이동을 어떻게 저장하지 map을 사용해야하나
// t, r, c