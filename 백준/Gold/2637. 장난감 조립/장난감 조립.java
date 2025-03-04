import java.util.*;
import java.io.*;

public class Main {

	public static int[][] price;
	public static int[] in;

	public static int N;

	public static List<List<Edge>> adj;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 부품 번호 수
		int M = Integer.parseInt(br.readLine()); // 관계 수

		price = new int[N + 1][N + 1]; // 각 r의 생산 비용
		for (int i = 1; i <= N; i++) {
			price[i][i] = 1;
		}

		in = new int[N + 1]; // 진입 차수

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int e = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(s, e, w));
			in[e]++;
			price[e][e] = 0;
		}

		topology();

		// 출력은 기본 부품의 수가 작은 거부터.번호 + 개수
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= N; i++) {
			if (price[N][i] != 0) {
				bw.write(i + " " + price[N][i] + "\n");
			}
		}

		bw.flush();
	}

	public static void topology() {
		Queue<Edge> myQ = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (in[i] == 0) {
				myQ.add(new Edge(i, i, 0));// w사용 x
			}
		}

		while (!myQ.isEmpty()) {
			Edge edge = myQ.poll();
			int nS = edge.s;
			int nE = edge.e;
			int nW = edge.w;

			for (Edge tmp : adj.get(nE)) {
				int tE = tmp.e;
				int tW = tmp.w;

				// 생산비용 계산
				for (int i = 1; i <= N; i++) {
					price[tE][i] += price[nE][i] * tW;
				}

				in[tE]--;

				if (in[tE] == 0) {
					myQ.add(new Edge(nE, tE, 0));
				}
			}
		}
	}

	public static class Edge {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}


// 기본부품 or 중간부품 조립 -> 중간부품
// 부품들끼리 조립
//

// 부품5 = 2 * 기본1 + 2 * 기본2
// 부품6 = 2 * 중간5 + 3 * 기본3 + 4 * 기본4
// 완제품7 = 2 * 중간5 + 3 * 중간6 + 5 * 기본4

// 2 (2 * 기본1 + 2 * 기본2) + 3(2 * 중간5 + 3 * 기본3 + 4 * 기본4) + 5 *기본4
// 4기본1 + 4기본2 + 6중간5 + 9기본3 + 17기본4
// 16기본1 + 16기본2

// 기본 부품의 종류별 개수 개신 -> 재귀?

// N이 100이하 N^2까지?
// N 완제품번호
// M 100 개의 줄
// X, Y, K (X를 만드는데 부품 Y가 K개 필요)
// int 사용

// 왼쪽은 모두 중간부품

// 재귀로 해서 기본부품이면 모두 더하면 될 거 같다?

// 만드는 순서가 정해져있다.
// 7 <- 6
// 7 <- 5
// 7 <- 4
// 6 <- 3
// 6 <- 4
// 6 <- 5
// 5 <- 1
// 5 <- 2

// 2차원 배열 사용
// 각 r 별 필요한 c 수


// 진입 차수 0인것을 큐에 넣는다.
// 0인 노드를 방문한다.
// 주위 노드를 탐색하며 진입차수를 1씩 줄인다.
// 각 노드를 확인 후 e에 s 배열을 읽어 w만큼 곱해 추가한다.
// 진입차수가 0이면 엣지에 넣는다.