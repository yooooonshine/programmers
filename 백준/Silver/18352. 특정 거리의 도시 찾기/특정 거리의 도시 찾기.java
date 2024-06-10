import java.util.*;
import java.io.*;

class Main {
	public static int count = 0;
	public static List<Integer> results = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시 개수
		int M = Integer.parseInt(st.nextToken()); // 도로 개수
		int K = Integer.parseInt(st.nextToken()); // 거리 정보
		int X = Integer.parseInt(st.nextToken()); // 출발 도시

		List<List<Integer>> adj = new ArrayList();
		for (int i = 0; i <= N; i++) { //0은 사용x
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			adj.get(start).add(end);
		}

		boolean[] visit = new boolean[N + 1];

		bfs(adj, visit, X, K);

		if (results.isEmpty()) {
			System.out.println(-1);
		} else {
			Collections.sort(results);
			for (int result : results) {
				System.out.println(result);
			}
		}
	}

	public static void bfs(List<List<Integer>> adj, boolean[] visit, int start, int K) {
		Queue<List<Integer>> myQ = new LinkedList<>();

		int distance = 0;
		List<Integer> myList = new ArrayList<>();
		myList.add(start);
		myList.add(distance);
		myQ.add(myList);

		while (!myQ.isEmpty()) {
			List<Integer> visitNodeList = myQ.poll();
			int node = visitNodeList.get(0);
			distance = visitNodeList.get(1);

			if (visit[node]) {
				continue;
			}
			if (distance == K) {
				results.add(node);
			}

			visit[node] = true;

			List<Integer> nodeAdj = adj.get(node);
			for (int i = 0; i < nodeAdj.size(); i++) {
				int adjNode = nodeAdj.get(i);
				if (!visit[adjNode]) {
					myList = new ArrayList<>();
					myList.add(adjNode);
					myList.add(distance + 1);
					myQ.add(myList);
				}
			}
		}
	}
}