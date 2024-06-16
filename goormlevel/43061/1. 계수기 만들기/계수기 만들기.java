import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //자리수
		long[] maxNums = new long[N + 1]; // 각 자리수 최대
		long[] nowNums = new long[N + 1]; // 현재 숫자
 		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			maxNums[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nowNums[i] = Long.parseLong(st.nextToken());
		}
		
		long k = Long.parseLong(br.readLine()); //횟수
		
		long nowLong = toLong(maxNums, nowNums, N);
		nowLong += k;
		
		getResult(maxNums, nowLong, N);
	}
	
	public static long toLong(long[] maxNums, long[] nowNums, int N) {
		long sum = 0L;
		long pow = 1L;
		for (int i = N; i >= 1; i--) {
			sum += pow * nowNums[i];
			pow *= maxNums[i] + 1;
		}
		
		return sum;
	}
	
	public static void getResult(long[] maxNums, long num, int N) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long[] pows = new long[N + 1];
		
		long pow = 1L;
		for (int i = N; i >= 0; i--) {
			pows[i] = pow;
			pow *= maxNums[i] + 1;
		}
		
		num %= pows[0];

		for (int i = 1; i <= N; i++) {
			bw.write(num / pows[i] + "");
			num = num % pows[i];
		}
		
		bw.flush();
		bw.close();
	}
}

//마지막 넘어가면 불변

// 예를 들어 max가 1234 라면
// 최대는 4 * 3, 2는 4 * 3을 2개, 1은 4 * 3 * 2를 1개

// 59 / 8 -> 73
