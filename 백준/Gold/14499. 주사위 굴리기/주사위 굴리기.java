import java.util.*;
import java.io.*;

public class Main {

	public static int R;
	public static int C;

	public static int[][] map;

	public static int[] Rs = {0, 0, 0, -1, 1};
	public static int[] Cs = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int nowR = Integer.parseInt(st.nextToken()) + 1;
		int nowC = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken()); // 명령의 개수

		map = new int[R + 1][C + 1];

		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dice = new int[7];
		st = new StringTokenizer(br.readLine());
		for (int k = 1; k <= K; k++) {
			int direction = Integer.parseInt(st.nextToken());

			int nextR = nowR + Rs[direction];
			int nextC = nowC + Cs[direction];

			if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
				continue;
			}
			nowR = nextR;
			nowC = nextC;

			if (direction == 1) {
				moveRight(dice);
			} else if (direction == 2) {
				moveLeft(dice);
			} else if (direction == 3) {
				moveUp(dice);
			} else {
				moveDown(dice);
			}

			if (map[nowR][nowC] == 0) {
				map[nowR][nowC] = dice[1];
			} else {
				dice[1] = map[nowR][nowC];
				map[nowR][nowC] = 0;
			}

			System.out.println(dice[6]); // 주사위 윗면 출력
		}
	}

	public static int getReverse(int now) {
		if (now == 1) {
			return 6;
		} else if (now == 2) {
			return 5;
		} else if (now == 3) {
			return 4;
		} else if (now == 4) {
			return 3;
		} else if (now == 5) {
			return 2;
		} else {
			return 1;
		}
	}

	public static void moveUp(int[] dice) {
		int tmp = dice[1];
		dice[1] = dice[2];
		dice[2] = dice[6];
		dice[6] = dice[5];
		dice[5] = tmp;
	}

	public static void moveDown(int[] dice) {
		int tmp = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[6];
		dice[6] = dice[2];
		dice[2] = tmp;
	}

	public static void moveLeft(int[] dice) {
		int tmp = dice[1];
		dice[1] = dice[4];
		dice[4] = dice[6];
		dice[6] = dice[3];
		dice[3] = tmp;
	}

	public static void moveRight(int[] dice) {
		int tmp = dice[1];
		dice[1] = dice[3];
		dice[3] = dice[6];
		dice[6] = dice[4];
		dice[4] = tmp;
	}
}
// 크기 N x M 지도
// r,c

// 주사위 윗면이 1, 3이 동족


// 지도 0 -> 주사위 바닥면 수 복사
// 0x -> 칸 수 -> 주사위 바닥면에 복사, 칸 수 = 0

// 바깥이동은 무시

// 상하는 무조건 2156
// 좌우는 4136