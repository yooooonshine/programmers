import java.util.*;
import java.io.*;

public class Main {
	public static int[][] arr;
	public static int M;
	public static int N;
	public static List<Point> o2;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 연구소 크기
		M = Integer.parseInt(st.nextToken()); // 바이러스 수

		arr = new int[N + 2][N + 2];
		for (int i = 0; i <= N + 1; i++) {
			arr[i][0] = 1;
			arr[0][i] = 1;
			arr[i][N + 1] = 1;
			arr[N + 1][i] = 1;
		}

		o2 = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				if (arr[i][j] == 2) {
					o2.add(new Point(i, j));
				}
			}
		}

		List<Point> s2 = new ArrayList<>();
		choice2(0, s2);

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	public static class Point {
		int r;
		int c;
		int w;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
			this.w = 0;
		}

		public Point(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}


	// index는 0부터 시작
	public static void choice2(int index, List<Point> s2) {
		if (s2.size() == M) {
			int max = func(s2);

			if (min > max) {
				min = max;
			}

			return;
		}
		if (index > o2.size() - 1) {
			return;
		}

		choice2(index + 1, s2);

		s2.add(o2.get(index));
		choice2(index + 1, s2);
		s2.remove(s2.size() - 1);
	}

	// Queue로 돌리면 되겠다.
	public static int func(List<Point> s2) {
		int[][] tmpArr = new int[N + 2][N + 2];
		Queue<Point> myQ = new LinkedList<>();

		for (int i = 0; i <= N + 1; i++) {
			for (int j = 0; j <= N + 1; j++) {
				tmpArr[i][j] = arr[i][j];
			}
		}

		for (Point p : s2) {
			tmpArr[p.r][p.c] = 3;
			myQ.add(p);
		}

		int max = 0;
		while (!myQ.isEmpty()) {
			Point tmpP = myQ.poll();
			int r = tmpP.r;
			int c = tmpP.c;
			int w = tmpP.w;

			if (w > max) {
				max = w;
			}

			if (tmpArr[r - 1][c] == 0 || tmpArr[r - 1][c] == 2) {
				tmpArr[r - 1][c] = 3;
				myQ.add(new Point(r - 1, c, w + 1));
			}

			if (tmpArr[r + 1][c] == 0 || tmpArr[r + 1][c] == 2) {
				tmpArr[r + 1][c] = 3;
				myQ.add(new Point(r + 1, c, w + 1));
			}
			if (tmpArr[r][c - 1] == 0 || tmpArr[r][c - 1] == 2) {
				tmpArr[r][c - 1] = 3;
				myQ.add(new Point(r, c - 1, w + 1));
			}
			if (tmpArr[r][c + 1] == 0 || tmpArr[r][c + 1] == 2) {
				tmpArr[r][c + 1] = 3;
				myQ.add(new Point(r, c + 1, w + 1));
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (tmpArr[i][j] == 0 || tmpArr[i][j] == 2) {
					return Integer.MAX_VALUE;
				}
			}
		}

		return max;
	}
}

// M개의 바이러스
// 빈 칸, 벽
// 1초다마 상하좌우로 복제
// 0 빈칸, 1벽, 2바이러스 놓을 수 잇는 공간

// 모든 빈 칸 바이러스 퍼트리는 최소 시간

// N (연구소 크기, 50), M(바이러스 수 10)
// N개의 줄

// 주의 모든 빈 칸에 바이러스 퍼트리지 못하면 -1

// N^5 =
// 벨만 포드를 여러번 돌려? -> 이것보다 직접하는게 빠를 수도
// 그럼 NCM을 해야 하는데?

// M개 선택은 재귀를통해?

// 선택 배열
// 재귀 함수 (index, 선택 배열)
// 만약 선택된 게 M개면 함수 돌리기
// 현재 선택
// 재귀 함수(index + 1, 선택배열)
// 현재 선택 재거
// 재귀 함수(index + 1, 선택배열)

// 함수 돌리기
// 각 2의 위치에 대해 상하좌우
// 변화가 없을 때 까지