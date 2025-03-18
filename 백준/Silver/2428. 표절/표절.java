
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 솔루션 수
		long[] F = new long[N]; // 파일 크기

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			F[i] = Long.parseLong(st.nextToken()) * 10L;
		}

		// 오름차순 정렬
		Arrays.sort(F);

		int e = 1;
		long count = 0L;
		for (int s = 0; s < N; s++) {
			while (e < N && F[s] >= F[e] * 9L / 10L) {
				e++;
			}

			count += (long)(e - s - 1);
			e--;
		}
		System.out.println(count);
	}
}