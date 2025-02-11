
import java.util.*;
import java.io.*;

public class Main {
	public static int count = 0;
	public static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		sort(A, 1, N);
		
		if (count < K) {
			System.out.println(-1);
		}
	}

	public static void sort(int[] A, int s, int e) {
		if (s < e) {
			int p = partition(A, s, e);
			sort(A, s, p - 1);
			sort(A, p + 1, e);
		}
	}

	public static int partition(int[] A, int s, int e) {
		int pivot = A[e];
		int i = s - 1;

		// s부터 i번째 까지는 pivot보다 작음을 보장
		// 피봇보다 작은 게 나오면 i를 + 1하고 그 자리에 값을 넣는다.
		// 이미 지나쳤다는 건 피봇보다 크다는 것
		for (int j = s; j <= e - 1; j++) {
			if (A[j] <= pivot) {
				i++;
				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;

				count++;
				if (count == K) {
					if (A[i] < tmp) {
						System.out.println(A[i] + " " + tmp);
					} else {
						System.out.println(tmp + " " + A[i]);
					}
				}
			}
		}
		if (i + 1 != e) {
			int tmp = A[i + 1];
			A[i + 1] = A[e];
			A[e] = tmp;

			count++;
			if (count == K) {
				if (A[i + 1] < tmp) {
					System.out.println(A[i + 1] + " " + tmp);
				} else {
					System.out.println(tmp + " " + A[i + 1]);
				}
			}
		}

		return i + 1;
	}
}
