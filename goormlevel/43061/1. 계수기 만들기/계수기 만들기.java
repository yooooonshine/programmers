import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N + 1]; // 최댓값
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] B = new int[N + 1]; // 초기값
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		int K = Integer.parseInt(br.readLine());
		
		int max = 1;
		for (int i = 1; i <= N; i++) {
			max = max * (A[i] + 1);
		}
		
		int tmp[] = new int[N + 1];
		for (int i = 0; i < N; i++) {
			
			tmp[i] = K / max;
			K %= max;
			max /= (A[i + 1] + 1);
		}
		tmp[N] = K;
		
		for (int i = N; i >= 1; i--) {
			B[i] += tmp[i];
			
			if (B[i] > A[i]) {
				B[i] -= A[i] + 1;
				B[i - 1]++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(B[i]);
		}
	}
}

// 7 * 10 * 8 * 8
// 64 * 7 = 4480
// 6400
// 64
// 320 

//5
//5
//1

//14523 + 551
// 
// N개의 자릿수
// i는 0부터 Ai까지 숫자 표시
// 가장 왼쪽 최댓값 -> 전파 x