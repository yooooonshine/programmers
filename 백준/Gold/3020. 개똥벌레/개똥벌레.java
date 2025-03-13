
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //길이, 항상 짝수
		int H = Integer.parseInt(st.nextToken()); // 높이

		int[] down = new int[N / 2]; // 0번부터 짝
		int[] up = new int[N / 2]; // 1번부터 홀
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());

			if (i % 2 == 0) {
				down[i / 2] = tmp;
			} else {
				up[i / 2] = tmp;
			}
		}
		Arrays.sort(down);
		Arrays.sort(up);

		int[] Hs = new int[H + 1]; // 장애물 수
		for (int i = 1; i <= H; i++) {
			Hs[i] += binary(down, i);
			Hs[i] += binary(up, H - i + 1);

		}

		Arrays.sort(Hs);

		int value = Hs[1];
		int count = 1;
		for (int i = 2; i <= H; i++) {
			if (value != Hs[i]) {
				break;
			}
			count++;
		}

		System.out.println(value + " " + count);
	}

	public static int binary(int[] arr, int h) {
		int s = 0;
		int e = arr.length - 1;
		int m = 0;

		int last = arr.length;

		while (s <= e) {
			m = (s + e) / 2;

			if (h <= arr[m]) {
				e = m - 1;
				last = m;
			} else {
				s = m + 1;
			}
		}

		return arr.length - last;
	}
}


/// 이제 찾는건 각 높이별로 넘는 장애물 수
/// 그럼 down의 경우 자신과 높이가 같은 것을 찾으면 되네. 이게 가장 왼쪽
/// up의 경우 위에서부터 탐색 h라면 H - h + 1 와 같은 것을 찾는다. lower