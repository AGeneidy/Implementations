public class Quik {
	public static void main(String[] args) {
		int[] s = new int[] { 9, 6, 8, 4, 10, 90, 7 };
		quik(0, 6 , s);
		for (int u : s)
			System.out.print(u + " ");

	}
	
	
	public static void quik(int l , int r , int [] arr){
		int index = parition(l,r,arr);
		if(l<index-1)quik(l, index-1, arr);
		if(r>index)quik(index, r, arr);
	}


	private static int parition(int l, int r, int[] arr) {
		// TODO Auto-generated method stub
		int pivot = arr[(r+l)/2];
		while(l<=r){
			while(arr[l]<pivot)l++;
			while(arr[r]>pivot)r--;
			if(l<=r){
				int tmp = arr[l];
				arr[l] = arr[r];
				arr[r] = tmp;
				r--;
				l++;
			}
		}
		return l;
	}
}
