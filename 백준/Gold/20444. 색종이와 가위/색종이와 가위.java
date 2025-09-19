
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken()); // N번의 가위질
		long K = Long.parseLong(st.nextToken()); // K개의 색종이

		long s = 1L;
		long e = N;
		boolean result = false;

		while (s <= e) {
			long m = (s + e) / 2L;

			long r = m;
			long c = N - r;

			long v = (1L + r) * (1L + c);

			if (v == K) {
				result = true;
				break;
			} else if (v < K) {
				e = m - 1L;
				
			} else {
				s = m + 1L;
			}
		}

		if (result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}