import java.util.*;
import java.io.*;

public class Main {

	public static int[] Xs = {0, 0, 1, -1};
	public static int[] Ys = {1, -1, 0, 0};

	public static int[] weight = {0, 1, 10, 100, 1000};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int studentNumber = N * N;

		int[][] like = new int[studentNumber + 1][5];
		int[] seq = new int[studentNumber + 1];

		for (int i = 1; i <= studentNumber; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			seq[i] = p;

			for (int k = 1; k <= 4; k++) {
				like[p][k] = Integer.parseInt(st.nextToken());
			}
		}

		// N x N 배열에 앉은 번호 저장 -> seat
		int[][] seat = new int[N + 2][N + 2];
		for (int i = 0; i <= N + 1; i++) {
			seat[0][i] = -1;
			seat[N + 1][i] = -1;
			seat[i][0] = -1;
			seat[i][N + 1] = -1;
		}

		// 특정 배열에 기본 값은 (0,0) 자리 배치하면 (x, y)로 저장 -> loc
		Location[] loc = new Location[studentNumber + 1];

		// 특정 학생의 좋아하는 학생이 자리에 배치되어있는 지 알아야 돼
		for (int f = 1; f <= studentNumber; f++) {
			int target = seq[f];

			// loc을 보고 x,y가 있다면, 그 위치 및 상하좌우 빈 칸 개수 리스트에 저장, count도 함께 저장
			// 이렇게 4명의 학생을 봐
			// 리스트에 x,y가 같은 게 있으면 count를 올려
			List<Seat> seats = new ArrayList<>();
			for (int i = 1; i <= 4; i++) {
				int friend = like[target][i];
				// System.out.println("target: " + target + ", friend: " + friend);

				if (loc[friend] != null) {
					int x = loc[friend].x;
					int y = loc[friend].y;

					for (int m = 0; m <= 3; m++) {
						if (x + Xs[m] < 1 || x + Xs[m] > N || y + Ys[m] < 1 || y + Ys[m] > N) {
							continue;
						}

						if (seat[x + Xs[m]][y + Ys[m]] != 0) {
							continue;
						}

						boolean in = false;
						for (Seat tmpS : seats) {
							if (tmpS.equals(x + Xs[m], y + Ys[m])) {
								tmpS.addCount();
								in = true;
							}
						}
						if (!in) {
							int empty = 0;

							for (int l = 0; l <= 3; l++) {
								if (seat[x + Xs[m] + Xs[l]][y + Ys[m] + Ys[l]] == 0) {
									empty++;
								}
							}

							seats.add(new Seat(x + Xs[m], y + Ys[m], empty, 1));
						}
					}
				}
			}
			// System.out.println("point");

			// 아예 없는 경우
			if (seats.isEmpty()) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (seat[i][j] == 0) {
							int empty = 0;

							// 상하좌우 빈칸
							for (int l = 0; l <= 3; l++) {
								if (seat[i + Xs[l]][j + Ys[l]] == 0) {
									empty++;
								}
							}

							seats.add(new Seat(i, j, empty, 1));
						}
					}
				}
			}
			// System.out.println("seat.size(): " + seats.size());

			// 리스트를 돌면서 count가 가장 높은 것들을 추려내서 리스트에 저장해
			// 1개이면 그걸 선택해서 seat, loc 반영
			// System.out.println("point2");
			int countMax = 0;
			for (Seat tmpS : seats) {
				if (tmpS.count > countMax) {
					countMax = tmpS.count;
				}
			}
			// System.out.println("countMax: " + countMax);

			List<Seat> countSeats = new ArrayList<>();
			for (Seat tmpS : seats) {
				if (tmpS.count == countMax) {
					countSeats.add(tmpS);
				}
			}

			// System.out.println("countSeats.size(): " + countSeats.size());

			if (countSeats.size() == 1) {
				Seat resultS = countSeats.get(0);

				loc[target] = new Location(resultS.x, resultS.y);
				seat[resultS.x][resultS.y] = target;
				continue; // 다음 학생
			}

			// 여러개면 list 보면서 비어있는 칸 가장 많은 것들만 추려내
			// 1개면 그 자리
			int emptyMax = 0;
			for (Seat tmpS : countSeats) {
				if (tmpS.empty > emptyMax) {
					emptyMax = tmpS.empty;
				}
			}

			List<Seat> emptySeats = new ArrayList<>();
			for (Seat tmpS : countSeats) {
				if (tmpS.empty == emptyMax) {
					emptySeats.add(tmpS);
				}
			}

			// System.out.println("emptySeats.size(): " + emptySeats.size());

			if (emptySeats.size() == 1) {
				Seat resultS = emptySeats.get(0);

				loc[target] = new Location(resultS.x, resultS.y);
				seat[resultS.x][resultS.y] = target;
				// System.out.println("target: " + target + ", x: " + resultS.x + ", y: " + resultS.y);
				continue; // 다음 학생
			}

			// 여러개면 행이 가장 작은 것을 추려내
			// 1개면 그 자리
			int rLow = N + 2;
			for (Seat tmpS : emptySeats) {
				if (tmpS.x < rLow) {
					rLow = tmpS.x;
				}
			}

			List<Seat> rLowSeats = new ArrayList<>();
			for (Seat tmpS : emptySeats) {
				if (tmpS.x == rLow) {
					rLowSeats.add(tmpS);
				}
			}

			// System.out.println("rLowSeats.size(): " + rLowSeats.size());

			if (rLowSeats.size() == 1) {
				Seat resultS = rLowSeats.get(0);

				loc[target] = new Location(resultS.x, resultS.y);
				seat[resultS.x][resultS.y] = target;
				// System.out.println("target: " + target + ", x: " + resultS.x + ", y: " + resultS.y);
				continue; // 다음 학생
			}

			// 여러개면 열이 가장 적은 것을 추려내
			// 그 자리
			int cLow = N + 2;
			for (Seat tmpS : rLowSeats) {
				if (tmpS.y < cLow) {
					cLow = tmpS.y;
				}
			}

			for (Seat tmpS : rLowSeats) {
				if (tmpS.y == cLow) {
					Seat resultS = tmpS;

					loc[target] = new Location(resultS.x, resultS.y);
					seat[resultS.x][resultS.y] = target;
					// System.out.println("target: " + target + ", x: " + resultS.x + ", y: " + resultS.y);
					continue; // 다음 학생
				}
			}
		}

		// 마지막으로 행열 for문 돌아
		// 상하좌우에 좋아하는 학생이 있는 지 카운트해
		// 개수에 따라 1, 10 ,100, 1000을 부여해서 total에 반영해
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int target = seat[i][j];
				int total = 0;

				for (int m = 0; m <= 3; m++) {
					if (seat[i + Xs[m]][j + Ys[m]] == 0) {
						continue;
					}

					int friend = seat[i + Xs[m]][j + Ys[m]];
					for (int k = 1; k <= 4; k++) {
						if (like[target][k] == friend) {
							total += 1;
						}
					}
				}

				result += weight[total];
			}
		}

		System.out.println(result);
	}

	public static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Seat {
		int x;
		int y;
		int empty;
		int count;

		public Seat(int x, int y, int empty, int count) {
			this.x = x;
			this.y = y;
			this.empty = empty;
			this.count = count;
		}

		public void addCount() {
			this.count++;
		}

		public boolean equals(int x, int y) {
			if (this.x == x && this.y == y) {
				return true;
			}
			return false;
		}
	}
}

// 교실은 N x N
// 학생수는 N제곱
// 상하좌우가 인접
// 각 학생이 좋아하는 4명
//1. 특정학생을 앉힐 때 좋아하는 학생이 많도록
//2. 여러개면 비어있는 칸이 많도록
//3. 2도 만족하면 행이 가장 작게
//4. 3도 만족하면 열의 번호가 가장 작게

// N x N 배열에 앉은 번호 저장 -> seat
// 특정 배열에 기본 값은 (0,0) 자리 배치하면 (x, y)로 저장 -> loc

// 특정 학생의 좋아하는 학생이 자리에 배치되어있는 지 알아야 돼
// loc을 보고 x,y가 있다면, 그 위치 및 상하좌우 빈 칸 개수 리스트에 저장, count도 함께 저장
// 이렇게 4명의 학생을 봐
// 리스트에 x,y가 같은 게 있으면 count를 올려

// 리스트를 돌면서 count가 가장 높은 것들을 추려내서 리스트에 저장해
// 1개이면 그걸 선택해서 seat, loc 반영

// 여러개면 list 보면서 비어있는 칸 가장 많은 것들만 추려내
// 1개면 그 자리
// 여러개면 행이 가장 작은 것을 추려내
// 1개면 그 자리
// 여러개면 열ㅇ 가장 적은 것을 추려내
// 그 자리

// 마지막으로 행열 for문 돌아
// 상하좌우에 좋아하는 학생이 있는 지 카운트해
// 개수에 따라 1, 10 ,100, 1000을 부여해서 total에 반영해
