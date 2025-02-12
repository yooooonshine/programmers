
import java.util.*;
import java.io.*;

public class Main {

	public static int[] arr;
	public static BufferedWriter bw;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N];

		func(N, M, 0);
		bw.flush();
	}

	public static void func(int N, int M, int index) throws IOException {
		if (index == M) {

			for (int i = 0; i < M; i++) {
				bw.write(arr[i] + " ");
			}
			bw.write("\n");
			return;
		}


		for (int i = 1; i <= N; i++) {
			arr[index] = i;
			func(N, M, index + 1);
		}
	}
}

// 길이가 M인 수열을 모두 구하기
// 1부터n 까지 중 M개 고르기
// 같은 수 여러번 가능

// 중복 수열은 여러 번 출력 x
// 재귀인가

// 마지막 인덱스면 N까지 돌리면서 출력
//
