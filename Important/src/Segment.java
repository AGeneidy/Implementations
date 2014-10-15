import java.util.Arrays;

public class Segment {
	int[] arr;
	int[] lazy;

	public Segment(int[] b) {
		// TODO Auto-generated constructor stub
		arr = new int[b.length * 4];
		lazy = new int[b.length * 4];
		Arrays.fill(lazy, 0);
		build(1, 0, b.length - 1, b);
	}

	private void build(int node, int j, int k, int[] b) {
		// TODO Auto-generated method stub
		if (j > k)
			return;
		if (k == j) {
			arr[node] = b[j];
			return;
		}
		build(2 * node, j, (k - j) / 2 + j, b);
		build(2 * node + 1, 1 + (k - j) / 2 + j, k, b);
		arr[node] = Math.min(arr[node * 2], arr[node * 2 + 1]);
	}

	public int query(int node, int l, int r, int lowerBound, int upperBound) {
		if (r < l || l > upperBound || r < lowerBound)
			return Integer.MAX_VALUE;
		if (r <= upperBound && l >= lowerBound)
			return arr[node];
		int p1 = query(2 * node, l, (r - l) / 2 + l, lowerBound, upperBound);
		int p2 = query(2 * node + 1, (r - l) / 2 + l + 1, r, lowerBound,
				upperBound);
		return Math.min(p1, p2);
	}

	public void update(int node, int l, int r, int lowerBound, int upperBound,
			int val) {
		if (l > upperBound || l > r || r < lowerBound)
			return;
		if (l == r) {
			arr[node] += val;// leaf
			return;
		}
		update(2 * node, l, (r - l) / 2 + l, lowerBound, upperBound, val);
		update(2 * node + 1, 1 + (r - l) / 2 + l, r, lowerBound, upperBound,
				val);
		arr[node] = Math.min(arr[node * 2], arr[node * 2 + 1]);
	}

	// lazy propagation;

	public void updateLazy(int node, int l, int r, int lowerBound,
			int upperBound, int val) {
		if (l > upperBound || r < lowerBound || l > r)
			return;
		if (lazy[node] != 0) {
			arr[node] += lazy[node];
			if (l != r) {
				lazy[node * 2 + 1] += lazy[node];
				lazy[node * 2] += lazy[node];
			}
			lazy[node] = 0;
		}
		if (l >= lowerBound && r <= upperBound) {
			arr[node] += val;
			if (l != r) {
				lazy[2 * node + 1] += val;
				lazy[2 * node + 1] += val;
			}
			return;
		}
		updateLazy(node * 2, l, (r - l) / 2 + l, lowerBound, upperBound, val);
		updateLazy(node * 2 + 1, (r - l) / 2 + l + 1, r, lowerBound,
				upperBound, val);
		arr[node] = Math.min(arr[2 * node + 1], arr[2 * node]);
	}
	public int queryLazy(int node , int l , int r , int lowerBound , int upperBound){
		if(l>upperBound||r<lowerBound||l>r)return 1000000;
		if (lazy[node] != 0) {
			arr[node] += lazy[node];
			if (l != r) {
				lazy[node * 2 + 1] += lazy[node];
				lazy[node * 2] += lazy[node];
			}
			lazy[node] = 0;
		}
//		if(l==r)return arr[node];
		if(l>=lowerBound&&r<=upperBound)return arr[node];
		int q1 = query(node*2, l, l+(r-l)/2, lowerBound, upperBound);
		int q2 = query(node*2+1, l+(r-l)/2+1,r, lowerBound, upperBound);
		return Math.min(q1, q2);
	}

}
