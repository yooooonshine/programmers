import java.util.*;
import java.io.*;

public class Main {

	public static long[] seg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int k = 1;
		while (!((int) Math.pow(2, k) > N)) {
			k++;
		}

		seg = new long[(int) Math.pow(2, k + 1)];
		Arrays.fill(seg, 1);

		int startSeq = (int)Math.pow(2, k);
		for (int i = 0; i < N; i++) {
			Long tmp = Long.parseLong(br.readLine());

			seg[startSeq + i] = tmp;
		}

		for (int i = startSeq - 1; i >= 1; i--) {
			seg[i] = seg[i *2] * seg[i * 2 + 1] % 1000000007l;
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());

				change(b - 1 + startSeq, c);
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				System.out.println(mux(b - 1 + startSeq, c - 1 + startSeq) % 1000000007);
			}

		}
	}

	public static long mux(int s, int e) {
		List<Long> tmp = new ArrayList<>();

		while (s <= e) {
			if (s % 2 == 1) {
				tmp.add(seg[s]);
			}

			if (e % 2 == 0) {
				tmp.add(seg[e]);
			}

			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}

		long result = 1;
		for (Long data : tmp) {
			result = result * data % 1000000007;
		}

		return result;
	}

	public static void change(int p, long data) {
		seg[p] = data;
		p = p / 2;

		while (p != 1) {
			seg[p] = seg[p * 2] * seg[p * 2 + 1] % 1000000007;
			p = p / 2;
		}
	}
}

// 부분의 곲? 부분 배열? 세그먼트 트리?
// 세그먼트 트리? 근데 부분 곱은 안되지 않나?

// N, M, K (N개의 수, 수의 변경 수, 구간의 곱을 구하는 횟수)
// N번 동안 N개의 수
// M + K 번 동안
// a, b, c
// a가 1이면 b번째를 c로
// a가 2이면 b부터 C까지의 곱을 구한다.

// 구간의 곱을 100000000000007로 나눈 나머지 출력
// 주의 마지막에 나누면 안된다. 계속 계속 나눠간다.

// long으로 하자.

// 세그먼트 트리?

// 세그먼트 트리 구현
// 2 ^ k > N인 k를 구한다
// 2 ^ k + 1 배열선언한다
// 2 ^ k부터 채운다.
// 2 ^ k - 1 부터 채운다. 계속 10007로 나눈다.

// 구하기
// s % 2
// e % 2
// s = (s + 1) / 2
// e = (e - 1) / 2
// s <= e 까지

// 변경하기
// 리프 노드 찾는다( 주의 인덱스 변경하기)
// 리프 노드에서 위로 이동하면서 값을 변경시킨다