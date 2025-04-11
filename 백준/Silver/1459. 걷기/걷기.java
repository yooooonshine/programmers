
import java.util.*;
import java.io.*;

public class Main {
	public static int X;
	public static int Y;
	public static int W; // 한 블록 시간
	public static int S; // 한 블록 가로지르기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		long result = 0;
		if (2 * W > S) {
			result += (long)Math.min(X, Y) * (long)S;

			int remain = Math.abs(X - Y);

			if (W > S) {
				result += (long)(remain / 2 * 2) * (long)S;
				result += (long)(remain % 2) * (long)W;
			} else {
				result += (long)remain * (long)W;
			}
		} else {
			if (W > S) {
				result += (long)(X / 2 * 2) * (long)S;
				result += (long)(X % 2) * (long)W;
				result += (long)(Y / 2 * 2) * (long)S;
				result += (long)(Y % 2) * (long)W;

			} else {
				result += (long)(X + Y) * (long)W;
			}
		}

		System.out.println(result);
	}
}

// 도시 크기 무한대?
// x좌표마다 세로 도로
// y좌표마다 가로도로
// 집(X,Y)
// 이동방법
// 가로 세로 한블록
// 블록 대각선

// 그리디한데?


// 1. 2W < S 면 W로만가는게 유리 -> 이 경우( x + y ) * W
// 2. 2W > S 면 S로 이동하고 마지막에만 W로 이동하는 게 유리
// -> 이 경우 min(x, y) * s + math.abs( x - y) * w