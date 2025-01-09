import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // N개의 수
		int M = Integer.parseInt(st.nextToken()); // 명령어 개수

		int k = 0;
		while (Math.pow(2, k) <= N) {
			k++;
		}

		arr = new long[(int) Math.pow(2, k + 1)];

		int sIndex = (int) Math.pow(2, k);

		for (int l = 1; l <= M; l++) {
			st = new StringTokenizer(br.readLine());

			int mode = Integer.parseInt(st.nextToken());

			if (mode == 0) {
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());

				if (i > j) {
					int tmp = i;
					i = j;
					j = tmp;
				}

				long sum = sum(sIndex + i - 1, sIndex + j - 1);
				System.out.println(sum);
			} else {
				int i = Integer.parseInt(st.nextToken());
				long tmpk = Long.parseLong(st.nextToken());

				modify(sIndex + i - 1, tmpk);
			}
		}
	}

	public static void modify(int index, long v) {
		long tmp = arr[index];

		while (index >= 1) {
			arr[index] = arr[index] - tmp + v;
			index /= 2;
		}
	}

	public static long sum(int s, int e) {
		long sum = 0L;

		while (s <= e) {
			if (s % 2 == 1) {
				sum += arr[s];
			}

			if (e % 2 == 0) {
				sum += arr[e];
			}

			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}

		return sum;
	}
}
// 인덱스 조심!
// N, M (N개의 수, M은 명령어 개수)
// M개의 줄

// 세그먼트 트리
// 0인 경우 Sum 함수 i, j = a[i] ~ + a[j]
// 1일 경우 Modify i , k -> a[i] = k
// 시작은 모두 0

// 2^k > N 인 최소 k
// 2^(k+1) 만큼 배열 확보
// 2^k부터 초기화
// 2^k - 1부터 값 리뉴얼

// 합
// s % 2 == 1 이면 넣기
// e % 2== 0 이면 넣기
// (s + 1) / 2 이동
// (e - 1) / 2 이동

// 변경
// 값을 빼고 더한다.
// i이면 i값 뺴고 더하고
// /2해서 1까지 수정

