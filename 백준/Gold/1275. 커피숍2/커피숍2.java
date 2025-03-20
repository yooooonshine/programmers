
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int Q = Integer.parseInt(st.nextToken()); // 턴의 개수

		int k = 0;
		while ((int)Math.pow(2, k) <= N) {
			k++;
		}
		long[] tree = new long[(int)Math.pow(2, k + 1)];
		// 수 받기
		st = new StringTokenizer(br.readLine());
		int start = (int)Math.pow(2, k);
		for (int i = 0; i < N; i++) {
			tree[start + i] = Long.parseLong(st.nextToken());
		}

		// 계산하기
		for (int i = start - 1; i >= 1; i--) {
			tree[i] = tree[2 * i] + tree[2 * i + 1];
		}

		// Q 줄
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= Q; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()); // s
			int y = Integer.parseInt(st.nextToken()); // e
			int a = Integer.parseInt(st.nextToken()); // a를
			long b = Long.parseLong(st.nextToken()); // b로 변경

			if (x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			bw.write(calc(x + start - 1, y + start - 1, tree) + "\n");
			change(a + start - 1, b, tree);
		}

		bw.flush();
	}


	// 짝 홀 순
	public static long calc(int s, int e, long[] tree) {
		Stack<Long> myS = new Stack<>();

		while (s <= e) {
			if (s % 2 == 1) {
				myS.add(tree[s]);
			}
			if (e % 2 == 0) {
				myS.add(tree[e]);
			}

			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}

		long result = 0L;
		while (!myS.empty()) {
			result += myS.pop();
		}

		return result;
	}

	public static void change(int l, long v, long[] tree) {
		long tmp = tree[l];

		while (l >= 1) {
			tree[l] = tree[l] - tmp + v;

			l /= 2;
		}
	}
}
