
import java.util.*;
import java.io.*;

public class Main {

	public static int R;
	public static int C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		int T = Integer.parseInt(st.nextToken()); // T초 후

		int top = 0;
		int bottom = 0;

		int[][] arr = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());

				if (arr[r][c] == -1) {
					bottom = r;
					top = r - 1;
				}
			}
		}

		// t초 반복
		for (int t = 1; t <= T; t++) {
			arr = diffusion(arr);

			// for (int r = 1; r <= R; r++) {
			// 	for (int c = 1; c <= C; c++) {
			// 		System.out.print(arr[r][c] + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();
			clean(top, bottom, arr);

			// for (int r = 1; r <= R; r++) {
			// 	for (int c = 1; c <= C; c++) {
			// 		System.out.print(arr[r][c] + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();
		}

		int result = 0;
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (arr[r][c] > 0) {
					result += arr[r][c];
				}
			}
		}
		System.out.println(result);
	}

	public static int[] rs = {0, 0, -1, 1};
	public static int[] cs = {1, -1, 0, 0};

	public static int[][] diffusion(int[][] arr) {
		int[][] tmp = new int[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				tmp[r][c] = arr[r][c];
			}
		}

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (arr[r][c] > 0) {
					int sum = 0;
					for (int i = 0; i <= 3; i++) {
						int nextR = r + rs[i];
						int nextC = c + cs[i];

						// 벽이거나, 청정기면 패스
						if (nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
							continue;
						}

						if (arr[nextR][nextC] == -1) {
							continue;
						}

						// 확산
						tmp[nextR][nextC] += arr[r][c] / 5;
						sum += arr[r][c] / 5;
					}

					tmp[r][c] -= sum;
				}


			}
		}

		return tmp;
	}

	public static void clean(int top, int bottom, int[][] arr) {

		// 위
		for (int r = top - 2; r >= 1; r--) {
			arr[r + 1][1] = arr[r][1];
		}
		for (int c = 2; c <= C; c++) {
			arr[1][c - 1] = arr[1][c];
		}
		for (int r = 2; r <= top; r++) {
			arr[r - 1][C] = arr[r][C];
		}
		for (int c = C - 1; c > 1; c--) {
			arr[top][c + 1] = arr[top][c];
		}
		arr[top][2] = 0;

		// 아래
		if (bottom + 1 <= R) {
			arr[bottom + 1][1] = 0;
		}
		for (int r = bottom + 2; r <= R; r++) {
			arr[r - 1][1] = arr[r][1];
		}
		for (int c = 2; c <= C; c++) {
			arr[R][c - 1] = arr[R][c];
		}
		for (int r = R - 1; r >= bottom; r--) {
			arr[r + 1][C] = arr[r][C];
		}
		for (int c = C - 1; c > 1; c--) {
			arr[bottom][c + 1] = arr[bottom][c];
		}
		arr[bottom][2] = 0;
	}
}

// 집 R x C
// 공기청정기는 두 행 차지, 처음 1열 설치

// 미세먼지 인접 네방향, 공기청정기 존재 or 칸 x -> 확산 x
// 확산양은 A / 5
// 남은양은 확산양 뺸 거
// 바람 불며 미세먼지 이동

// 위쪽 공기청정기는 반시계
// 아래쪽 공기청정기는 시계

// T초후 미세먼지양
