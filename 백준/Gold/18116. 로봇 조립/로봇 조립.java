
import java.util.*;
import java.io.*;

public class Main {

	public static int[] arr = new int[1000001];
	public static int[] partCount = new int[1000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 요청 수

		for (int i = 1; i <= 1000000; i++) {
			arr[i] = i;
		}

		Arrays.fill(partCount, 1);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String type = st.nextToken();

			if (type.equals("I")) {
				// 같은 부품인지 알려줌

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);

			} else {
				// 질문

				int v = Integer.parseInt(st.nextToken());

				bw.write(partCount[find(v)] + "\n");
			}
		}

		bw.flush();
	}

	public static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		if (ra > rb) {
			arr[ra] = rb;
			partCount[rb] += partCount[ra];
		} else if (ra < rb) {
			arr[rb] = ra;
			partCount[ra] += partCount[rb];
		}
	}

	public static int find(int v) {
		if (arr[v] == v) {
			return v;
		}

		return arr[v] = find(arr[v]);
	}
}

// 부품은 1 ~ 10^6 정수
// 부품 i가 속한 로봇은 robot(i)

// 두 부품 -> 같은 로봇
// 지금까지 알아낸 robot(i)의 부품이 몇개야?

// 배열 10^6개 생성
// 모두 자신으로 초기화
// I이면 합치기
// 형 쪽에 개수 저장하기
// 개수저장배열은 모두 1로 초기화
// Q이면 부모 찾고, 개수저장배열 리턴
