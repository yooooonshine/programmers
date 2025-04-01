
import java.util.*;
import java.io.*;

public class Main {
	public static int N, K, P, X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N이하가 되도록
		K = Integer.parseInt(st.nextToken()); // K자리 디스플레이
		P = Integer.parseInt(st.nextToken()); // 최대 P개 반전
		X = Integer.parseInt(st.nextToken()); // 현재 층수

		boolean[][] nMap = new boolean[10][7];
		nMap[0] = new boolean[] {true, true, true, false, true, true, true};
		nMap[1] = new boolean[] {false, false, true, false, false, false, true};
		nMap[2] = new boolean[] {false, true, true, true, true, true, false};
		nMap[3] = new boolean[] {false, true, true, true, false, true, true};
		nMap[4] = new boolean[] {true, false, true, true, false, false, true};
		nMap[5] = new boolean[] {true, true, false, true, false, true, true};
		nMap[6] = new boolean[] {true, true, false, true, true, true, true};
		nMap[7] = new boolean[] {false, true, true, false, false, false, true};
		nMap[8] = new boolean[] {true, true, true, true, true, true, true};
		nMap[9] = new boolean[] {true, true, true, true, false, true, true};


		int[] numX = numToDigit(X);

		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}

			int[] numStrings = numToDigit(i);

			int diff = 0;
			for (int k = 0; k < K; k++) {
				boolean[] a = nMap[numX[k]];
				boolean[] b = nMap[numStrings[k]];

				for (int j = 0; j < 7; j++) {
					if (a[j] != b[j]) {
						diff++;
					}
				}

				if (diff > P) {
					break;
				}
			}

			if (diff <= P && diff > 0) {
				result++;
			}
		}

		System.out.println(result);
	}

	public static int[] numToDigit(int num) {
		int[] result = new int[K];
		for (int i = K - 1; i >= 0; i--) {
			result[i] = num % 10;
			num /= 10;
		}

		return result;
	}
}

// 9층
// 1자리
// 2개 반전
// 5층
