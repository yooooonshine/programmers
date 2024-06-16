import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //노드 개수
		int M = Integer.parseInt(st.nextToken()); //변경 횟수
		int K = Integer.parseInt(st.nextToken()); //구간 합 횟수

		long[] data = new long[N + 1]; //1번 인덱스부터 초기화
		for (int i = 1; i <= N; i++) {
			data[i] = Long.parseLong(br.readLine());
		}

		int r = 0; //깊이 - 1
		while(true) {
			if (Math.pow(2, r) > N) {
				break;
			}
			r++;
		}
		//세그먼트 트리
		long[] tree = new long[(int) Math.pow(2, r + 1)];

		//초기화
		makeSegment(tree, data, r);

		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine()); // 1이면 변경, 0이면 구간합)
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				update(tree, b + (int)Math.pow(2, r) - 1, c);
			} else {
				System.out.println(getAnswer(tree, b + (int)Math.pow(2, r) - 1, (int)c + (int)Math.pow(2, r) - 1));;
			}
		}
	}

	public static void makeSegment(long[] tree, long[] data, int r) {
		int start = (int)Math.pow(2, r);
		for (int i = 1; i < data.length; i++) {
			tree[start + i - 1] = data[i];
		}

		//나머지 초기화
		for (int i = start - 1; i >= 1; i--) {
			tree[i] = tree[2 * i] + tree[2 * i + 1];
		}
	}

	public static long getAnswer(long[] tree, int start, int end) { //트리의 start 값
		List<Long> results = new ArrayList<>();

		while (start <= end) {
			if (start % 2 == 1) {
				results.add(tree[start]);
			}
			if (end % 2 == 0) {
				results.add(tree[end]);
			}
			start = (start + 1) / 2;
			end = (end - 1) / 2;
		}

		long sum = 0;
		for (int i = 0; i < results.size(); i++) {
			sum += results.get(i);
		}

		return sum;
	}

	public static void update(long[] tree, int index, long value) { //인덱스는 리프 인덱스

		tree[index] = value;
		while (index != 1) {
			int left;
			int right;
			if (index % 2 == 0) {
				left = index;
				right = index + 1;
			} else {
				left = index - 1;
				right = index;
			}

			index /= 2;
			tree[index] = tree[left] + tree[right];

		}

	}
}
