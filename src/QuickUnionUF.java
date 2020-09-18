public class QuickUnionUF {

	private int[] id;
	private int count;
	
	public QuickUnionUF(int n){
		id = new int[n];
		count = n;
		for(int i=0;i<n;i++)
			id[i]=i;
	}
	
	/*
	 * метод повертає корінь елемента	
	 */
	private int find(int i){
		while (i!=id[i])
			i=id[i];
		return i;
	}
	
	public boolean connected(int p, int q){
		return find(p)==find(q);
	}
	
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot==qRoot)
			return;
		id[pRoot]=qRoot;
		count--;
	}
	
	public int count(){
		return count;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		for (int i:id){
			str.append(i);
			str.append(" ");
		}
		return str.toString();
	}
}
