
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i = 1; i < N; i++) {
			if (A[i - 1] <= A[i]) {
				continue;
			}

			boolean isInsert = false;
			for (int j = 0; j < i - 1; j++) {
				if (A[j] <= A[i] && A[i] <= A[j + 1]) {
					insert(A, j + 1, i);
					isInsert = true;
				}
			}

			if (!isInsert) {
				insert(A, 0, i);
			}
		}

		int[] sum = new int[N];
		sum[0] = A[0];
		for (int i = 1; i < N; i++) {
			sum[i] = A[i] + sum[i - 1];
		}

		int totalSum = 0;
		for (int num : sum) {
			totalSum += num;
		}
		System.out.println(totalSum);
	}

	public static void insert(int[] A, int index1, int index2) {
		int tmp = A[index2];
		for (int i = index2; i > index1; i--) {
			A[i] = A[i - 1];
		}
		A[index1] = tmp;
	}
}