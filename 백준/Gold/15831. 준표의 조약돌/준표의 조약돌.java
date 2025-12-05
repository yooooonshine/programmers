
import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int B;
	public static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 조약돌 수
		B = Integer.parseInt(st.nextToken()); // 검은 조약돌 최대 수
		W = Integer.parseInt(st.nextToken()); // 하얀 최소 수

		String[] arr = br.readLine().split(""); // 길(N개)

		int s = 0;
		int bc = 0;
		int wc = 0;
		int max = 0;

		// 투포인트
		for (int e = 0; e < N; e++) {
			if (arr[e].equals("W")) {
				wc++;
			} else {
				bc++;
			}

			while (bc > B) {
				if (arr[s].equals("W")) {
					wc--;
				} else {
					bc--;
				}

				s++;
			}

			if (e - s + 1 > max && wc >= W) {
				max = e - s + 1;
			}
		}

		System.out.println(max);
	}
}

// 산책로 일려로 검은색, 흰색
// N개의 조약돌,
// 집에 장식
// 산책구간 모든 조약돌을 줍는다.
// 조금이라도 더 긴 구간 산책
// 까만색은 B개 이하
// 흰색은 W개 이상

// 없으면 바로 집에간다.

// 산책 구간 중 가장 긴 구간의 길이

// 그럼 일단 무조건 검은 조약돌을 최대로 하는게  이득이네?

// 투포인트 시작
// 처음,끝 모두 0에서 시작

// black이하이고, 다음이 w이면 e++
// 최대 갱신
// black개이고 다음 black이면 s++