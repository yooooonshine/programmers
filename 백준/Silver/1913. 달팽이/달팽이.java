import java.io.*;

public class Main {
	public static int index = 0;
	public static int resultX = 0;
	public static int resultY = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 칸, 홀수
		int K = Integer.parseInt(br.readLine()); // N^2 이하

		int[][] arr = new int[N + 1][N + 1];

		index = (int)Math.pow(N , 2D); //최대 숫자

		int mid = N / 2 + 1;
		for (int i = 1; i <= mid; i++) {
			run(arr, i, N, K);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.print(resultX + " " + resultY
		);
	}

	public static void run(int[][] arr, int start, int N, int K) { // 행이 x
		int x = start;
		int y = start;

		int end = N - start + 1;

		// 중앙인 경우
		if (end == start) {
			arr[x][y] = index;

			checkIndex(x, y, index, K);

			return;
		}

		while (x < end) {
			arr[x][y] = index;

			checkIndex(x, y, index, K);

			index--;
			x++;
		}

		while (y < end) {
			arr[x][y] = index;

			checkIndex(x, y, index, K);

			index--;
			y++;
		}

		while (x > start) {
			arr[x][y] = index;

			checkIndex(x, y, index, K);

			index--;
			x--;
		}

		while (y > start) {
			arr[x][y] = index;

			checkIndex(x, y, index, K);

			index--;
			y--;
		}
	}

	public static void checkIndex(int x, int y, int index, int k) {
		if (index == k) {
			resultX = x;
			resultY = y;
		}
	}


	// 결국 숫자는 1 ~ N^2 까지야
	// 한 바퀴를 기준으로 하자
	// 11 22 33 44 55로가기
	// 언제까지? N / 2 + 1까지
	// 1 -> 5 - 1 + 1
	// 2 -> 5 - 2 + 1
}
