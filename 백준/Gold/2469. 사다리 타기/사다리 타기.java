

import java.io.*;

public class Main {

	public static int K, N;
	public static char[] finalOrder;
	public static char[][] ladder;
	public static int questionIdx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		finalOrder = br.readLine().toCharArray();
		ladder = new char[N][K - 1];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			if (line.charAt(0) == '?') {
				questionIdx = i;
			}
			ladder[i] = line.toCharArray();
		}

		// 1. 위에서 내려오기
		char[] before = new char[K];
		for (int i = 0; i < K; i++) before[i] = (char) ('A' + i);
		for (int i = 0; i < questionIdx; i++) {
			for (int j = 0; j < K - 1; j++) {
				if (ladder[i][j] == '-') {
					char tmp = before[j];
					before[j] = before[j + 1];
					before[j + 1] = tmp;
				}
			}
		}

		// 2. 아래에서 올라오기
		char[] after = finalOrder.clone();
		for (int i = N - 1; i > questionIdx; i--) {
			for (int j = 0; j < K - 1; j++) {
				if (ladder[i][j] == '-') {
					char tmp = after[j];
					after[j] = after[j + 1];
					after[j + 1] = tmp;
				}
			}
		}

		// 3. 중간 상태 유추
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < K - 1; i++) {
			if (before[i] == after[i]) {
				result.append('*');
			} else if (i + 1 < K && before[i] == after[i + 1] && before[i + 1] == after[i]) {
				result.append('-');
				// swap to maintain future comparison
				char tmp = before[i];
				before[i] = before[i + 1];
				before[i + 1] = tmp;
			} else {
				// 불가능한 경우
				System.out.println("x".repeat(K - 1));
				return;
			}
		}

		System.out.println(result);
	}
}
