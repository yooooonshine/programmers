import java.util.*;
import java.io.*;

public class Main {
	public static int Send;
	public static String[] Ss;
	public static String[] Ps;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String P = br.readLine();

		Ss = S.split("");
		Ps = P.split("");
		Send = Ss.length - 1;
		int Pend = Ps.length - 1;

		int index = 0;
		int r = 0;
		while (index <= Pend) {

			int count = 1;
			while (true) {
				if (index + count > Pend) {
					break;
				}

				if (correspond(index, count + 1)) {
					count++;
				} else {
					break;
				}
			}

			index += count;
			r++;
		}

		System.out.println(r);
	}

	public static boolean correspond(int s, int count) {
		for (int i = 0; i <= Send - count + 1; i++) {
			boolean result = true;

			for (int j = 0; j < count; j++) {
				if (!Ps[s + j].equals(Ss[i + j])) {
					result = false;
				}
			}

			if (result) {
				return true;
			}
		}

		return false;
	}
}

// 문자열 S
// 문자열 부분을 복사해 P라는 문자열
// copy(s, p) -> s번 문자부터 p개의 문자 복사
// S에서 copy를 통해 P를 만든다. copy 조금 사용하기
// 그리디?

// P를 분리하면 된다. 다만 최소한의로 분리한다.

// z z z 0 y y y0 x x x

// 2000 -> 1000 가능

// 현재의 최선이 미래의 최선이네 그리디

// 1. S를 모든 가능한 케이스로 쪼개서 저장한다. (큰 거 부터)
// S를 순회하면서 P에 일치하는 게 있는지 체크한다.
// 일치하면 use를 true로 한다.
// 다음 S를 순회하면서 일치하는 게 있는지 체크한다.
// 만약 우측 끝이 use가 true면 index를 false까지 이동시키고, 좌측 끝을 index로
// 분리가 아니라 합체가 편한가

// S의 부분 집합을 P의 좌측에 맞추고, 검사 맞으면 use true 처리 틀리면 index + 1
// 검사는 use가 모두 false인지 체크
// 값이 일치하는 지 체크
// 아니면 바로 리턴


// P를 모두 쪼개고 융합?