import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mancher {
	String str;
	int len;
	String modified;
	int[] p;
	int longest = -1;

	public Mancher(String u) {
		// TODO Auto-generated constructor stub
		str = u;
		modified = preProcess(u);
		len = str.length();
		parse();
	}

	private void parse() {
		// TODO Auto-generated method stub
		p = new int[modified.length()];
		int R = 0, C = 0;
		char[] s = modified.toCharArray();
		for (int i = 1; i < p.length - 1; i++) {
			int dash = C - (i - C);
			if (R > i)
				p[i] = Math.min(p[dash], R - i);
			while (s[i + p[i] + 1] == s[i - p[i] - 1])
				p[i]++;
			if (p[i] + i > R) {
				R = p[i] + i;
				C = i;
			}
		}
	}

	private String preProcess(String u) {
		// TODO Auto-generated method stub
		char[] w = new char[u.length() * 2 + 3];
		w[0] = '$';
		for (int i = 0; i < u.length(); i++) {
			w[i * 2 + 1] = '#';
			w[i * 2 + 2] = u.charAt(i);
		}
		w[u.length() * 2 + 1] = '#';
		w[u.length() * 2 + 2] = '@';
		return new String(w);
	}

	public String longestPlaindromAt(int i) {
		i += 2;
		int len = p[i];
		int center = i;
		return str.substring((center - len - 1) / 2, (center + len - 1) / 2);
	}

	public String longestPalindrom() {
		int center = 0, len = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] > len) {
				len = p[i];
				center = i;
			}
		}
		return str.substring((center - 1 - len) / 2, (center - 1 + len) / 2);
	}

	public String getMaxLen(){
		String max="";
		for(int i = 0 ; i < len-1 ; i++){
			String w = palindrom(i,i);
			if(w.length()>max.length())max = w;
			w = palindrom(i,i+1);
			if(w.length()>max.length())max = w;
		}
		return max;
	}

	private String palindrom(int i, int j) {
		// TODO Auto-generated method stub
		while(i>=0&&j<len&&str.charAt(i)==str.charAt(j)){i--;j++;}
		return str.substring(i+1,j);
	}
}
