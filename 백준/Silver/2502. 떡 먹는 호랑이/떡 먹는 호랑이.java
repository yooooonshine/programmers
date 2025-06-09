import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken()); // D째 날
		int K = Integer.parseInt(st.nextToken()); // K개

		int[][] A = new int[D + 1][2]; //0이 A
		A[1][0] = 1;
		A[2][1] = 1;

		for (int i = 3; i <= D; i++) {
			A[i][0] = A[i - 1][0] + A[i - 2][0];
			A[i][1] = A[i - 1][1] + A[i - 2][1];
		}


		int index = 1;
		int result = 0;
		while (true) {

			if ((K - A[D][0] * index) % A[D][1] == 0) {
				result = (K - A[D][0] * index) / A[D][1];
				break;
			}
			index++;
		}

		System.out.println(index + "\n" + result);
	}
}

// 떡
// 어제 받은 떡 + 그저께 받은 떡 = 오늘 받을 떡
// 몇개의 떡 + 며칠
// D째 날, K개
// 처음 만난날 준 떡 A와 다음 날 떡 B 개 구하기

// 수학 아닌가?

// A
// B
// A + B
// A + 2B
// 2A + 3B
// 3A + 5B

// f(n) = f(n - 1) + f(n - 2)

// F(D) = f(D - 1) + f(D - 2) = K

// 여기서 A,B를 구해야 해

// 마지막에 정수 확인

// A[D]x + B[D]y = K

// 0 1
// 0 0 1