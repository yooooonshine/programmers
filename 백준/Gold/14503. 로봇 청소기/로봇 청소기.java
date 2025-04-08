import java.util.*;
import java.io.*;

public class Main {

	public static int N, M;
	public static int[][] room;
	public static int count = 0;

	public static int[] rs = {-1, 0, 1, 0};
	public static int[] cs = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //r
		M = Integer.parseInt(st.nextToken()); //c

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		room = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		clean(R, C, D);

		System.out.println(count);
	}

	// 청소하면 2
	public static void clean(int r, int c, int d) {
		if (room[r][c] == 0) {
			room[r][c] = 2;
			count++;
		}

		int nextD = d;
		for (int i = 0; i <= 3; i++) {
			nextD = getNextD(nextD);
			int tmpR = r + rs[nextD];
			int tmpC = c + cs[nextD];

			boolean isDirty = false;
			if (room[tmpR][tmpC] == 0) {
				isDirty = true;
			}

			if (isDirty) {
				clean(tmpR, tmpC, nextD);
				return;
			}
		}

		// 전부 dirty 아님
		int backD = getNextD(getNextD(d));
		int tmpR = r + rs[backD];
		int tmpC = c + cs[backD];

		if (room[tmpR][tmpC] == 1) {
			return;
		} else {
			clean(tmpR, tmpC, d);
		}
	}

	public static int getNextD(int d) {
		return (d + 3) % 4;
	}
}