
import java.util.*;
import java.io.*;

public class Main {
	public static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] A = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		sort(A, 1, N);

		for (int i = 1; i <= N; i++) {
			System.out.println(A[i]);
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
		int pivot = A[s];

		int l = s + 1;
		int r = e;

		while (l <= r) {
			if (A[l] < pivot) {
				l++;
			} else if (A[r] > pivot) {
				r--;
			} else {
				int tmp = A[l];
				A[l] = A[r];
				A[r] = tmp;
				l++;
				r--;
			}
		}

		int tmp = A[r];
		A[r] = pivot;
		A[s] = tmp;

		return r;
	}
}

// 피봇보다 A[s]가 작으면 s++
// 피봇보다 A[e]가 크면 e--
// 둘 다 아니면 스위치