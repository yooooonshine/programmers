
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Set<Integer> arr1 = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				arr1.add(Integer.parseInt(st.nextToken()));
			}

			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				int tmp = Integer.parseInt(st.nextToken());

				if (arr1.contains(tmp)) {
					bw.write("1 \n");
				} else {
					bw.write("0 \n");
				}
			}


		}

		bw.flush();
	}
}

// 수첩1 실제 본 수
// M개의 질문
// X본적있나
// 주장수 수첩2