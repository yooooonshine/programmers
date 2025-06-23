import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] X = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int x = 1; x <= N; x++) {
			X[x] = Integer.parseInt(st.nextToken());
		}

		int max = 0;

		int l = 1;
		int r = N;

		while (l < r) {
			int min = Math.min(X[l], X[r]);
			int width = r - l - 1;
			max = Math.max(max, min * width);

			if (X[l] < X[r]) {
				l++;
			} else {
				r--;
			}
		}

		System.out.println(max);
	}
}
