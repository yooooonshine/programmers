
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // tc 번호
			int M = Integer.parseInt(st.nextToken()); // 노드 수
			int P = Integer.parseInt(st.nextToken()); // 간선 수

			int[][] max = new int[M + 1][3];

			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i <= M; i++) {
				adj.add(new ArrayList<>());
			}

			int[] in = new int[M + 1];
			for (int i = 1; i <= P; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				adj.get(A).add(B);
				in[B]++;
			}

			Queue<Integer> myQ = new LinkedList<>();
			for (int i = 1; i <= M; i++) {
				if (in[i] == 0) {
					myQ.add(i);
					max[i][2] = 1;
					max[i][1] = 0;
				}
			}

			int result = 0;
			while (!myQ.isEmpty()) {
				int node = myQ.poll();

				for (int tmpN : adj.get(node)) {
					in[tmpN]--;

					if (max[tmpN][0] < max[node][2]) {
						max[tmpN][0] = max[node][2];
						max[tmpN][1] = 1;
					} else if (max[tmpN][0] == max[node][2]) {
						max[tmpN][1]++;
					}

					if (in[tmpN] == 0) {
						myQ.add(tmpN);

						if (max[tmpN][1] >= 2) {
							max[tmpN][2] = max[tmpN][0] + 1;
						} else {
							max[tmpN][2] = max[tmpN][0];
						}
					}
				}

				if (myQ.isEmpty()) {
					result = max[node][2];
				}
			}

			System.out.println(K + " " + result);
		}
	}
}

// 유향 그래프?
// 강이 시작, 합쳐지거나 나눠지는 곳, 바다와 만나는 곳
// 네모는 순서, 동그라미는 노드 번호
// 근원 순서는 1
// 하천계의 순서는 마지막노드

//  T 테스트케이스 수
// K, M, P(테스트 케이스 번호, 노드의 수, 간선의 수)
// A. B (P개의 줄)

// 진입 차수가 중요하네 토폴로지 소트인가?


// 토폴로지 솥,
// 모든 노드의 진입 차수를 계산한다.
// 큐 사용
// 0인 노드에서 시작한다.
// 시작 노드의 인접노드의 진입 차수를 1씩 뺀다.
// 진입 차수가 0이 되면 큐에 넣는다.

// 각 노드는 진입하는 노드의 순서와 개수를 알아야 한다.
// 배열을 따로 둔다.
// 가장 큰 값과 개수만 저장하면 된다.
// 2차원 배열
// 0번은 가장 큰 값
// 1번은 개수

// O(N + V)
