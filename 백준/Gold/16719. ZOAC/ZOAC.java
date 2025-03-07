import java.util.*;
import java.io.*;

public class Main {

	public static boolean[] used;
	public static int END;
	public static char[] cs;
	public static BufferedWriter bw;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		cs = br.readLine().toCharArray();
		END  = cs.length;

		used = new boolean[END];

		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		recursion(0, END - 1);
		bw.flush();
	}

	public static void recursion(int start, int end) throws Exception {

		while (start <= end) {
			int min = 10000000;
			char minChar = ' ';
			int minIndex = start;

			for (int i = start; i <= end; i++) {
				int num = cs[i] - 'a';

				if (min > num) {
					min = num;
					minChar = cs[i];
					minIndex = i;
				}
			}

			used[minIndex] = true;
			for (int i = 0; i < END; i++) {
				if (used[i]) {
					bw.write(cs[i]);
				}
			}
			bw.write("\n");
			recursion(minIndex + 1, end);

			end = minIndex - 1;
		}


	}
}

// 추가했을 때 문자열이 가장 앞에

// 규칙이 없으면 100!
// 구현 x

// 무조건 가장 빠른 문자열 뒷부분부터 시작이구나
// 약간 dp 느낌이 있다

// 5 1 3 2 4

// 특정 문자가 나오면 그 뒤부터 우선적으로 해야 하는구나

// 재귀식으로 탐색하면 안되나

// x
// stack은 가능할지도?

// 가장 작은 알파벳을 찾는다.
// 그 뒤에서 가장 작은 알파벳을 찾는다.
// 그 뒤에서 가장 작은 알파벳을 찾는다.
// 다 찾으면 나머지에서 가장 작은
// 모두를 우선순위 큐에 넣으면 좋을텐데
// 메모리 초과 날듯


// 자료구조 삘이난다.
// 스택 큐?

// 낮은 숫자 -> 높은 숫자

// 토폴로지 아니다.
// 스택, 큐, 재귀


// 4 3 1 2

// 7 8 1 6 8 4 2 5 3

// 1 2 5 3