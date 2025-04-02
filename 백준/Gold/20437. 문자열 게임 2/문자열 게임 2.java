import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 게임 수

		for (int i = 1; i <= T; i++) {
			char[] arr = br.readLine().toCharArray(); // 문자열W
			int K = Integer.parseInt(br.readLine()); // K개 포함

			int end = arr.length - 1;

			int[][] englishCount = new int[26][2];

			int max = 0;
			int min = Integer.MAX_VALUE;

			for (int e = 0; e <= end; e++) {
				if (++englishCount[charToInt(arr[e])][0] >= K) {
					int s = englishCount[charToInt(arr[e])][1];

					for (int k = s; k <= e; k++) {
						if (arr[k] == arr[e]) {
							if (max < e - k + 1) {
								max = e - k + 1;
							}
							if (min > e - k + 1) {
								min = e - k + 1;
							}
							englishCount[charToInt(arr[k])][1] = k + 1;
							break;
						}
					}
				}
			}

			if (max == 0) {
				System.out.println(-1);
			} else {
				System.out.println(min + " " + max);
			}
		}
	}

	public static int charToInt(char a) {
		return a - 'a';
	}
}
// 이러면서 max, min 모두 체크
// 특장 어떤 개 k개가 될때까지 오른쪽 늘려
// 특정 어떤 게 k개가 안될 때까지 왼쪽 늘려
// 만약 끝이랑 같아지면 min, max 반영


// 양 끝은 두 케이스 모두 같은 문자여야 한다.
// 완전탐색으로 자신과 같아지는 지점을 찾는다.
// 개수가 같이지면 멈춘다.
// 테스트 수 떄문에 안된다.

// 양 끝 시작?

// 양 끝 중 개수가 k가 안되는 것은 넘어가도 돼.
// 양 끝 중 개수가 k보다 크면 넘어가도 돼, 그냥 넘어가면 안돼


// 알파벳 소문자로 이뤄진 문자열 W
// 양의 정수 K
// 어떤 문자를 K개 포함하는 가장 짧은 연속문자열의 길이
// 어떤 문자를 K개포함하는 가장 긴 문자열 길이, 양 끝이 해당 문자.

// 즉 가장 큰, 가장 작은,

// T회
// 투포인터

// 각 문자의 개수를 담는 배열
// 매번 문자열 개수 체크
// K개 되면

// 투포인터
// 한번에 끝내야 한다.

// 알고리즘이 필요해 nlogn 까지
// n은 가능. 투포인트
