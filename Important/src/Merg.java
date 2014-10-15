public class Merg {
	public static void main(String[] args) {
		int[] s = new int[] { 9, 6, 8, 4, 10, 90, 7 };
		merg(0, 6, new int[7], s);
		for (int u : s)
			System.out.print(u + " ");
	}

	private static void merg(int l, int r, int[] helper, int[] arr) {
		// TODO Auto-generated method stub
		if (l < r) {
			int mid = l + (r - l) / 2;
			merg(l, mid , helper, arr);
			merg(mid + 1, r, helper, arr);
			sort(l, mid, r, helper, arr);
		}
	}

	private static void sort(int l, int mid, int r, int[] helper, int[] arr) {
		// TODO Auto-generated method stub
		for (int i = l; i <= r; i++) {
			helper[i] = arr[i];
		}
		int helperLeft = l;
		int helperRight = mid + 1;
		int current = l;
		while (helperLeft <= mid && helperRight <= r) {
			if (helper[helperLeft] > helper[helperRight]) {
				arr[current++] = helper[helperRight++];
			} else {
				arr[current++] = helper[helperLeft++];
			}
		}
		for (int i = helperLeft; i <= mid; i++) {
			arr[current++] = helper[i];
		}
	}
}
