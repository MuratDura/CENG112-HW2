

public class WaitingList<T> implements QueueInterface<T>, DequeInterface<T>  {
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private static final int MAX_CAPACITY = 10000;

	private int numberOfEntries = 0;
	
	public WaitingList(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
	}
	
	@Override
	public void addToFront(T newEntry) {
		if (isEmpty()) queue[frontIndex] = newEntry;
		ensureCapacity();
		if (frontIndex-1 < 0) {
			queue[queue.length-1] = newEntry;
			frontIndex = queue.length-1;
		}
		else {
			frontIndex = (frontIndex-1)%queue.length;
			queue[frontIndex] = newEntry;
		}
		numberOfEntries++;
	}

	@Override
	public void addToBack(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex+1)%queue.length;
		queue[backIndex] = newEntry;
		numberOfEntries++;
	}

	@Override
	public T removeFront() {
		if (isEmpty()) return null;
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex+1)%queue.length;
			numberOfEntries--;
			return front;
		}
	}

	@Override
	public T removeBack() {
		if (isEmpty()) return null;
		else {
			T back = queue[backIndex];
			queue[backIndex] = null;
			backIndex = (backIndex-1)%queue.length;
			numberOfEntries--;
			return back;
		}
	}

	@Override
	public T getBack() {
		return queue[backIndex];
	}

	@Override
	public void enqueue(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex+1)%queue.length;
		queue[backIndex] = newEntry;
		numberOfEntries++;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) return null;
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex+1)%queue.length;
			numberOfEntries--;
			return front;
		}
	}

	@Override
	public T getFront() {
		return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex+1)%queue.length);
	}

	@Override
	public void clear() {
		while (!isEmpty()) { 
			dequeue();
		}
	}
	
	private void ensureCapacity() {
		if (frontIndex == (backIndex+2)%queue.length) {
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = 2*oldSize;
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			for (int index = 0; index < oldSize-1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex+1)%oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}

	public int getLength(){return numberOfEntries;}

	

	
}
