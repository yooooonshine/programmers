import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder s = new StringBuilder(br.readLine());
		StringBuilder t = new StringBuilder(br.readLine());

		boolean result = recursion(s, t);

		System.out.println(result ? 1 : 0);
	}

	public static boolean recursion(StringBuilder s, StringBuilder t) {
		if (s.length() == t.length()) {
			if (Objects.equals(s.toString(), t.toString())) {
				return true;
			}
			return false;
		}

		if (t.charAt(0) == 'B') {
			t.deleteCharAt(0);
			t.reverse();

			if (recursion(s, t)) {
				return true;
			}

			t.reverse();
			t.insert(0, 'B');
		}

		if (t.charAt(t.length() - 1) == 'A') {
			t.deleteCharAt(t.length() - 1);

			if (recursion(s, t)) {
				return true;
			}

			t.append('A');
		}


		return false;
	}
}




// 문자열 S, T가 주어졌을 때 S를 T로

// 문자열뒤에 A추가
// 문자열 뒤에 B를 추가하고 문자열 뒤집기 좌우로

// BAB
// ABA

// 만약 끝이 A라면
// 문자열 뒤에 A추가
// B추가 뒤집기(결과 맨 앞이 A라면 불가능)

// 만약 끝이 B라면 뒤집은 것.
// B추가 뒤집기(결과 맨 앞이 A라면 불가능)

// BABA이면
// BAB
// ABA

// BAB라면
// BA
// ABA라면
// AB

// BA라면
// B
// A

// AB라면
// 불가

// 재귀로 한다면 2^50 불가능
//


// 완탐x
// 백트래킹? x

// DP일텐데

//문자열 뒤에 A를 추가한다.
// 문자열을 뒤집고 앞에 B를 추가한다.


// 규칙이 없어
// 현재의 최선이 최선의 결론을 낼까? 아니다.
// dp라면?
// 분할 정복이라면?


// 맨 뒤에 A가 없어?
// 그럼 앞에 B를 붙인 것.
// dp란? 현재로 미래를 결정