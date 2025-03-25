
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] Ns = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			Ns[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		int[] Ms = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			Ms[i] = Integer.parseInt(st.nextToken());
		}

		long result = 1L;
		boolean isOver = false;

		for (int i = 1; i <= N; i++) {

			for (int k = 1; k <= M; k++) {
				int tmp = gcd(Ns[i], Ms[k]);

				Ns[i] /= tmp;
				Ms[k] /= tmp;

				result = (long)tmp * result;
				if (result >= 1000000000L) {
					isOver = true;
					result %= 1000000000L;
				}

				if (Ns[i] == 1) {
					break;
				}
			}
		}

		if (isOver) {
			System.out.println(String.format("%09d", result % 1000000000L));
		} else {
			System.out.println(result);
		}
	}

	public static int gcd(int a, int b) {
		int big = (int)Math.max(a, b);
		int small = (int)Math.min(a, b);

		int remainder = big % small;

		if (remainder == 0) {
			return small;
		} else {
			return gcd(small, remainder);
		}
	}
}

// N개의 수 곱 => A, 각 N은 10^9이하
// M개의 수 곱 => B, 각 M은 10^9이하
// 9자리보다 길면 마지막 9자리만

// 15 10 = 1, 5
// 10 5 = 2, 0  => 5가 최대 공약수

// 3 * 5 2 * 5
// 결국 최대 공약수는 나눠서 구해도 돼

// 특정 N과 나머지 모두랑했을때 나오는 최대 공약수를 모조
