import java.util.*;
import java.io.*;

public class Main {

	public static int N, L, R; // 인구차 L명 이상, R명 이하, N x N크기 땅

	public static int[] rs = {-1, 1, 0, 0};
	public static int[] cs = {0, 0, -1, 1};

	public static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N + 2][N + 2];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i <= N + 1; i++) {
			arr[i][N + 1] = -1;
			arr[i][0] = -1;
			arr[0][i] = -1;
			arr[0][N + 1] = -1;
		}

		int resultCount = 0;

		while (true) {
			boolean change = false;
			boolean[][] visit = new boolean[N + 2][N + 2];

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (!visit[r][c]) {
						Result result = dfs(r, c, visit);

						int avg = result.sum / result.nodes.size();

						if (result.nodes.size() >= 2) {
							for (Node node : result.nodes) {
								arr[node.r][node.c] = avg;
							}
							change = true;
						}
					}
				}
			}

			if (!change) {
				break;
			}
			resultCount++;
		}

		System.out.println(resultCount);
	}

	public static Result dfs(int r, int c, boolean[][] visit) {
		Stack<Node> stack = new Stack<>();
		stack.add(new Node(r, c));

		List<Node> team = new ArrayList<>();
		int sum = 0;

		while(!stack.isEmpty()) {
			Node node = stack.pop();
			int nowR = node.r;
			int nowC = node.c;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			team.add(node);
			sum += arr[nowR][nowC];

			for (int i = 0; i <= 3; i++) {
				int tmpR = nowR + rs[i];
				int tmpC = nowC + cs[i];
				if (tmpR < 1 || tmpR > N || tmpC < 1 || tmpC > N) {
					continue;
				}
				if (arr[tmpR][tmpC] == -1) {
					continue;
				}

				int abs = (int)Math.abs(arr[nowR][nowC] - arr[tmpR][tmpC]);
				if (L <= abs && abs <= R) {
					stack.add(new Node(tmpR, tmpC));
				}
			}
		}
		return new Result(team, sum);
	}

	public static class Result {
		List<Node> nodes;
		int sum;

		public Result(List<Node> nodes, int sum) {
			this.nodes= nodes;
			this.sum = sum;
		}
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}

// 변경 = false
// dfs를 통해 모든 탐구 가능한 부분의 인구수 합을 구한다.
// 방문한 곳이 몇개인지도 구한다.
// 이 때 노드가 2개 이상이면 평균치로 만든다.
// 변경 = true
// 이렇게 모든 방문하지 않은 노드들을 탐색한다.
// 변경이 false면 끝낸다.

// r행 c열
// 각 땅 나라
// 각 나라는 A[r][c]명
// 국경선 공유 두 나라 인구차 L명이상, R명이하 -> 국경선 연다.
// 연합 각 칸 인구수 = 연합의 인구수 / 연합 칸 개수(소수점 버림) -> int?

// 인구 이동이 며칠동안 발생하는지

// 유니온파인드? 안된다.
// 탐색
// dfs하면?
// 한번당 대략 10^4
// 가능하다.