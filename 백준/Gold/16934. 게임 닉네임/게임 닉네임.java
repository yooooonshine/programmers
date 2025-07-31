import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Trie trie = new Trie();

		for (int n = 1; n <= N; n++) {
			String str = br.readLine();

			System.out.println(trie.getAlias(str, 0));
		}
	}

	public static class Trie {
		Trie[] str;
		int count;

		public Trie() {
			this.str = new Trie[26];
			this.count = 0;
		}

		public void makeRoad(String input, int index) {


			int i = charToInt(input.charAt(index));
			if (this.str[i] == null) {
				this.str[i] = new Trie();
			}

			// 마지막이면 끝.
			if (index == input.length() - 1) {
				this.str[i].addCount();
				return;
			}

			this.str[i].getAlias(input, index + 1);
		}

		public String getAlias(String input, int index) {
			int i = charToInt(input.charAt(index));

			// 중간에 별칭 생성되면
			if (this.str[i] == null) {
				// 그 뒤에 길 만듬.
				makeRoad(input, index);

				return input.substring(0, index + 1);
			}

			// 끝까지 갔으면
			if (index == input.length() - 1) {
				this.str[i].addCount();

				if (this.str[i].count == 1) {
					return input;
				} else {
					return input + this.str[i].count;
				}
			}

			return this.str[i].getAlias(input, index + 1);
		}

		public void addCount() {
			this.count++;
		}
	}

	public static int charToInt(char a) {
		return a - 'a';
	}
}

// 닉네임 알파벳 소문자, 중복 가능
// 별칭, 내부 사용, 별칭 길이 최소화

// trie?
// 접두사중 가장 짧은 것

// 가능한 별칭 없는 경우
// 가입 시점까지 같은 닉네임으로 가입한 사람의 수 x
// x == 1인 경우 닉네임을 별칭으로 사용(자신 포함)
// x == 2 이상인 경우 뒤에 x붙여서 별칭(자신 포함)
// 같은 별칭 가능?

// 이전에 가입한 닉네임의 접두사 x

// 트라이 사용
// 1. 단어 들어옴
// 2. 트라이에서 찾아감(만듬)
// 3. 일치하는 게 존재하지 않을 경우 -> 별칭 생성
// 4. 그 뒤에 트라이에 단어 저장
// 5. 만약 끝까지 이동했는데 모두 포함
// 6. 그럼 이 단어가 있으면 -> count++ 후, x붙여서 별칭
// 7. 이 단어가 없으면 --> count++ 후, 단어를 별칭으로

//