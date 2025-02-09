
import java.util.*;
import java.io.*;

public class Main {

	public static int[][] result;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[K + 1];
			for (int i = 1; i <= K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 초기화
			result = new int[K + 1][K + 1];
			for (int i = 1; i <= K; i++) {
				for (int j = 1; j <= K; j++) {
					result[i][j] = -1;
				}
			}
			for (int i = 1; i <= K; i++) {
				result[i][i] = 0;
			}

			System.out.println(calc(1, K));
		}
	}

	public static int calc(int i1, int i2) {
		if (result[i1][i2] != -1) {
			return result[i1][i2];
		}

		int tmpSum = 0;
		for (int i = i1; i <= i2; i++) {
			tmpSum += arr[i];
		}

		int min = 100000000;
		for (int i = i1; i <= i2 - 1; i++) {
			int sum = calc(i1, i) + calc(i + 1, i2) + tmpSum;

			min = Math.min(min, sum);
		}

		return result[i1][i2] = min;
	}
}