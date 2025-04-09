import java.util.*;
import java.io.*;

public class Main {

	public static int N, M; // 행, 열
	public static int K;
	public static int count = 0;
	public static int[][] table;
	public static int[] tableCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열

		Map<String, Integer> map = new HashMap<>();

		table = new int[N + 1][M + 1]; //r,c
		String[] stable = new String[N + 1];
		for (int r = 1; r <= N; r++) {
			String tmpS = br.readLine();
			map.put(tmpS, map.getOrDefault(tmpS, 0) + 1);
			stable[r] = tmpS;

			int[] tmpArr = Arrays.stream(tmpS.split("")).mapToInt(Integer::parseInt).toArray();
			for (int c = 1; c <= M; c++) {
				table[r][c] = tmpArr[c - 1];
			}
		}

		K = Integer.parseInt(br.readLine());

		for (int r = 1; r <= N; r++) {
			int zeroCount = 0;
			for (int c = 1; c <= M; c++) {
				if (table[r][c] == 0) {
					zeroCount++;
				}
			}

			if (zeroCount <= K && (zeroCount % 2 == K % 2)) {
				count = Math.max(count, map.get(stable[r]));
			}
		}


		System.out.println(count);
	}
}

// 특정 행을 모두 Map에 넣는다. 각 Map의 같은 패턴이 몇개인지 count한다.
// 이 후 행에 대해 for문을 돌리면서, 변경할 수 있는 지 확인
// 가능하면 그 패턴의 count로 갱신

// K번 눌렀을 때 켜져있는 행을 최대로
// 열의 버튼을 누르면 열 전체가 뒤집힘.
// 무조건 K번 누른다.(서로 다르지 않아도 된다)

// 완전탐색이어도 50 * 1000
// 괜찮다.
// 재귀를 사용한다.
// 카운터를 갖는다.
// 배열을 갖는다.
// 1~50까지 각 열을 하나씩 켜본다.
// 다음으로 넘어간다.
// K번 켰을 때, 각 줄을 확인한다. count Max를 구한다.

// 10 0 0 0 0
// 9 1 0 0 0

// 2라는 숫자 2개변경, 그대로
// 3이라는 숫자, 1개 변경, 1개 그대로
// 4라는 숫자 4개변경, 2개 변경, 그대로
// 5라는 숫자 5개 변경, 3개변경, 1개변경

// M보다 작은 K를 만든다.

// 결국 핵심은 K라고 했을 때 그 아래 홀수, 짝수번은 모두 된다는거


// 그리디한 상황이 아니다.
// 근데 결국 On, or Off 잖아
// 2 ^ 50
