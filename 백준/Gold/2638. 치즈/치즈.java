import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;

	public static int[][] arr;
	public static int[][] contact;

	public static int[] rs = {1, 0, -1, 0};
	public static int[] cs = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //r
		M = Integer.parseInt(st.nextToken()); //c

		arr = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int resultCount = 0;
		while (true) {
			contact = new int[N + 1][M + 1];
			boolean[][] visit = new boolean[N + 1][M + 1];

			bfs(1, 1, visit);
			// 끝내는 조건

			boolean change = false;
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					if (contact[r][c] >= 2) {
						arr[r][c] = 0;
						change = true;
					}
				}
			}

			if (change) {
				resultCount++;
			} else {
				break;
			}
		}

		System.out.println(resultCount);
	}

	public static void bfs(int r, int c, boolean[][] visit) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));

		// 치즈거나, 이미 방문했다면 종료
		if (arr[r][c] == 1 || visit[r][c]) {
			return;
		}

		while (!q.isEmpty()) {
			Node node = q.poll();
			int nowR = node.r;
			int nowC = node.c;

			if (visit[nowR][nowC]) {
				continue;
			}
			visit[nowR][nowC] = true;

			for (int i = 0; i <= 3; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				if (nextR <= 0 || nextR >= N + 1 || nextC <= 0 || nextC >= M + 1) {
					continue;
				}

				if (arr[nextR][nextC] == 1) {
					contact[nextR][nextC]++;
					continue;
				}

				q.add(new Node(nextR, nextC));
			}
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

// N 세로
// M 가로
// 내부는 외부 x
// 두면

// 모두 없어지는 시간

// 외부 접촉 배열
// 모든 겉 테두리에서 그래프 탐색
// 0 -> 1 만나면 외부접촉배열 ++
// for문 돌면서 외부접촉 배열이 2이상인 경우 0으로 변경 및 외부접촉 배열도 0
// 외부접촉 배열 변경이 없으면 끝
