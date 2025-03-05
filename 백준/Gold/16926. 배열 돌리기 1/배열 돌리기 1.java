import java.util.*;
import java.io.*;

public class Main {
	public static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int R = Integer.parseInt(st.nextToken()); // 회전수

		arr = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 회전
		for (int i = 1; i <= R; i++) {
			rotate(N, M);

		}
		// 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				bw.write(arr[r][c] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
	}

	public static void rotate(int R, int C) {
		int min = (int)Math.min(R, C) / 2;

		for (int i = 1; i <= min; i++) {
			int tmp = arr[i][i];
			int rs = i;
			int re = R - i + 1;
			int cs = i;
			int ce = C - i + 1;

			for (int w = cs + 1; w <= ce; w++) {
				arr[rs][w - 1] = arr[rs][w];
			}
			for (int w = rs + 1; w <= re; w++) {
				arr[w - 1][ce] = arr[w][ce];
			}
			for (int w = ce - 1; w >= cs; w--) {
				arr[re][w + 1] = arr[re][w];
			}
			for (int w = re - 1; w >= i; w--) {
				arr[w + 1][cs] = arr[w][cs];
			}

			arr[i + 1][i] = tmp;
		}
	}
}

// 크기 N * M(r,c) R(회전 수)
// 배열

// 가로는 1씩 증가해서 절반까지, 세로도 -> 더 작은 곳까지
// 회전
// 테두리를, 시작을 tmp에 넣고 다 왼쪽으로 한칸씩 옮기기, 마지막에 tmp 넣기