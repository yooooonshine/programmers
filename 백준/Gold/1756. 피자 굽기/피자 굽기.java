
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] oven = new int[D + 1];
		int[] doughs = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
		}

		// 위에서부터 최소값으로 갱신 (넓은 공간에 좁은 피자 안 들어가게)
		for (int i = 2; i <= D; i++) {
			oven[i] = Math.min(oven[i], oven[i - 1]);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			doughs[i] = Integer.parseInt(st.nextToken());
		}

		int pos = D;  // 시작은 맨 아래부터
		for (int i = 0; i < N; i++) {
			int dough = doughs[i];

			// 위에서 아래로 내려가는 게 아니라, 아래에서 위로 올라가야 들어갈 수 있는 자리를 찾을 수 있음
			while (pos > 0 && oven[pos] < dough) {
				pos--;
			}

			if (pos == 0) {
				System.out.println(0);
				return;
			}

			pos--;  // 이 위치에 피자가 들어갔으니, 다음 피자는 그 위에 넣어야 함
		}

		System.out.println(pos + 1);  // 마지막으로 들어간 피자의 위치
	}
}
