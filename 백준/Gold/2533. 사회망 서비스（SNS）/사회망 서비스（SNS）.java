import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Edge>> adj;
	public static List<List<Edge>> tmpAdj;
	public static boolean[] early;

	public static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 노드 개수

		adj = new ArrayList<>();
		tmpAdj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
			tmpAdj.add(new ArrayList<>());
		}

		for (int n = 1; n <= N - 1; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			tmpAdj.get(s).add(new Edge(s, e));
			tmpAdj.get(e).add(new Edge(e, s));
		}

		// dfs로 탐색하면서,트리구조로 변환
		int root = 1;
		Stack<Integer> stack = new Stack<>();
		stack.push(root);
		boolean[] visited = new boolean[N + 1];
		while (!stack.isEmpty()) {
			int now = stack.pop();
			if (visited[now]) continue; // 이미 방문한 노드면 건너뛰기
			visited[now] = true;

			for (Edge edge : tmpAdj.get(now)) {
				if (!visited[edge.e]) {
					adj.get(now).add(edge); // 트리 구조로 변환
					stack.push(edge.e);
				}
			}
		}


		early = new boolean[N + 1];

		result = new int[N + 1][2]; // [노드][0: 얼리 압터, 1: 일반]
		for (int i = 1; i <= N; i++) {
			result[i][0] = -1; // 얼리 압터
			result[i][1] = -1; // 일반
		}

		System.out.println(Math.min(recursion(root, true), recursion(root, false)));
	}

	public static int[][] result;

	public static int recursion(int root, boolean early) {
		if (adj.get(root).size() == 0) {
			// 리프 노드인 경우
			if (early) {
				return 1; // 얼리 압터인 경우
			} else {
				return 0; // 일반인 경우
			}
		}


		int resultInt = 0;

		if (early) {
			if (result[root][0] != -1) {
				return result[root][0]; // 이미 계산된 값 반환
			} else {
				int sum = 1;
				for (Edge edge : adj.get(root)) {
					if (edge.e == root) continue; // 자기 자신은 제외
					sum += Math.min(recursion(edge.e, true), recursion(edge.e, false));
				}
				resultInt = sum;
			}
		} else {
			if (result[root][1] != -1) {
				return result[root][1]; // 이미 계산된 값 반환
			} else {
				int sum = 0;
				for (Edge edge : adj.get(root)) {
					if (edge.e == root) continue; // 자기 자신은 제외
					sum += recursion(edge.e, true); // 자식 노드가 얼리 압터인 경우
				}
				resultInt = sum;
			}
		}
		result[root][early ? 0 : 1] = resultInt; // 결과 저장
		return resultInt;
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

// 얼리 압터터가 아니면, 모든 친구가 얼리아텁터일 때 아이디어 받아들임
// 최소의 수의 얼리아덥터 확보
// 트리 가정