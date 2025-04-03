
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //N까지 정수
		int K = Integer.parseInt(st.nextToken()); // K번쨰 지워지는 수

		boolean[] used = new boolean[N + 1];

		int count = 0;
		for (int i = 2; i <= N; i++) {
			if (used[i]) {
				continue;
			}

			int index = 1;
			while (index * i <= N) {
				if (!used[index * i]) {
					count++;
					used[index * i] = true;

					if (count == K) {
						System.out.println(index * i);
						return;
					}
				}
				index++;
			}
		}
	}
}
