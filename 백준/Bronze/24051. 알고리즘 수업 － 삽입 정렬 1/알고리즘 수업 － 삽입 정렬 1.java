
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int count = 0;

		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i <= N; i++) {
			int tmp = A[i];
			int index = i - 1;

			while (index >= 1 && A[index] > tmp) {
				A[index + 1] = A[index];

				count++;
				if (count == K) {
					System.out.println(A[index]);
					return;
				}

				index--;
			}

			if (index + 1 != i) {
				A[index + 1] = tmp;

				count++;
				if (count == K) {
					System.out.println(tmp);
					return;
				}
			}
		}

		System.out.println(-1);
	}
}