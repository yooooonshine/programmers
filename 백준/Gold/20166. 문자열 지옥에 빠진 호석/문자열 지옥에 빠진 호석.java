
import java.util.*;
import java.io.*;

public class Main {

	public static Tree first;
	public static int R;
	public static int C;
	public static int K;
	public static String[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 배열 채우기
		arr = new String[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 1; c <= C; c++) {
				arr[r][c] = tmp[c - 1];
			}
		}

		// 모든 배열의 트리 값 계산하기
		first = new Tree();
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				bfs(r, c);
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 신 문자열
		for (int k = 1; k <= K; k++) {
			String god = br.readLine();
			bw.write(first.getCount(god, 0) + "\n");
		}

		bw.flush();
	}

	public static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c, 0, arr[r][c]));

		while (!q.isEmpty()) {
			Node now = q.poll();
			int nowR = now.r;
			int nowC = now.c;
			int nowI = now.index;
			String nowV = now.v;

			// 반영
			first.add(nowV, 0);

			if (nowI == 4) {
				continue;
			}

			for (int i = 0; i < 8; i++) {
				int nextR = nowR + rs[i];
				int nextC = nowC + cs[i];

				// 넘으면 패스
				if (nextR < 1) {
					nextR = R;
				}
				if (nextR > R) {
					nextR = 1;
				}
				if (nextC < 1) {
					nextC = C;
				}
				if (nextC > C) {
					nextC = 1;
				}

				q.add(new Node(nextR, nextC, nowI + 1, nowV + arr[nextR][nextC]));
			}
		}
	}

	public static int[] rs = {0, 0, -1, 1, 1, 1, -1, -1};
	public static int[] cs = {1, -1, 0, 0, 1, -1, -1, 1};

	public static class Node {
		int r;
		int c;
		int index;
		String v;

		public Node(int r, int c, int index, String v) {
			this.r = r;
			this.c = c;
			this.index = index;
			this.v = v;
		}
	}

	public static class Tree {
		Tree[] sub;
		int count;

		public Tree() {
			this.sub = new Tree[26];
			this.count = 0;
		}

		public void add(String v, int index) {
			char tmp = v.charAt(index);

			// 자식 없으면 생성
			if (sub[tmp - 'a'] == null) {
				sub[tmp - 'a'] = new Tree();
			}

			if (v.length() - 1 == index) {
				// sub의 count 증가
				this.sub[tmp - 'a'].count++;
			} else {
				// 재귀
				sub[tmp - 'a'].add(v, index + 1);
			}
		}

		public int getCount(String v, int index) {
			char tmp = v.charAt(index);

			// 자식 없으면 생성
			if (sub[tmp - 'a'] == null) {
				sub[tmp - 'a'] = new Tree();
			}

			if (v.length() - 1 == index) {
				return this.sub[tmp - 'a'].count;
			} else {
				return sub[tmp - 'a'].getCount(v, index + 1);
			}
		}
	}
}

// 바닥은 격자모양타일
// N행 M열
// 11, ~ N,M
// 상하좌우, 대각선, 같은 곳 재방문가능
// 이동마다 알파벳붙여 문자열
// 문자열 K개
// 내가 만들수 있는 경우의 수


// 1000* (64000)
// 10^7 * 100
// 넘는데?

// 1000번을 없애야 하는구나
// 모두 한번 한다음
// 그걸저장해둬야 하는구나

// 해시맵을 통해 저장하는방법