import java.util.*;
import java.io.*;

public class Main {
	public static int min = Integer.MAX_VALUE;
	public static int[][] room;
	public static List<Point> points;
	public static int N;
	public static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기

		room = new int[N + 2][M + 2]; // 0포함
		for (int i = 0; i <= M + 1; i++) {
			room[0][i] = 6;
			room[N + 1][i] = 6;
		}
		for (int i = 0; i <= N + 1; i++) {
			room[i][0] = 6;
			room[i][M + 1] = 6;
		}

		// cctv 리스트
		points = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				room[i][j] = tmp;

				if (tmp >= 1 && tmp <= 5) {
					points.add(new Point(i, j, tmp));
					room[i][j] = 7;
				}
			}
		}

		for (Point point : points) {
			if (point.type == 5) {
				goU(point.r, point.c);
				goD(point.r, point.c);
				goL(point.r, point.c);
				goR(point.r, point.c);
			}
		}

		calc(0);

		System.out.println(min);
	}

	public static void calc(int index) {
		if (index == points.size()) {
			int count = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (room[i][j] == 0) {
						count++;
					}
				}
			}

			if (min > count) {
				min = count;
			}
			return;
		}

		Point point = points.get(index);
		int r = point.r;
		int c = point.c;
		int type = point.type;

		// 5번은 패스
		if (type == 5) {
			calc(index+1);
		}
		List<Point> tmp1;
		List<Point> tmp2;
		List<Point> tmp3;

		if (type == 1) {
			tmp1 = goU(r, c);
			calc(index + 1);
			back(tmp1);

			tmp1 = goD(r, c);
			calc(index + 1);
			back(tmp1);

			tmp1 = goL(r, c);
			calc(index + 1);
			back(tmp1);

			tmp1 = goR(r, c);
			calc(index + 1);
			back(tmp1);
		}

		if (type == 2) {
			tmp1 = goL(r, c);
			tmp2 = goR(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);

			tmp1 = goU(r, c);
			tmp2 = goD(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);
		}

		if (type == 3) {
			tmp1 = goU(r, c);
			tmp2 = goL(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);

			tmp1 = goL(r, c);
			tmp2 = goD(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);

			tmp1 = goR(r, c);
			tmp2 = goD(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);

			tmp1 = goR(r, c);
			tmp2 = goU(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);
		}

		if (type == 4) {
			tmp1 = goU(r, c);
			tmp2 = goL(r, c);
			tmp3 = goD(r, c);
			calc(index+1);
			back(tmp1);
			back(tmp2);
			back(tmp3);

			tmp1 = goR(r, c);
			tmp2 = goD(r, c);
			tmp3 = goL(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);
			back(tmp3);

			tmp1 = goU(r, c);
			tmp2 = goD(r, c);
			tmp3 = goR(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);
			back(tmp3);

			tmp1 = goU(r, c);
			tmp2 = goL(r, c);
			tmp3 = goR(r, c);
			calc(index + 1);
			back(tmp1);
			back(tmp2);
			back(tmp3);

		}
	}

	public static class Point {
		int r;
		int c;
		int type;

		public Point(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}

	public static void back(List<Point> points) {
		for (Point point : points) {
			room[point.r][point.c] = 0;
		}
	}

	public static List<Point> goU(int r, int c) {
		List<Point> tmp = new ArrayList<>();
		while (room[r][c] != 6) {
			if (room[r][c] == 0) {
				room[r][c] = 7;
				tmp.add(new Point(r, c, 0));
			}
			r--;
		}

		return tmp;
	}

	public static List<Point> goD(int r, int c) {
		List<Point> tmp = new ArrayList<>();
		while (room[r][c] != 6) {
			if (room[r][c] == 0) {
				room[r][c] = 7;
				tmp.add(new Point(r, c, 0));
			}
			r++;
		}

		return tmp;
	}

	public static List<Point> goL(int r, int c) {
		List<Point> tmp = new ArrayList<>();
		while (room[r][c] != 6) {
			if (room[r][c] == 0) {
				room[r][c] = 7;
				tmp.add(new Point(r, c, 0));
			}
			c--;
		}

		return tmp;
	}

	public static List<Point> goR(int r, int c) {
		List<Point> tmp = new ArrayList<>();
		while (room[r][c] != 6) {
			if (room[r][c] == 0) {
				room[r][c] = 7;
				tmp.add(new Point(r, c, 0));
			}
			c++;
		}

		return tmp;
	}
}

// cctv 회전
// cctv 특정방향 전체 감시 가능, 벽은 통과불가, cctv는 통과 가능
// 0 빈칸, 6은 벽, 1~5는 cctv

// 사각 지대의 최소 크기

// N,M (세로 크기, 가로크기)
// N개의 줄에 사무실 칸 정보
// cctv는 8개 이하
// 8개의 모든 방향 체크 5^8 다 확인해도 시간 남는다.
// 8 * 8 * 5 ^ 8 해도 시간이 남네 -> 그냥 구현이구나.
// 그럼 각 cctv의 뱡항은 어떻게 지정하지?

// cctv 자리는 이미 허락 x -> 7

// 1 cctv의 모든 위치를 찾는다.
// 2. 배열은 깔끔하게 두어 모든 케이스마다 레이져 쏜다.(기존 1이면 지나간다. 0이면 count --)
// 각 케이스는 어떻게 따지지?
// 1번은 0 1 2 3 을 각각 상하좌우?
// 2번은 0 1을 각각 좌우 상하
// 3번은 0 1 2 3을 상우, 우하, 우좌, 우상
// 4번은 상수로 미리 없애기

// 상 하, 좌, 우로 이동하면서 레이저 쏘는 함수 만들자.

// 그리디일까?

// 재귀로 따져야 할까?
// 재귀를 통해 돌아갈 cctv에 대해 돌린다.

// 5번 cctv는 미리 초기화
// 1~5번의 경우 별도로 리스트에 추가한다.
// 리스트 돌면서 5번 cctv를 발견하면 상하좌우로 7로 색칠한다. 색칠하면서 6을 만나면 끝낸다.
// 리스트에서 해당 원소는 제거 한다.

// Point를 만들어 cctv 위치와 타입을 저장하고, 이를 List로 저장한다.
// index를 기반으로 재귀를 돌린다.
// 재귀는 현재 지도 상황, 지금 돌릴 인덱스를 받는다.
// 재귀 함수 안에서 cctv 상황에 맞게 레이저를 저장한다. 저장할 때 변경한 부분을 기억해둔다.
// 이 상황에서 인덱스를 하나 깊게 들어간다.
// 최대 인덱스에서는 상하좌우 기반으로 하여 최소를 저장한다.
// 재귀에서 나오면 cctv 상황을 복구하고 다음 상황을 연출한다.