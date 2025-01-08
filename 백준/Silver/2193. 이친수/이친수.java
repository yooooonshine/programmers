import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[][] arr = new long[N + 1][2];
		arr[1][1] = 1L;

		for (int i = 2; i <= N; i++) {
			arr[i][0] = arr[i - 1][1] + arr[i - 1][0];
			arr[i][1] = arr[i - 1][0];
		}

		System.out.println(arr[N][0] + arr[N][1]);
	}
}

// 0으로 시작 x
// 1두번연속 x
//

// N자리 이친수 개수

// 2^90 -> 1초넘는다.
// dp아니고
// 알고리즘은 아니다.
// dp로 되나?

//1 로 시작
// 다음에 대한 경우의 수
// 1자리는 -> 1개
// 2자리는 -> 10 ->  1
// 3자리는 -> 100 101 -> 1 1
// 4자리는 -> 1001 1000 1010 -> 2 1
// 5자리는 -> 10010 10001 10000 10101 10100 -> 3 2

// 0번 배열 -> 이전 1일 때의 경우의 수 + 이전 0일 때의 경우의 수
// 1번 배열 -> 이전 0일 때의 경우의 수