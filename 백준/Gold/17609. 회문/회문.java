import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 문자열의 개수

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= T; i++) {
			char[] arr = br.readLine().toCharArray();



			int s = 0;
			int e = arr.length - 1;

			int r = 0;
			boolean change = false;
			while (s <= e) {
				if (arr[s] == arr[e]) {
					s++;
					e--;

					continue;
				}

				// 같지 않은 경우, 이미 변경한 경우
				if (isEqual(arr, s + 1, e) || isEqual(arr, s, e - 1)) {
					r = 1;
					break;
				} else {
					r = 2;
					break;
				}
			}

			bw.write(r + "\n");
		}

		bw.flush();
	}

	public static boolean isEqual(char[] arr, int s, int e) {
		while (s <= e) {
			if (arr[s] != arr[e]) {
				return false;
			}
			s++;
			e--;
		}
		return true;
	}
}
// 유사회문
// 회문인지 0
// 유사회문인지(한 문자 삭제) 1
// 일반 문자열인지 2

// 투포인터로
// 양 끝에서 시작
// 양끝에서 시작하여 모두 같으면 끝
// 양끝 시작해서 하나 다르면
// 각 앞에 체크해서, 상대방 현재와 같은 게 있으면 그 쪽을 한 칸 앞으로
/// 이게 여러 번이면 x, 한번이면 ok
