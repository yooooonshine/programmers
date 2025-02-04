
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			A[i][1] = 1;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i][0] = Integer.parseInt(st.nextToken());
		}


		for (int i = 2; i <= N; i++) {
			for (int j = i; j >= 1; j--) {
				if (A[j][0] > A[i][0] && A[j][1] >= A[i][1]) {
					A[i][1] = A[j][1] + 1;
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= N; i++) {
			if (A[i][1] > max) {
				max = A[i][1];
			}
		}

		System.out.println(max);
	}
}
