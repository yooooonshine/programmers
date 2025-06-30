
import java.util.*;
import java.io.*;

public class Main {

	public static int result = 0;
	public static boolean[][] choice;
	public static String[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = new String[6][6];
		choice = new boolean[6][6];

		for (int r = 1; r <= 5; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 1; c <= 5; c++) {
				arr[r][c] = tmp[c - 1];
			}
		}

		recursion(0, 1, 0, 0);

		System.out.println(result);
	}

	public static void recursion(int count, int index, int sC, int yC) {
		if (yC >= 4) {
			return;
		}

		if (count == 7) {
			if (sC < 4) {
				return;
			}
			// 인접 체크
			boolean isAdj = check();

			if (isAdj) {
				result++;
				return;
			}

		}

		for (int i = index; i <= 25; i++) {
			int r = (i - 1) / 5 + 1;
			int c = (i - 1) % 5 + 1;

			choice[r][c] = true;
			if (Objects.equals(arr[r][c], "Y")) {
				recursion(count + 1, i + 1, sC, yC + 1);
			} else {
				recursion(count + 1, i + 1, sC + 1, yC);
			}
			choice[r][c] = false;
		}
	}

	public static boolean check() {
		int count = 0;

		int startR = 0;
		int startC = 0;

		for (int i = 1; i <= 25; i++) {
			int r = (i - 1) / 5 + 1;
			int c = (i - 1) % 5 + 1;

			if (choice[r][c]) {
				startR = r;
				startC = c;
				break;
			}
		}

		Queue<int[]> queue = new LinkedList<>();

		boolean[][] visited = new boolean[6][6];
		queue.add(new int[] {startR, startC});

		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int r = pos[0];
			int c = pos[1];

			if (visited[r][c]) {
				continue;
			}
			visited[r][c] = true;
			count++;

			for (int dr = -1; dr <= 1; dr++) {
				for (int dc = -1; dc <= 1; dc++) {
					if (Math.abs(dr) + Math.abs(dc) != 1) {
						continue; // 가로 세로 인접만
					}

					int nextR = r + dr;
					int nextC = c + dc;

					if (nextR < 1 || nextR > 5 || nextC < 1 || nextC > 5) {
						continue;
					}

					if (choice[nextR][nextC] && !visited[nextR][nextC]) {
						queue.add(new int[] {nextR, nextC});
					}
				}
			}
		}

		if (count == 7) {
			return true; // 7명 인접
		} else {
			return false; // 인접하지 않음
		}
	}
}

// 7명의 여학생, 가로 세로 인접
// 이다솜파 4명이상

// 25 c 7