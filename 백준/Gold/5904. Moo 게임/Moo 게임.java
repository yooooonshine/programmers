
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int K = 0; // moo수열
		int l = 3; // 길이

		while (l < N) {
			K++;
			l = l * 2 + 1 + K + 2;
		}

		System.out.println(recursion(K, l, N));
	}

	public static String recursion(int k, int l, int n) {
		// k번쨰, l인 상황에서,
		// n번쨰 찾기
		if (k == 0) {
			if (n == 1) {
				return "m";
			} else {
				return "o";
			}
		}

		int beforeL = (l - (k + 2 + 1)) / 2;

		if (n <= beforeL) {
			return recursion(k - 1, beforeL, n);
		} else if (beforeL < n && n <= beforeL + k + 3) {
			int reIndexingN = n - beforeL;

			if (reIndexingN == 1) {
				return "m";
			} else {
				return "o";
			}
		} else {
			return recursion(k - 1, beforeL, n - (beforeL + k + 3));
		}
	}
}

// moo 수열을 하나씩 순서대로
// S(k) = S(k - 1) + m + o x (k + 2) + S(k - 1)

// N이 주어졌을 때 N번쨰 글자
// N번쨰 숫자는 항상 고졍되겠구나.

// 만약 반복을 통해 Moo수열이 N이상이 될때까지 찾는다면?]
// 대충 2^n > N = 10^9
// n은 30이면되겠는데?

// 아 근데 메모리 때문에 10^9인 배열이 안돠겠다

// 정확히 특정 N인 위치만 찾아내는 방법이 필요하겠다

// 점화식이
// 길이(k) = 길이(k - 1) * 2 + 1 + k + 2
// 이렇게 k를 알았다면?
// N이 어느 위치인지 분할로 찾기
// S(k - 1) + mo~~ + s(k - 1)
// 중 위치 찾아서, 앞개수 빼고
// s(k - 1)에서 해당 값 다시 재귀