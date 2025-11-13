import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();

		int N = A.length;

		// 각 문자 개수가 다르면 -1
		Map<Character, Integer> m1 = new HashMap<>();
		for (int n = 0; n < A.length; n++) {
			if (m1.containsKey(A[n])) {
				m1.replace(A[n], m1.get(A[n]) + 1);
			} else {
				m1.put(A[n], 1);
			}
		}
		Map<Character, Integer> m2 = new HashMap<>();
		for (int n = 0; n < B.length; n++) {
			if (m2.containsKey(B[n])) {
				m2.replace(B[n], m2.get(B[n]) + 1);
			} else {
				m2.put(B[n], 1);
			}
		}

		boolean same = true;
		for (char tmp : m1.keySet()) {
			if (!m2.containsKey(tmp)) {
				same = false;
				break;
			}

			if (m1.get(tmp) != m2.get(tmp)) {
				same = false;
				break;
			}
		}

		if (!same) {
			System.out.println("-1");
			return;
		}

		// 같을 경우
		int count = 0;
		int aIndex = N - 1;
		for (int n = N - 1; n >= 0; n--) {


			while (true) {
				if (aIndex < 0) {
					break;
				}

				if (B[n] == A[aIndex]) {
					aIndex--;
					break;
				}

				aIndex--;
				count++;
			}
		}

		System.out.println(count);
	}
}

// 문자열 A, B
// 문자열 바꾸는 것 -> A 한굴자를 문자열 맨 앞으로

// 바꾸는 최소 횟수

// 못바꾸면 -1

// dp, 그리디, 구현

// 순수면 50!

// 끝부분부터 같은지 체크해야 하는구나. x
// 최대 일치하는 게 중요한가?
