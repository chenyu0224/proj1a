
public class ArrayDeque <Group>{
	private int size;
	private Group[] items;
	private int nextFirst;
	private int nextLast;
	
	public ArrayDeque() {
		size = 0;
		items = (Group[]) new Object[8];
		nextFirst = 0;
		nextLast = 1;
	}
	
	/**
	 * to test whether the array is full
	 * @return
	 */
	private boolean isFull() {
		return size == items.length;
	}
	
	/**
	 * to test if the array is empty
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * to test if we need to cut down the size
	 * @return
	 */
	private boolean downR() {
		return items.length >= 16 && (items.length > 4*size);
	}
	
	/**
	 * to increase idex by one circularly
	 * @param idex
	 * @return
	 */
	private int plus(int idex) {
		return (idex + 1) % items.length;
	}
	
	/**
	 * to minus idex by one circularly
	 * @param idex
	 * @return
	 */
	private int minus(int idex) {
		return (idex - 1 + items.length) % items.length;
	}
	
	
	/**
	 * resize items to reach our need
	 * @param capacity
	 */
	private void resize(int capacity) {
		Group[] a = (Group []) new Object[capacity];
		int beforeindex = (nextFirst + 1) % items.length;
		for( int i = 0; i< size; i++) {
			a[i] = items[beforeindex];
			 beforeindex = plus(beforeindex);
		}
		items = a;
		nextFirst = capacity - 1;
		nextLast = size;
	}
	
	/**
	 * add Group x to the front of items
	 * @param x
	 */
	public void addFirst(Group x) {
		
		if(isFull()) {
			resize(size*2);//��ʱRFACTOR=0.5;
		}
		items[nextFirst] = x;
		nextFirst = minus(nextFirst);
		size += 1;
	}
	
	/**
	 * add Group x to the last of the ArrayList
	 * @param x
	 */
	public void addLast(Group x) {
		
		if(isFull()) {
			resize(size*2);//��ʱRFACTOR=0.5;
		}
		items[nextLast] = x;
		nextLast = plus(nextLast);
		size += 1;
	}
	
	
	/**
	 * remove the first one of the items
	 */
	public Group removeFirst() {
		if(downR()) {
			resize(items.length/2);
		}
		nextFirst = plus(nextFirst);
		Group returnItem = items[nextFirst];
		items[nextFirst] = null;
		size -= 1;
		return returnItem;
	}
	
	/**
	 * remove the last one of the items
	 */
	public Group removeLast() {
		if(downR()) {
			resize(items.length/2);
		}
		nextLast = minus(nextLast);
		Group returnItem = items[nextLast];
		items[nextLast] = null;
		
		if(isEmpty()) {
			size -= 1;
		}
		return returnItem;
	}

	
	/**
	 * return the size of items
	 * @return
	 */
	public int size() {
		return size;
	}
	
	public Group get(int idex) {
		for(int i = 0; i <= idex; i++ ) {
			nextFirst = plus(nextFirst);
		}
		return items[nextFirst];
	}
	
	/**
	 * print the ArrayDeque
	 */
	public void printDeque() {
		for(int i = plus(nextFirst); i != nextLast; i = plus(i)) {
			System.out.print(items[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayDeque<Integer> items = new ArrayDeque<>();
		items.addFirst(5);
		items.addFirst(7);
		items.addLast(63);
		items.addLast(45);
		items.addLast(89);
		items.addLast(34);
		System.out.println(items.size);
//		System.out.println(items.get(1));
//		items.remove();
		items.printDeque();
		System.out.println(items.get(3));
		
	}
}
