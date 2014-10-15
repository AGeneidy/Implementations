import java.util.ArrayList;

public class Suffix {
	Node root;

	public Suffix(String u) {
		// TODO Auto-generated constructor stub
		root = new Node();
		for (int i = 0; i <= u.length(); i++)
			root.insert(u.substring(i), i);
	}

	public ArrayList<Integer> getPosition(String u) {
		if (u == null || u.length() == 0)
			return new ArrayList<Integer>();
		return root.search(u);
	}

	public class Node {
		Node[] children;
		char val;
		ArrayList<Integer> indexes;

		public Node() {
			children = new Node[26];// asummption
			indexes = new ArrayList<Integer>();
		}

		public Node(char u) {
			children = new Node[26];// asummption
			indexes = new ArrayList<Integer>();
			val = u;
		}

		public ArrayList<Integer> search(String u) {
			if (u.length() == 0)
				return indexes;
			char c = u.charAt(0);
			if (children[c - 'a'] != null)
				return children[c - 'a'].search(u.substring(1));
			return new ArrayList<Integer>();
		}

		public void insert(String u, int index) {
			indexes.add(index);
			if (u.length() == 0)
				return;
			char c = u.charAt(0);
			if (children[c - 'a'] == null)
				children[c - 'a'] = new Node(c);
			Node child = children[c - 'a'];
			child.insert(u.substring(1), index);
		}
	}
}
