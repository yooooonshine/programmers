
import java.util.*;
import java.io.*;

public class Main {

	public static int[][] world;
	public static boolean[][] visit;
	public static int[] uf;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로

		world = new int[N + 2][M + 2];
		visit = new boolean[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= M; k++) {
				world[i][k] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 색칠하기
		int islandCount = 2; //섬은 2부터
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (visit[r][c]) {
					continue;
				}

				recursion(islandCount, r, c);

				// 0,1 모두 아니라면 방금 방문한것
				if (world[r][c] != 1 && world[r][c] != 0) {
					islandCount++;
				}
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		// 엣지 추가하기
		// 우방향
		// 하 방향
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {

				// 섬이면

				// 길이 1칸은 안돼
				if (world[r][c] != 0) {
					int length = 0;

					// 세로 다음 섬 찾기
					for (int tr = r + 1; tr <= N; tr++) {
						if (world[tr][c] != 0) {
							// 길이 조건 맞으면 넣기
							if (length >= 2) {
								pq.add(new Edge(world[r][c], world[tr][c], length));
							}
							break;
						}
						length++;
					}

					// 가로 다음 섬 찾기
					length = 0;
					for (int tc = c + 1; tc <= M; tc++) {
						if (world[r][tc] != 0) {
							if (length >= 2) {
								pq.add(new Edge(world[r][c], world[r][tc], length));
							}
							break;
						}
						length++;
					}
				}
			}
		}

		uf = new int[islandCount]; // 2번인덱스부터 사용
		for (int i = 2; i < islandCount; i++) {
			uf[i] = i;
		}

		int totalW = 0;
		while(!pq.isEmpty()) {
			Edge tEdge = pq.poll();
			int s = tEdge.s;
			int e = tEdge.e;
			int w = tEdge.w;

			if (find(s) != find(e)) {
				union(s, e);
				totalW += w;
			}
		}

		// 연결 안된 섬이 있으면  -1
		for (int i = 2; i < islandCount - 1; i++) {
			if (find(uf[i]) != find(uf[i + 1])) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(totalW);
	}

	public static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);

		if (ar > br) {
			uf[ar] = br;
		} else if (ar < br) {
			uf[br] = ar;
		}
	}

	public static int find(int a) {
		if (a == uf[a]) {
			return a;
		}

		return uf[a] = find(uf[a]);
	}


	public static int[] rs = {1, -1, 0, 0};
	public static int[] cs = {0, 0, 1, -1};
	public static void recursion(int islandCount, int r, int c) {
		if (!visit[r][c] && world[r][c] == 1) {
			visit[r][c] = true;
			world[r][c] = islandCount;

			for (int i = 0; i <= 3; i++) {
				int tR = r + rs[i];
				int tC = c + cs[i];
				recursion(islandCount, tR, tC);
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.w - edge.w;
		}
	}
}

// 다리의 방향이 중간에 바뀌면 안된다.
// 다리 길이는 2이상
// 다른 섬과 인접하게 지나도 연결 x
// 다리 양끝에는 섬이 있어야 한다.
// 겹쳐도 따로 계산한다.

// 다리 길이의 최솟값?

// 숫자가 작네..
// 최솟값? 그래프 문제일까? 하지만 직선인걸

// 최소 신장트리네..
// 각섬의 테두리에서 나아갔을때 다른 섬에 도착하면 엣지를 넣는다.
// 이후 엣지에 대해 정렬한다/
// 엣지에서 작은 거 먼저 빼면서 유니온 파인드

// 특정 섬 군집을 찾아야 하는데.
// 섬들의 테두리는 어떻게 구하지
// 테두리는 나중에 for문 돌리면서 경계찾으면 돼
// 일단 색칠부터 해야 돼

// 재귀로 찾는다. 특정 점의 상하좌우로 들어간다
