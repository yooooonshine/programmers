
import java.util.*;
import java.io.*;

public class Main {

	public static int[] fires;
	public static int M;
	public static int N;
	public static int L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 사대 수
		N = Integer.parseInt(st.nextToken()); // 동물의 수
		L = Integer.parseInt(st.nextToken()); // 사정거리

		// 사대 저장
		fires = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			fires[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(fires);
		
		if (M == 1) {
			int fireX = fires[0];
			int count = 0;
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				if (y + (int)Math.abs(x - fireX) <= L) {
					count++;
				}
			}

			System.out.println(count);
			return;
		}

		// 동물 별로 게산
		int count = 0;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int fireX = fires[bs(x)];

			if (y + (int)Math.abs(x - fireX) <= L) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static int bs(int x) {
		int s = 0;
		int e = M - 1;

		int index = 0;

		while (s <= e) {
			int m = (s + e) / 2;

			int midV = fires[m];

			if (midV < x) {
				s = m + 1;
			} else if (midV > x) {
				e = m - 1;
			} else {
				index = m;
				break;
			}
			index = m;
		}

		// index 기준으로 가까운 사대 찾기
		if (index == 0) {
			int midV = fires[index];
			int rightV = fires[index + 1];

			if (Math.abs(x - rightV) < Math.abs(x - midV)) {
				return index + 1;
			} else {
				return index;
			}

		} else if (index == M - 1) {
			int leftV = fires[index - 1];
			int midV = fires[index];

			if (Math.abs(x - leftV) <= Math.abs(x - midV)) {
				return index - 1;
			} else {
				return index;
			}

		} else {
			int leftV = fires[index - 1];
			int midV = fires[index];
			int rightV = fires[index + 1];

			int result = index;


			if (Math.abs(x - leftV) <= Math.abs(x - midV)) {
				result = index - 1;
			}
			if (Math.abs(x - rightV) < Math.abs(x - fires[result])) {
				result = index + 1;
			}

			return result;
		}
	}
}

// N마리의 동물들이 위치에 삼
// 일직선상의 M개의 총쏘는 곳에서만 사격
// x축, 사대 위치 x1, ~ xM
// 동물 위치 (a, b) ~ N개

// 사정거리 L, L보다 작은 위치(거리는 x-a + b) , 끝포함

// 이분탐색, 투포인트,

// 다 확인하는 건 안돼
// 시간초과 나

// 그리디 되나
// 보통 x거리가 가장 가까운게 높지 않나
// x거리로 이분탐색해야 하나

// 동물에 대해 for문
// 이분탐색으로 x가 가장 가까운 사대 찾기
// 계산

// 1 3 7 9 10

// 5