package Modules.Interfaces;

public interface Queue_Interface<T> {

	public void enqueue(T newEntry);
	
	public T dequeue();
	
	public T get_Front();
	
	public boolean isEmpty();
	
	public void Clear();
	
	public T[] toArray();
	
}
