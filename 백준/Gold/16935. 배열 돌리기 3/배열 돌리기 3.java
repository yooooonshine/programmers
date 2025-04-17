
import java.util.*;
import java.io.*;

public class Main {

	public static int N, M; // R, C
	public static int R; // 횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //R
		M = Integer.parseInt(st.nextToken()); //C
		R = Integer.parseInt(st.nextToken());

		int[][] A = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int r = 1; r <= R; r++) {
			int f = Integer.parseInt(st.nextToken());

			if (f == 1) {
				A = func1(A);
			} else if (f == 2) {
				A = func2(A);
			} else if (f == 3) {
				A = func3(A);
			} else if (f == 4) {
				A = func4(A);
			} else if (f == 5) {
				A = func5(A);
			} else {
				A = func6(A);
			}
		}

		int usedR = A.length - 1;
		int usedC = A[0].length - 1;

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int r = 1; r <= usedR; r++) {
			for (int c = 1; c <= usedC; c++) {
				bw.write(A[r][c] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
	}

	public static int[][] func1(int[][] A) {
		int usedR = A.length - 1;
		int usedC = A[0].length - 1;


		int[][] tmp = new int[usedR + 1][usedC + 1];
		for (int r = 1; r <= usedR; r++) {
			for (int c = 1; c<= usedC; c++) {
				tmp[usedR - r + 1][c] = A[r][c];
			}
		}

		return tmp;
	}

	public static int[][] func2(int[][] A) {
		int usedR = A.length - 1;
		int usedC = A[0].length - 1;
		int[][] tmp = new int[usedR + 1][usedC + 1];

		for (int r = 1; r <= usedR; r++) {
			for (int c = 1; c<= usedC; c++) {
				tmp[r][usedC - c + 1] = A[r][c];
			}
		}

		return tmp;
	}

	public static int[][] func3(int[][] A) {
		int usedR = A.length - 1;
		int usedC = A[0].length - 1;

		int[][] tmp = new int[usedC + 1][usedR +1];


		for (int r = 1; r <= usedR; r++) {
			for (int c = 1; c<= usedC; c++) {
				//  1,1 1,2 -> 1,6 2,6 (c를 r로, r를  - c + 1)
				tmp[c][usedR - r + 1] = A[r][c];
			}
		}

		return tmp;
	}

	public static int[][] func4(int[][] A) {
		int usedR = A.length - 1; //6
		int usedC = A[0].length - 1; //8

		int[][] tmp = new int[usedC + 1][usedR + 1];
		for (int r = 1; r <= usedR; r++) {
			for (int c = 1; c<= usedC; c++) {
				//  1,1 1,2 2,1-> 8,1 7,1 8,2
				tmp[usedC - c + 1][r] = A[r][c];
			}
		}

		return tmp;
	}

	public static int[][] func5(int[][] A) {
		int usedR = A.length - 1; //6
		int usedC = A[0].length - 1; //8

		int midR = usedR / 2; //시작 위치 전
		int midC = usedC / 2; // 시작 위치 전

		int[][] tmp = new int[usedR + 1][usedC + 1];

		for (int r = 1; r <= midR; r++) {
			for (int c = 1; c <= midC; c++) {
				tmp[r][c + midC] = A[r][c];
			}
		}
		for (int r = 1; r <= midR; r++) {
			for (int c = 1 + midC; c <= usedC; c++) {
				tmp[r + midR][c] = A[r][c];
			}
		}
		for (int r = 1 + midR; r <= usedR; r++) {
			for (int c = 1 + midC; c <= usedC; c++) {
				tmp[r][c - midC] = A[r][c];
			}
		}
		for (int r = 1 + midR; r <= usedR; r++) {
			for (int c = 1; c <= midC; c++) {
				tmp[r - midR][c] = A[r][c];
			}
		}

		return tmp;
	}

	public static int[][] func6(int[][] A) {
		int usedR = A.length - 1; //6
		int usedC = A[0].length - 1; //8

		int midR = usedR / 2; //시작 위치
		int midC = usedC / 2; // 시작 위치

		int[][] tmp = new int[usedR + 1][usedC + 1];

		for (int r = 1; r <= midR; r++) {
			for (int c = 1; c <= midC; c++) {
				tmp[r + midR][c] = A[r][c];
			}
		}
		for (int r = 1 + midR; r <= usedR; r++) {
			for (int c = 1; c <= midC; c++) {
				tmp[r][c + midC] = A[r][c];
			}
		}
		for (int r = 1 + midR; r <= usedR; r++) {
			for (int c = 1 + midC; c <= usedC; c++) {
				tmp[r - midR][c] = A[r][c];
			}
		}
		for (int r = 1; r <= midR; r++) {
			for (int c = 1 + midC; c <= usedC; c++) {
				tmp[r][c - midC] = A[r][c];
			}
		}

		return tmp;
	}
}


// 1. 배열을 상하 반전
// 2. 배열 좌우 반전
// 3. 오른쪽 90도
// 4. 왼쪽 90도
// 5. 4조각으로 짤라서 시계방향
// 6. 4조각으로 잘라서 반시계 방향
