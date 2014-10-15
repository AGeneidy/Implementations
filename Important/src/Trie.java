import java.util.ArrayList;
import java.util.List;

public class Trie {
	Node root;

	public Trie() {
		root = new Node();
	}

	public Trie(String[] words) {
		root = new Node();
		for (String word : words)
			root.insert(word.toLowerCase());
	}

	public boolean contains(String word) {
		 if(word==null||word.length()==0)return false; // asummption
		return root.containsWord(word.toLowerCase());
	}

	public class Node {
		Node[] children;
		char val;
		boolean isWord, isLeaf;

		public Node() {
			children = new Node[26];// lowerCase
			isWord = false;
			isLeaf = true;
		}

		public Node(char u) {
			val = u;
			children = new Node[26];// lowerCase
			isWord = false;
			isLeaf = true;
		}

		public void insert(String word) {
			// TODO Auto-generated method stub
			 isLeaf = false;
			char c = word.charAt(0);
			if (children[c - 'a'] == null)
				children[c - 'a'] = new Node(c);
			Node child = children[c - 'a'];
			if (word.length() > 1)
				child.insert(word.substring(1));
			else
				child.isWord = true;
		}

		public boolean contains(String word) {
			if (word.length() == 0)
				return true;
			char c = word.charAt(0);
			if (children[c - 'a'] != null)
				return children[c - 'a'].contains(word.substring(1));
			return false;
		}

		public boolean containsWord(String word) {
			if (word.length() == 0)
				return isWord;
			char c = word.charAt(0);
			if (children[c - 'a'] != null)
				return children[c - 'a'].containsWord(word.substring(1));
			return false;
		}
	}
}
