import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			int[] arr = new int[n + 1];
			for (int k = 1; k <= n; k++) {
				arr[k] = Integer.parseInt(st.nextToken());
			}

			long sum = 0;
			for (int k = 1; k <= n; k++) {
				for (int j = k + 1; j <= n; j++) {
					int tmp = gcd(arr[k], arr[j]);

					sum += (long)tmp;
				}
			}

			bw.write(sum + "\n");
		}
		bw.flush();
	}

	public static int gcd(int a, int b) {
		int big = Math.max(a, b);
		int small = Math.min(a, b);

		while (true) {
			int r = big % small;

			if (r == 0) {
				return small;
			}

			big = small;
			small = r;
		}
	}
}
