
import java.util.*;
import java.io.*;

public class Main {

	public static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 비교

		boolean[][] arr1 = new boolean[N + 1][N + 1];	// 키 커지는 방향
		boolean[][] arr2 = new boolean[N + 1][N + 1]; // 키 작아지는 방향
		for (int i = 1; i <= N; i++) {
			arr1[i][i] = true;
			arr2[i][i] = true;
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			arr1[s][e] = true;
			arr2[e][s] = true;
		}

		fw(arr1);
		fw(arr2);

		int count = 0;
		for (int i = 1; i <= N; i++) {
			boolean can = true;
			for (int k = 1; k <= N; k++) {
				if (i == k) {
					continue;
				}

				if (!arr1[i][k] && !arr2[i][k]) {
					can = false;
					break;
				}
			}

			if (can) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static void fw(boolean[][] arr) {
		for (int v = 1; v <= N; v++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {
					if (arr[s][v] && arr[v][e]) {
						arr[s][e] = true;
					}
				}
			}
		}
	}
}
