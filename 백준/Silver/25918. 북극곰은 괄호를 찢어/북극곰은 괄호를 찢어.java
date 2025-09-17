import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 마을 수
		String S = br.readLine();
		String[] arr = S.split("");
		Stack<String> myS = new Stack();

		int max = 0;

		for (int i = 0; i < N; i++) {
			if (myS.isEmpty()) {
				myS.add(arr[i]);
			} else if (myS.peek().equals(arr[i])) {
				myS.add(arr[i]);
			} else {
				int size = myS.size();
				if (max < size) {
					max = size;
				}

				myS.pop();
			}
		}

		if (!myS.isEmpty()) {
			System.out.println(-1);
		} else {
			System.out.println(max);
		}
	}
}

// (()))((())
// stack에는 (를 쌓는다.
// (의 개수는 곧 깊이
// (( -> 깊이 2
// ) 들어오면 뺸다
// 깊이 반영
// ( 가 없느데 ) 가들어온다

// ()() 는 OO나 O다음 X로도 볼 ㅜㅅ있다.
// )()(
// 반대 마주치면 바로 뺴는게 낫나?
// (())()

// (의 개수와 )의 개수는 항상 같아야 함.
// (가 쌓여있을 떄 )가 오면 무조건 빼는 게 이득인가?
// 그치
// )만 쌓인 경우 다음에 무조건 (가 와야 하나?
// ) 누적되어도 된다/


