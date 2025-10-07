import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int R;
	public static int C;

	public static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //2^N
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		recursion(0, 0, N, 0);
		System.out.println(result);
	}

	public static void recursion(int r, int c, int n, int count) {
		int width = (int)Math.pow(2, n);

		if (width == 2) {
			if (r == R && c == C) {
				result = count;
			} else if (r == R && c + 1 == C) {
				result = count + 1;
			} else if (r + 1 == R && c == C) {
				result = count + 2;
			} else {
				result = count + 3;
			}

			return;
		}


		int half = width / 2;
		int halfC = half * half;

		int nowR = r;
		int nowC = c;
		if (nowR <= R && R < nowR + half && nowC <= C && C < nowC + half) {
			recursion(nowR, nowC, n - 1, count);
			return;
		}
		count += halfC;

		nowR = r;
		nowC = c + half;
		if (nowR <= R && R < nowR + half && nowC <= C && C < nowC + half) {
			recursion(nowR, nowC, n - 1, count);
			return;
		}
		count += halfC;

		nowR = r + half;
		nowC = c;
		if (nowR <= R && R < nowR + half && nowC <= C && C < nowC + half) {
			recursion(nowR, nowC, n - 1, count);
			return;
		}
		count += halfC;

		nowR = r + half;
		nowC = c + half;
		if (nowR <= R && R < nowR + half && nowC <= C && C < nowC + half) {
			recursion(nowR, nowC, n - 1, count);
			return;
		}
		count += halfC;

	}
}
// n > 1이면 4등분으로 쪼갠다.
// 4등분을 for문 돌면서
// r,c가 있는 위치면
// 재귀
// 없으면 패스

// 주의 0,0 포함
