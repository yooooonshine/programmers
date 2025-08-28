
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] A = new long[N + 1];
		long[] B = new long[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) A[i] = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) B[i] = Long.parseLong(st.nextToken());

		Map<Long, Long> freq = new HashMap<>();
		long diff = 0L;
		freq.put(0L, 1L);

		for (int i = 1; i <= N; i++) {
			diff += B[i] - A[i];
			freq.put(diff, freq.getOrDefault(diff, 0L) + 1);
		}

		long result = 0L;
		for (long count : freq.values()) {
			if (count > 1) {
				result += count * (count - 1) / 2;
			}
		}

		System.out.println(result);
	}
}