import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();



		// ( = -1
		// ) = -2
		// )는 스택에 넣기
		// 숫자면 개수 카운터 늘려서 스택에 넣기
		// (는 숫자 꺼내기, ) 꺼내기
		// 숫자는 곱해서 다시 넣기
		// 만약 꺼낸게 바로 )면 0으로 처리

		Stack<Integer> myS = new Stack<>();

		for (int i = input.length() - 1; i >= 0; i--) {

			if (input.charAt(i) == ')') {
				myS.add(-2);
			} else if (input.charAt(i) == '(') {
				i--;

				if (myS.peek() == -2) {
					myS.pop();
					myS.add(0);
				} else {
					int tmpN = myS.pop();

					while (myS.peek() != -2) {
						tmpN += myS.pop();
					}

					myS.pop();
					int multiN = input.charAt(i) - '0';
					myS.add(tmpN * multiN);
				}
			} else {
				if (myS.isEmpty() || myS.peek() == -2) {
					myS.add(1);
				} else {
					int tmpN = myS.pop();
					myS.add(tmpN + 1);
				}
			}

		}

		int result = 0;
		while (!myS.isEmpty()) {
			result += myS.pop();
		}
		System.out.println(result);
	}


}