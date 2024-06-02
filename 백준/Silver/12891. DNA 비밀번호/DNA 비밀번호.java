import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		int P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이

		String[] strs = br.readLine().split("");

		st = new StringTokenizer(br.readLine());
		final int A = Integer.parseInt(st.nextToken());
		final int C = Integer.parseInt(st.nextToken());
		final int G = Integer.parseInt(st.nextToken());
		final int T = Integer.parseInt(st.nextToken());

		int[] countACGT = new int[4];

		for (int i = 0; i < P; i++) {
			countACGT = plus(strs[i], countACGT);
		}

		int count = 0;
		int start = 0;
		int end = P - 1;
		while (end < S) {
			if (check(A, C, G, T, countACGT)) {
				count++;
			}
			start++;
			end++;

			if (end == S) {
				break;
			}
			countACGT = minus(strs[start - 1], countACGT);
			countACGT = plus(strs[end], countACGT);
		}

		System.out.println(count);
	}

	public static int[] minus(String target, int[] countACGT) {
		if (Objects.equals(target, "A")) {
			countACGT[0]--;
		} else if (Objects.equals(target, "C")) {
			countACGT[1]--;
		} else if (Objects.equals(target, "G")) {
			countACGT[2]--;
		} else {
			countACGT[3]--;
		}
		return countACGT;
	}

	public static int[] plus(String target, int[] countACGT) {
		if (Objects.equals(target, "A")) {
			countACGT[0]++;
		} else if (Objects.equals(target, "C")) {
			countACGT[1]++;
		} else if (Objects.equals(target, "G")) {
			countACGT[2]++;
		} else {
			countACGT[3]++;
		}
		return countACGT;
	}

	public static boolean check(int A, int C, int G, int T, int[] countACGT) {
		if (A <= countACGT[0] && C <= countACGT[1] && G <= countACGT[2] && T <= countACGT[3]) {
			return true;
		}
		return false;
	}
}
