import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 카드의 개수
		int K = Integer.parseInt(st.nextToken()); // 카드를 섞은 횟수

		int[] S = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		int[] D = new int[N + 1];
		st= new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			D[i] = Integer.parseInt(st.nextToken());
		}

		int[] P = new int[N + 1];
		for (int i = 0; i < K; i++) {
			for (int j = 1; j <= N; j++) {
				P[D[j]] = S[j];
			}

			for (int j = 1; j <= N; j++) {
				S[j] = P[j];
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(S[i] + " ");
		}
	}
}