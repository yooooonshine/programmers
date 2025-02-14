import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Queue<Integer> myQ = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			myQ.add(i);
		}

		while (myQ.size() != 1) {
			myQ.poll();
			int tmp = myQ.poll();
			myQ.add(tmp);
		}

		System.out.println(myQ.peek());
	}
}

// N장의 카드 1~N
// 제일 위에 카드 버린다.
// 그 다음 위를 제일 아래에 넣는다.

