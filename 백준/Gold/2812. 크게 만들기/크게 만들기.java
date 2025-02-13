
import java.util.*;
import java.io.*;

public class Main {

	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		String[] tmp = br.readLine().split("");

		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(tmp[i - 1]);
		}

		Stack<Integer> myS = new Stack<>();

		int count = 0;
		boolean end = false;
		for (int i = 1; i <= N; i++) {
			if (end) {
				myS.add(arr[i]);
				continue;
			}

			while (!myS.isEmpty() && myS.peek() < arr[i]) {
				myS.pop();
				count++;
				if (count == K) {
					end = true;
					break;
				}
			}

			myS.add(arr[i]);
		}

		while (count != K) {
			myS.pop();
			count++;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i : myS) {
			bw.write(i + "");
		}
		bw.flush();

		br.close();
	}
}

// N자리 숫자 -> K개 지워서 얻을 수 있는 가장 큰 수?

// 작은 숫자 없애야 돼 웬만하면 앞에 있는 작은수
// 그리디구나
// 만약 다음 수보다 작으면 없애기

// 231234
// 31234
// 3234
// 334
// 34
// 4

// 늘 맨 앞부터스캔해서다음수가 더 크면 없애기
// 477252841
// 77252841
// 7752841
// 775841

// 하나씩 스택에 넣기

// 비어있으면 스택에 넣기
// 비어있지 않고, 현재 값보다 작으면 빼기 -> 계속 빼기
// 빼는 횟수가 K가 되면 끝
// 스택에 넣기