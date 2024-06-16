import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		st =  new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			binarySearch(A, N, Integer.parseInt(st.nextToken()));
		}

	}

	public static void binarySearch(int[] A, int N, int ans) {
		int s = 0;
		int e = N - 1;
		int mid;
		while (s <= e) {
			mid = (s + e) / 2;

			if (A[mid] == ans) {
				System.out.println(1);
				return;
			} else if (A[mid] < ans) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		System.out.println(0);
	}
}
