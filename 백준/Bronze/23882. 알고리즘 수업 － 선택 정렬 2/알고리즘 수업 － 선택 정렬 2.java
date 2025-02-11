import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0;
		for (int i = N; i >= 2; i--) {

			int max = 0;
			int index = 0;
			for (int j = 1; j <= i; j++) {
				if (A[j] > max) {
					max = A[j];
					index = j;
				}
			}

			if (index != i) {
				int tmp = A[i];
				A[i] = A[index];
				A[index] = tmp;

				count++;
				if (count == K) {
					break;
				}
			}

		}

		if (count < K) {
			System.out.println(-1);
			return;
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(A[i] + " ");
		}
	}
}