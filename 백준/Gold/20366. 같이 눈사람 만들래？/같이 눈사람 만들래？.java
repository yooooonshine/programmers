
import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] H = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n <= N - 1; n++) {
			H[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(H);

		int end = N - 1;

		int min = Integer.MAX_VALUE;

		for (int s = 0; s <= end; s++) {
			for (int e = s + 1; e <= end; e++) {


				int outerSum = H[s] + H[e];

				int tmpS = 0;
				int tmpE = end;
				while (tmpS < tmpE) {

					// 같은 눈덩이를 사용하는 경우는 패스
					if (tmpS == s || tmpS == e) {
						tmpS++;
						continue;
					}
					if (tmpE == e || tmpE == s) {
						tmpE--;
						continue;
					}

					int sum = H[tmpS] + H[tmpE];

					if (min > (int) Math.abs(outerSum - sum)) {
						min = (int) Math.abs(outerSum - sum);
					}

					if (outerSum > sum) {
						tmpS++;
					} else {
						tmpE--;
					}
				}
			}
		}

		System.out.println(min);
	}
}

// N 개의 눈덩이
// 각 눈덩이 지름 Hi
// 하나의 눈사람 = 두 개의 눈덩이(큰거 아래, 그 이하 위)

// 두 눈 사람 키 차이 작게


// 모든 경우의 수 600 * 600
// 정렬
// 눈덩이가 겹칠 수 있다.
// 이 방법 불가 하다.


// 이분탐색
// 투포인트
// 둘을 합친다?

// 이분탐색으로 최소 값을

// for 문

// 투포인트 한번 600
// 360000

//

// 모든 눈사람 경우의 수
// 중복된다

// 최소에 대해 이분탐색
//  a, b, c, d 라면 무조건 a + d vs b + c
// 1 3 4 5 9 10

