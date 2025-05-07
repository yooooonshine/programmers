import java.util.*;
import java.io.*;

public class Main {

	public static long[][] dpArr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		dpArr = new long[100001][4];
		for (int i = 0; i <= 100000; i++) {
			for (int j = 0; j <= 3; j++) {
				dpArr[i][j] = -1L;
			}
		}

		dpArr[0][1] = 0L;
		dpArr[0][2] = 0L;
		dpArr[0][3] = 0L;

		dpArr[1][1] = 1L;
		dpArr[1][2] = 0L;
		dpArr[1][3] = 0L;

		dpArr[2][1] = 0L;
		dpArr[2][2] = 1L;
		dpArr[2][3] = 0L;

		dpArr[3][1] = 1L;
		dpArr[3][2] = 1L;
		dpArr[3][3] = 1L;


		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int n = 1; n <= N; n++) {
			int tmp = Integer.parseInt(br.readLine());

			long result = (dp(tmp, 1) + dp(tmp, 2) + dp(tmp, 3)) % 1000000009L;
			bw.write(result + "\n");
		}

		bw.flush();
	}

	public static long dp(int n, int now) {
		if (dpArr[n][now] != -1) {
			return dpArr[n][now];
		}

		long sum = 0;
		for (int i = 1; i <= 3; i++) {
			if (i == now) {
				continue;
			}

			sum += dp(n - now, i);
			sum %= 1000000009L;
		}

		return dpArr[n][now] = sum % 1000000009L;
	}
}
