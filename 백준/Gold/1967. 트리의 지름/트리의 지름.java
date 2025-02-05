import java.util.*;
import java.io.*;

public class Main {
	public static int node;
	public static int maxW = 0;
	public static List<List<Edge>> adj;
	public static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 1; i <= n - 1; i++) {
			Integer[] tmpA = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

			adj.get(tmpA[0]).add(new Edge(tmpA[1], tmpA[2]));
			adj.get(tmpA[1]).add(new Edge(tmpA[0], tmpA[2]));
		}

		dfs(1);
		dfs(node);

		System.out.println(maxW);
	}

	public static class Edge {
		int e;
		int w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}

	public static void dfs(int s) {
		Stack<Edge> myS = new Stack<>();
		myS.add(new Edge(s, 0));

		boolean[] visited = new boolean[n + 1];

		while (!myS.isEmpty()) {
			Edge edge = myS.pop();

			if (visited[edge.e]) {
				continue;
			}
			visited[edge.e] = true;

			if (edge.w > maxW) {
				maxW = edge.w;
				node = edge.e;
			}

			for (Edge tmp : adj.get(edge.e)) {
				myS.add(new Edge(tmp.e, edge.w + tmp.w));
			}
		}
	}
}

// 노드의 수 n
// n - 1 개의 줄에 간선 정보
// 부모노드, 자식노드, 가중치
// 루트는 1

// adj로 만든다.
// 1에서 탐색을 한다.
// dfs로 탐색을 한다.
// 각 노드 방문했을 때 w가 최대 w보다 크면 노드 숫자와 최대 w를 갱신한다.
// 이 노드에서  다시 위를 반복한다.