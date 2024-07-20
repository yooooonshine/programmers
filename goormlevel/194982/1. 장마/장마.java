import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 장마 기간
		
		int[] K = new int[N + 1]; // 땅 높이
		st = new StringTokenizer(br.readLine()); 
		for (int i = 1; i <= N; i++) {
			K[i] = Integer.parseInt(st.nextToken());
		}
		
		int S[] = new int[M + 1]; // 1번부터
		int E[] = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			E[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] tmp = new int[N + 1];
		for (int i = 1; i <= M; i++) {
			for (int j = S[i]; j <= E[i]; j++) {
				tmp[j]++;
			}
			
			if (i % 3 == 0) {
				for (int k = 1; k <= N; k++) {
					if (tmp[k] != 0) {
						K[k] += tmp[k] - 1;
						tmp[k] = 0;
					}
				}
			}
		}
		
		for (int k = 1; k <= N; k++) {
			if (tmp[k] != 0) {
				K[k] += tmp[k];
				tmp[k] = 0;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(K[i] + " ");
		}
	}
}
// N개의 마을(1~N)
//땅 높이Ki 
// M일동안 비
// si ~ ei 까지 비 -> 물 높이 +1
// 3의 배수 날 비 내린 뒤 배수
// 2일 이내에 비 내린 곳에만  -> 물 높이 1감소

// 장마 끝 -> 땅 높이 물의 높이ㅁ나큼 증가

// 비가 오면 높이를 1증가시키고 rain을 true로 한다.
// 3의 배수날에는 true인 곳을 -1 하고 rain을 false로 한다.