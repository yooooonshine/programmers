import java.util.*;
import java.io.*;

public class Main {

	public static int[][] arr;
	public static List<Node> zeroList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		arr = new int[10][10];
		for (int r = 1; r <= 9; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= 9; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());

				if (arr[r][c] == 0) {
					zeroList.add(new Node(r, c));
				}
			}
		}

		dfs(0);

		StringBuilder sb = new StringBuilder();
		for (int r = 1; r <= 9; r++) {
			for (int c = 1; c <= 9; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static boolean dfs(int index) {
		if (index == zeroList.size()) {
			return true;
		}

		Node now = zeroList.get(index);

		boolean canR[] = canR(now.r);
		boolean canC[] = canC(now.c);
		boolean canS[] = canS(now.r, now.c);

		for (int num = 1; num <= 9; num++) {
			if (canR[num] && canC[num] && canS[num]) {
				arr[now.r][now.c] = num;
				boolean result = dfs(index + 1);
				if (result) {
					return true;
				}

				arr[now.r][now.c] = 0;
			}
		}

		return false;
	}

	public static boolean[] canR(int r) {
		boolean[] can = new boolean[10];
		Arrays.fill(can, true);

		for (int c = 1; c <= 9; c++) {
			if (arr[r][c] != 0) {
				can[arr[r][c]] = false;
			}
		}

		return can;
	}

	public static boolean[] canC(int c) {
		boolean[] can = new boolean[10];
		Arrays.fill(can, true);

		for (int r = 1; r <= 9; r++) {
			if (arr[r][c] != 0) {
				can[arr[r][c]] = false;
			}
		}

		return can;
	}


	public static boolean[] canS(int r, int c) {
		boolean[] can = new boolean[10];
		Arrays.fill(can, true);

		int sr = ((r - 1) / 3) * 3 + 1;
		int sc = ((c - 1) / 3) * 3 + 1;

		for (int rr = sr; rr < sr + 3; rr++) {
			for (int cc = sc; cc < sc + 3; cc++) {
				if (arr[rr][cc] != 0) {
					can[arr[rr][cc]] = false;
				}
			}
		}

		return can;
	}
}

// 가로, 세로줄에 1~9 한번씩
// 3x3안에서도 한번씩

// 가로 0의 개수, 세로 0의 개수, 사각형 별 0의 개우