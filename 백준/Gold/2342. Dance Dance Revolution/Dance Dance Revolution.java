
import java.util.*;
import java.io.*;

public class Main {

	public static int[][][] en;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열에 입력 넣기
		List<Integer> orders = new ArrayList<>();
		orders.add(0);
		while (st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == 0) {
				break;
			}
			orders.add(tmp);
		}
		int last = orders.size() - 1;

		en = new int[5][5][orders.size()]; // 왼발, 오른발이 특정 c에 있을 때 최소 값
		for (int l = 0; l <= 4; l++) {
			for (int r = 0; r <= 4; r++) {
				Arrays.fill(en[l][r], -1);
			}
		}
		en[0][0][0] = 0;


		for (int i = 1; i <= last; i++) {
			int next = orders.get(i);
			for (int l = 0; l <= 4; l++) {
				for (int r = 0; r <= 4; r++) {
					if (en[l][r][i-1] == -1) continue;

					// 왼발 이동
					if (next != r) { // 두 발이 같은 위치에 있을 수 없음
						int cost = en[l][r][i-1] + move(l, next);
						if (en[next][r][i] == -1 || en[next][r][i] > cost) {
							en[next][r][i] = cost;
						}
					}

					// 오른발 이동
					if (next != l) {
						int cost = en[l][r][i-1] + move(r, next);
						if (en[l][next][i] == -1 || en[l][next][i] > cost) {
							en[l][next][i] = cost;
						}
					}
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int finalV = orders.get(last);
		for (int r = 0; r <= 4; r++) {
			if (en[r][finalV][last] != -1) {

				min = Math.min(min, en[r][finalV][last]);

			}

			if (en[finalV][r][last] != -1) {
				min = Math.min(min, en[finalV][r][last]);
			}
		}

		System.out.println(min);
	}

	public static int move(int now, int next) {
		if (now == 0) return 2;
		if (now == next) return 1;
		if ((now == 1 && next == 3) || (now == 3 && next == 1)) return 4;
		if ((now == 2 && next == 4) || (now == 4 && next == 2)) return 4;
		return 3;
	}
}
