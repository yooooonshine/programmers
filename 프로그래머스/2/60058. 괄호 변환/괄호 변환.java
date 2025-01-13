import java.util.*;
import java.io.*;

class Solution {
    public String solution(String p) {
        
        if (p.equals("")) {
            return "";
        }
        
        String[] tmp = part(p);
        
        return recursion(tmp[0], tmp[1]);
    }
    
    public String[] part(String U) {
        int left = 0; // (
        int right = 0; // )
        
        String[] tmp = new String[2];
        tmp[0] = "";
        tmp[1] = "";
        
        for (int i = 0; i < U.length(); i++) {
            String apartS = U.substring(i, i + 1);
            
            if (apartS.equals("(")) {
                left++;
            } else {
                right++;
            }
            tmp[0] += apartS;
            
            if (left != 0 && right != 0 && left == right) {
                tmp[1] = U.substring(i + 1, U.length());
                break;
            }
        }
        
        return tmp;
    }
    
    public String recursion(String U, String V) {
        if (U == "") {
            return "";
        }
        
        String[] tmp = part(V);
        
        if (isRight(U)) {
            return U + recursion(tmp[0], tmp[1]);
        } else {
            return "(" + recursion(tmp[0], tmp[1]) + ")" + makeNewU(U);
        }
    }
    
    public String makeNewU(String u) {
        String tmp = u.substring(1, u.length() - 1);
        
        String result = "";
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '(') {
                result += ")";
            } else {
                result += "(";
            }
        }
        
        return result;
    }
    
    public boolean isRight(String u) {
        Stack<String> myS = new Stack<>();
        
        for (int i = 0; i < u.length(); i++) {
            if (myS.isEmpty()) {
                myS.add(u.substring(i, i + 1));
            } else if(myS.peek().equals("(") && u.substring(i, i + 1).equals(")")) {
                myS.pop();
            } else {
                myS.add(u.substring(i, i + 1));
            }
        }
        
        if (!myS.isEmpty()) {
            return false;
        }
        return true;
    }
}

// 괄호 짝?

// ()개수 맞으면 균형잡힌 문자열
// 짝도 맞으면 올바른 괄호 문자열

// 입력이 비어으면 빈 문자열
// u는 분리할 수 없는 균형잡힌, v는 빈문자열 가능
//

// 재귀 함수(분리하는 함수) U, V 받는다.

// V를 u, v로 분리
// v에 대해 재귀 (u, v)
// 올바르면 return U + 재귀(u, v)
// 올바르지 않으면 ( + 재귀(u, v) + ) + U에서 첫, 마지막 제거 및 뒤집기

// 올바른 문자열
// 스택?