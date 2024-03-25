package Modules.Interfaces;

public interface Stack_Interface<T> {
	
	public void push(T newEntry);
	
	public T pop();
	
	public T peek();
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public void Clear();
	
	
}
