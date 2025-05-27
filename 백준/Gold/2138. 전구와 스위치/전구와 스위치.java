
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] now = new boolean[N + 2]; // 0, N + 1 포함x
		boolean[] now1 = new boolean[N + 2];
		boolean[] end = new boolean[N + 2];

		String[] tmp = br.readLine().split("");
		for (int i = 1; i <= N; i++) {
			if (tmp[i - 1].equals("0")) {
				now[i] = false;
				now1[i] = false;
			} else {
				now[i] = true;
				now1[i] = true;
			}
		}

		tmp = br.readLine().split("");
		for (int i = 1; i <= N; i++) {
			if (tmp[i - 1].equals("0")) {
				end[i] = false;
			} else {
				end[i] = true;
			}
		}

		// case1 시작 그대로
		int count1 = 0;
		for (int i = 2; i <= N; i++) {
			if (i == N) {
				if (now[i - 1] != end[i - 1]) {
					rev(now, i - 1);
					rev(now, i);
					count1++;
				}

				continue;
			}

			if (now[i - 1] != end[i - 1]) {
				rev(now, i - 1);
				rev(now, i);
				rev(now, i + 1);
				count1++;
			}
		}

		boolean result1 = equal(now, end, N);


		// case2 시작 변경
		int count2 = 1;
		rev(now1, 1);
		rev(now1, 2);

		for (int i = 2; i <= N; i++) {
			if (i == N) {
				if (now1[i - 1] != end[i - 1]) {
					rev(now1, i - 1);
					rev(now1, i);
					count2++;
				}

				continue;
			}

			if (now1[i - 1] != end[i - 1]) {
				rev(now1, i - 1);
				rev(now1, i);
				rev(now1, i + 1);
				count2++;
			}
		}

		boolean result2 = equal(now1, end, N);

		if (result1 && result2) {
			System.out.println(Math.min(count1, count2));
		} else if (result1) {
			System.out.println(count1);
		} else if (result2) {
			System.out.println(count2);
		} else {
			System.out.println(-1);
		}
	}

	public static void rev(boolean[] tmp, int index) {
		tmp[index] = !tmp[index];
	}

	public static boolean equal(boolean[] now, boolean[] end, int endIndex) {
		for (int i = 1; i <= endIndex; i++) {
			if (now[i] != end[i]) {
				return false;
			}
		}
		return true;
	}
}


// N개의 스위치와 N개의 전구
//  각 전구 on or off
// i번째 스위치 -> i - 1, i, i + 1 뒤집힌다.
// 최소 몇번?

// N은 10^5
//
