import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static List<List<Integer>> tree;
	public static List<List<Integer>> revTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			tree = new ArrayList<>();
			revTree = new ArrayList<>();

			for (int n = 0; n <= N; n++) {
				tree.add(new ArrayList<>());
				revTree.add(new ArrayList<>());
			}

			// edge 받기
			for (int n = 1; n <= N - 1; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); // 부모
				int B = Integer.parseInt(st.nextToken()); // 자식

				tree.get(A).add(B);
				revTree.get(B).add(A);
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N1 = Integer.parseInt(st.nextToken());
			int N2 = Integer.parseInt(st.nextToken());

			// 루트 구하기
			int root = 0;
			for (int n = 1; n <= N; n++) {
				if (revTree.get(n).size() == 0) {
					root = n;
					break;
				}
			}

			// 깊이 구하기
			int[] degree = new int[N + 1];
			bfs(root, degree);

			int nowV1 = N1;
			int nowV2 = N2;

			if (degree[nowV1] > degree[nowV2]) {
				while (true) {
					nowV1 = revTree.get(nowV1).get(0);

					if (degree[nowV2] == degree[nowV1]) {
						break;
					}
				}
			} else if (degree[nowV1] < degree[nowV2]) {
				while (true) {
					nowV2 = revTree.get(nowV2).get(0);

					if (degree[nowV2] == degree[nowV1]) {
						break;
					}
				}
			}

			while (true) {
				if (nowV1 == nowV2) {
					break;
				}

				nowV1 = revTree.get(nowV1).get(0);
				nowV2 = revTree.get(nowV2).get(0);
			}

			System.out.println(nowV1);
		}
	}

	public static void bfs(int root, int[] degree) {
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(root, 0));

		while (!q.isEmpty()) {
			Edge now = q.poll();
			int nowE = now.e;
			int nowD = now.d;

			degree[nowE] = nowD;

			for (int next : tree.get(nowE)) {
				q.add(new Edge(next, nowD + 1));
			}
		}
	}

	public static class Edge {
		int e;
		int d;

		public Edge(int e, int d) {
			this.e = e;
			this.d = d;
		}
	}
}

// 가장 가까운 노드

// 가장 가까운 공통 조상

// 깊이를 정한다? 깊이ㅔㅇ 따라 이동?
// 각 깊이를 구한다. 깊이를 같게해서 1씩 올라간다.
