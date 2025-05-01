
import java.util.*;
import java.io.*;

public class Main {

	public static int N; // n가지 동전
	public static int K; // 만들 합

	public static int[] dpA;
	public static int[] arr;

	public static int MAX = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		dpA = new int[K + 1];

		dpA[0] = 0;
		for (int i = 1; i <= K; i++) {
			dpA[i] = MAX;
		}

		for(int i=1;i<=K;i++){
			dpA[i]=MAX;
		}
		for(int i=1;i<=N;i++){
			for(int j=arr[i];j<=K;j++){
				dpA[j]=Math.min(dpA[j],dpA[j-arr[i]]+1);
			}
		}

		if (dpA[K] == MAX) {
			System.out.println(-1);
		} else {
			System.out.println(dpA[K]);
		}
	}
}