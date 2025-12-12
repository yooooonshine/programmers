
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static BufferedWriter bw;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Tree firstT = new Tree();

		int N = Integer.parseInt(br.readLine());

		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int K = Integer.parseInt(st.nextToken());

			String[] vTmp = new String[K];
			for (int k = 0; k < K; k++) {
				vTmp[k] = st.nextToken();
			}

			firstT.add(vTmp, 0);
		}

		// 출력
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		firstT.print(0);
		bw.flush();
	}

	public static class Tree {
		Map<String, Tree> sub;

		public Tree() {
			this.sub = new HashMap<>();
		}

		public void add(String[] arr, int index) {
			// 끝까지 넣고, 다음 거 넣을 때
			if (arr.length == index) {
				return;
			}

			// 없으면 추가
			if (!sub.containsKey(arr[index])) {
				sub.put(arr[index], new Tree());
			}

			sub.get(arr[index]).add(arr, index + 1);
		}

		public void print(int index) throws Exception {
			List<String> keyList = new ArrayList<>(this.sub.keySet());
			Collections.sort(keyList);

			for (String v : keyList) {
				for (int i = 0; i < index; i++) {
					bw.write("--");
				}
				bw.write(v);
				bw.newLine();
				sub.get(v).print(index + 1);
			}
		}
	}
}

// 로봇 개미, 센서
// 각 층에 먹이가 있느 방을 따라 내려가다 먹이가 없으면 신호
// --가 층
// 각 층 여러 방이 있으면, 사전순서가 앞서는 먹이이

// 먹이 정보 개수 N
// n 줄 동안 K, 지나온 먹이 순서
// t는 15
// 사전숲 앞서는 먹이 정보 먼저 출력먹

// 트리를 만들어야 하나
// 트리 자식 모음 있고
// 자식에 대해 탐색?
// Map으로 하면 될듯?
// 클래스가 map을 가지고 있고
// keySet을 정렬하여 출력하면 될거같으넫
