import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 책의 수
		int M = Integer.parseInt(st.nextToken()); // 옮길 수 있는 책의 최대

		PriorityQueue<Integer> mPq = new PriorityQueue<>();
		PriorityQueue<Integer> pPq = new PriorityQueue<>(Collections.reverseOrder());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int tmp = Integer.parseInt(st.nextToken());

			if (tmp < 0) {
				mPq.add(tmp);
			} else {
				pPq.add(tmp);
			}
		}

		int sum = 0;
		// 더 큰 거 찾기
		if (!mPq.isEmpty() == !pPq.isEmpty()) {
			int m1 = (int)Math.abs(mPq.peek());
			int p1 = pPq.peek();

			// 한 방향 해결
			if (m1 > p1) {
				mPq.poll();

				for (int i = 2; i <= M; i++) {
					if (!mPq.isEmpty()) {
						mPq.poll();
					}
				}

				sum += m1;
			} else {
				pPq.poll();

				for (int i = 2; i <= M; i++) {
					if (!pPq.isEmpty()) {
						pPq.poll();
					}
				}

				sum += p1;
			}
		} else if (!mPq.isEmpty()) {
			int m1 = (int)Math.abs(mPq.peek());
			mPq.poll();

			for (int i = 2; i <= M; i++) {
				if (!mPq.isEmpty()) {
					mPq.poll();
				}
			}

			sum += m1;
		} else {
			int p1 = pPq.peek();
			pPq.poll();

			for (int i = 2; i <= M; i++) {
				if (!pPq.isEmpty()) {
					pPq.poll();
				}
			}

			sum += p1;
		}

		// 각 pq 처리
		// mq
		while (!mPq.isEmpty()) {
			int max = (int)Math.abs(mPq.poll());

			for (int i = 2; i <= M; i++) {
				if (!mPq.isEmpty()) {
					mPq.poll();
				}
			}

			sum += max * 2;
		}


		// pq
		while (!pPq.isEmpty()) {
			int max = pPq.poll();

			for (int i = 2; i <= M; i++) {
				if (!pPq.isEmpty()) {
					pPq.poll();
				}
			}

			sum += max * 2;
		}

		System.out.println(sum);
	}
}
// 현재 0, 책도 0
// 최소 걸음수
// 마지막에 0 돌아올 필요  x
// 최대 M권
// 책의 수 N

// 최대 큰거는 왕복x
// 3 * 2 = 6
// 5 * 2 = 10
// 1 * 2 = 2
// = 18 + 11 = 29
// 가장 큰건
// 가장 큰 것 부터
// 맨 처음 가장 큰것은 왕복x
// 음수 양수 분리
// 11
// 5 * 2
// 3 * 2

// -39 -37 -29 -28 -6 2 11

// 39 + 29 * 2 + 12 + 22
// 34 + 58 + 39 = 73 + 58 = 131