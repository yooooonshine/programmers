import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long G = Long.parseLong(br.readLine());

		// 최대 n찾기
		int n = 1;
		while (2 * n - 1 < (int)G) {
			n++;
		}

		int s = n - 1;
		int e = n;

		List<Integer> list = new ArrayList<>();
		while (s > 0 && e > 0) {
			long tmp = (long)Math.pow(e, 2) - (long)Math.pow(s, 2);
			if (tmp == G) {
				list.add(e);
				e--;
			} else if ( tmp > G) {
				e--;
			} else {
				s--;
			}
		}

		if (list.isEmpty()) {
			System.out.println(-1);
			return;
		}

		Collections.sort(list);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i : list) {
			bw.write(i + "\n");
		}
		bw.flush();
	}
}

// 1. 2n - 1 < G인 최대 n을 찾는다.
// 2. n과 n - 1로 포인터 시작한다.
// 맞으면 넣고 n을 줄인다.
// 넘기면 n을 줄인다.
// 좁으면 n - 1을 줄인다.


// G킬로그램 = 현재 몸무게 ^ 2 - 기존 몸무게 ^ 2
// 성원이의 가능한 몸무게 오름 차순

// 4^2 - 1^2
// 8^2 - 7^2
// G 100,000

// G = 4

// 1
// 4
// 9
// 16
// 25
// 36
// 49

// n^ - (n-1)^2
// n^ - (n^ -2n + 1)
// 2n - 1
// 최대 가능한 수는 2n - 1 < G인거구나


// 2^2 - 1^2
// 3 1, 3 2
// 4 1, 4 2, 4 3
// 5 1, 5 2, 5 3, 5 4
// 6 1, 6 2, 6 3, 6 4, 6 5
// 7 1, 7 2, 7 3, 7 4, 7 5, 7 6
// 8 1, 8 2, 8 3, 8 4, 8 5, 8 6, 8 7
// 2n - 1 < G를 넘는 순간 어떤 순간에도 그 격차를 줄일 수 없다.

// G = 25
// -> 13

// 투포인터이다.
// 87 체크 성공 ->
// 7 7 좁음 7 6 좁음 7 5 넘김
// 6 5 좁음 6 4 넘김
// 5 4 좁음 5 3 넘김
// 4 3 좁음 4 2 좁음 4 1 맞음
// 3 1 좁음 3 0