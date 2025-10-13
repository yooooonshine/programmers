
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] a1 = new int[N];
		int[] a2 = new int[N];
		int[] a3 = new int[N];
		int[] a4 = new int[N];

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			a1[n] = Integer.parseInt(st.nextToken());
			a2[n] = Integer.parseInt(st.nextToken());
			a3[n] = Integer.parseInt(st.nextToken());
			a4[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(a1);
		Arrays.sort(a2);
		Arrays.sort(a3);
		Arrays.sort(a4);

		int[] a34 = new int[N * N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a34[i * N + j] = a3[i] + a4[j];
			}
		}
		Arrays.sort(a34);

		int[] a12 = new int[N * N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a12[i * N + j] = a1[i] + a2[j];
			}
		}
		Arrays.sort(a12);

		long count = 0;


		// a1, a2 에 대하여 포인트
		int s1 = 0;
		int e3 = N * N - 1;
		while (s1 < N * N && e3 >= 0) {
			long v1 = a12[s1];
			long v3 = a34[e3];

			if (v1 + v3 == 0L) {
				int s1Count = 0;
				int e3Count = 0;
				
				while (s1 < N * N && a12[s1] == v1) {
					s1++;
					s1Count++;
				}
				while (e3 >= 0 && a34[e3] == v3) {
					e3--;
					e3Count++;
				}
				
				count += (long) s1Count * (long) e3Count;
				
			} else if (v1 + v3 < 0L) {
				s1++;
			} else {
				e3--;
			}
		}

		System.out.println(count);
	}
}

// 배열이 4개
// 2개의 배열에 대해서는 이중 for문
// 나머지 배여렝 대해서