import java.util.ArrayList;

public class KMP {
	int len;
	char[] str;

	public KMP(String u) {
		str = u.toCharArray();
		len = str.length;
	}
	public String getRepeted(){
		int [] drop = failureFunction(new String(str));
		int u = drop.length-drop[drop.length-1];
		if(drop.length%u==0)
			return (new String(str)).substring(0,u);
		return null;
	}
	public ArrayList<Integer> getPosition(String w) {
		int[] drop = failureFunction(w);
		char[] pat = w.toCharArray();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0, k = 0; i < str.length; i++) {
			while (k > 0 && str[i] != pat[k])
				k = drop[k - 1];
			if (str[i] == pat[k])
				k++;
			if (k == pat.length) {
				ans.add(i - k + 1);
				k = drop[k - 1];
			}
		}
		return ans;
	}

	private int[] failureFunction(String w) {
		// TODO Auto-generated method stub
		int[] d = new int[w.length()];
		if (w.length() == 0)
			return d;
		d[0] = 0;
		for (int i = 1, k = 0; i < d.length; i++) {
			while (k > 0 && w.charAt(i) != w.charAt(k))
				k = d[k - 1];
			if (w.charAt(i) == w.charAt(k))
				d[i] = ++k;
			else
				d[i] = k;
		}
		return d;
	}
	
}