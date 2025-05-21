import java.util.*;
import java.io.*;

public class Main {

	public static int[] G;
	public static boolean[] visit;
	public static int k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		G = new int[k + 1];
		int sum = 0;
		for (int i = 1; i <= k; i++) {
			G[i] = Integer.parseInt(st.nextToken());
			sum += G[i];
		}

		visit = new boolean[sum + 1]; // 0은 사용 x

		recursion(1, 0);

		int result = 0;
		for (int i = 1; i <= sum; i++) {
			if (!visit[i]) {
				result++;
			}
		}

		System.out.println(result);
	}

	public static void recursion(int index, int sum) {
		if (index > k) {
			if (sum < 1) {
				return;
			}
			visit[sum] = true;
			return;
		}

		recursion(index + 1, sum);
		recursion(index + 1, sum + G[index]);
		recursion(index + 1, sum - G[index]);
	}
}

// 사로다른 추 k개
// k개의 정수 gi
// 추 무게의 합 S
// 그릇의 무게는 0
// 양팔저울을 한번만 사용해 원하는 무게의 물
// 측정 불가능한 경우의 수

//1 2 6
// 인덱스, 숫자 합
//
// 재귀를 사용할거야
// 없는 상태
// 양수인 상태
// 음수인 상태
// 마지막까지 갔을 때 숫자 합하기

// 200000 * 13 = 2,600,000

// 1. 있고 없고
// 2. 양수고 음수고
// 3^13 = 27 * 27 * 27 * 27 * 3 = 243 * 243 * 3 =
