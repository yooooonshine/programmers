import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 기본. x == o 개수 or o 보다 x가 1개더
		// 이외 -> invalid

		// x빙고 카운트, o빙고 카운트
		// 1. 빈칸이 있으면 빙고가 있는지
		// -> 빙고 없으면 invalid
		// -> 빙고가 1개 -> valid
		// 1.1 x == o 이면 o빙고가 1개
		// 1.2 x가 1크면 x빙고 1개

		// 2. 빈칸이 없으면
		// -> valid

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			String input = br.readLine();

			// 종료 조건
			if (input.charAt(0) == 'e') {
				break;
			}

			int oCount = 0;
			int xCount = 0;
			int dotCount = 0;
			String[] tmp = input.split("");
			String[][] arr = new String[4][4]; //1~3
			for (int r = 1; r <= 3; r++) {
				for (int c = 1; c <= 3; c++) {
					int index = 3 * (r - 1) + c - 1;

					if (tmp[index].equals("X")) {
						arr[r][c] = "X";
						xCount++;
					} else if (tmp[index].equals("O")) {
						arr[r][c] = "O";
						oCount++;
					} else {
						arr[r][c] = ".";
						dotCount++;
					}
				}
			}

			BingoCount bingoCount = count(arr);

			int xBingoCount = bingoCount.xCount;
			int oBingoCount = bingoCount.oCount;

			// 양쪽 모두 빙고가 있으면 타당x
			if (xBingoCount >= 1 && oBingoCount >= 1) {
				bw.write("invalid\n");
				continue;
			}

			if (oCount == xCount || oCount + 1 == xCount) {
				// 빈칸 없을 때
				if (dotCount == 0) {
					if (oBingoCount >= 1) {
						bw.write("invalid\n");
					} else {
						bw.write("valid\n");
					}
				// 빈칸 있을 때
				} else {
					// 빙고가 양쪽에 있거나, 아예 없는 경우 타당x
					if (xBingoCount == 0 && oBingoCount == 0) {
						bw.write("invalid\n");
						continue;
					}

					// 둘이 똑같이 턴을 썼으면, o가 이겨야 함
					// x가 턴을 하나 더 썼으면 x가 이겨야 함
					if (oCount == xCount) {
						if (oBingoCount >= 1) {
							bw.write("valid\n");
						} else {
							bw.write("invalid\n");
						}
					} else if (oCount + 1 == xCount) {
						if (xBingoCount >= 1) {
							bw.write("valid\n");
						} else {
							bw.write("invalid\n");
						}
					} else {
						System.out.println("응");
					}
				}

			} else {
				bw.write("invalid\n");
			}
		}
		bw.flush();
	}

	// 빙고 카운트 세기
	public static BingoCount count(String[][] arr) {

		int XBingoCount = 0;
		int OBingoCount = 0;

		for (int r = 1; r <= 3; r++) {
			if (arr[r][1].equals(arr[r][2]) &&
				arr[r][2].equals(arr[r][3])
			) {
				if (arr[r][1].equals("X")) {
					XBingoCount++;
				} else if (arr[r][1].equals("O")) {
					OBingoCount++;
				}
			}
		}
		for (int c = 1; c <= 3; c++) {
			if (arr[1][c].equals(arr[2][c]) &&
				arr[2][c].equals(arr[3][c])
			) {
				if (arr[1][c].equals("X")) {
					XBingoCount++;
				} else if (arr[1][c].equals("O")) {
					OBingoCount++;
				}
			}
		}
		if (arr[1][1].equals(arr[2][2]) &&
			arr[2][2].equals(arr[3][3])
		) {
			if (arr[1][1].equals("X")) {
				XBingoCount++;
			} else if (arr[1][1].equals("O")) {
				OBingoCount++;
			}
		}
		if (arr[1][3].equals(arr[2][2]) &&
			arr[2][2].equals(arr[3][1])
		) {
			if (arr[1][3].equals("X")) {
				XBingoCount++;
			} else if (arr[1][3].equals("O")) {
				OBingoCount++;
			}
		}

		return new BingoCount(XBingoCount, OBingoCount);
	}

	public static class BingoCount {
		int xCount;
		int oCount;

		public BingoCount(int xCount, int oCount) {
			this.xCount = xCount;
			this.oCount = oCount;
		}
	}
}

//xxx
//ooo
//xox

// 양쪽에 빙고가 있으면 안됨