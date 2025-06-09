import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 물건 수
		int M = Integer.parseInt(br.readLine()); // 물건 쌍의 수

		List<List<Edge>> adj = new ArrayList<>();
		List<List<Edge>> revAdj = new ArrayList<>();

		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>());
			revAdj.add(new ArrayList<>());
		}

		for (int m = 1; m <= M; m ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj.get(s).add(new Edge(s, e));
			revAdj.get(e).add(new Edge(e, s));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int n = 1; n <= N; n++) {
			int tmp = N;
			int tmp1 = bfs(adj, n, N);
			int tmp2 = bfs(revAdj, n, N);
			tmp -= 1 + tmp1 + tmp2;

			bw.write(tmp + "\n");
		}

		bw.flush();
	}

	public static int bfs(List<List<Edge>> adj, int s, int N) {
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(0, s));

		boolean[] visited = new boolean[N + 1];

		int count = 0; // 본인 포함

		while (!q.isEmpty()) {
			Edge edge = q.poll();
			int nowE = edge.e;

			if (visited[nowE]) continue; // 이미 방문한 노드면 건너뛰기
			visited[nowE] = true; // 방문 처리

			count++;

			for (Edge next : adj.get(nowE)) {
				q.add(next);
			}
		}

		return count - 1;
	}

	public static class Edge {
		int s;
		int e;

		public Edge(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
// N개의 물건
// 1~N

// 토폴로지 소트?

// 각 물건에 대해 비교결과를 알 수 없는 물건의 개수

// N 물건의 개수
// M 물건 쌍의 개수
// 무거운 가벼운

// 방향 그래프 두개
// 각 노드 별로 bfs
// 방문 수
// Queue
// 인접

// 결과 배열
// 전체 -  하위 노드 개수 - 하위노드 개수 - 1
