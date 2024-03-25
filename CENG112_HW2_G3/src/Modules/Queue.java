package Modules;

import Modules.Interfaces.Queue_Interface;

public class Queue<T> implements Queue_Interface<T>{

	private final static int DEFAULT_SIZE = 31;
	private final static int MAX_SIZE = 1001;
	private T[] queue;
	private int top_index;
	private int bottom_index;
	
	@SuppressWarnings("unchecked")
	public Queue(int size) {
		T[] temp;
		
		if(!(size > MAX_SIZE) || !(size <= 0)) {
			temp = (T[]) new Object[size+1];
		}else {
			System.out.println("Invalid size value! Queue initialized with default size (30)");
			temp = (T[]) new Object[DEFAULT_SIZE];
		}
		
		queue = temp;
		top_index = 0 ; bottom_index = queue.length - 1;
	}
	
	public Queue() {
		this(DEFAULT_SIZE);
	}
	
	@Override
	public void enqueue(T newEntry) {
		if(isFull()) {
			extend_queue();
		}
		bottom_index = (bottom_index + 1) % queue.length;
		queue[bottom_index] = newEntry;
	}

	@Override
	public T dequeue() {
		if(!isEmpty()) {
			T entry = queue[top_index % queue.length];
			queue[top_index % queue.length] = null;
			top_index = (top_index + 1) % queue.length;
			return entry;
		}else {
			throw new IndexOutOfBoundsException("Couldn't dequeue due to empty queue !");
		}
	}

	@Override
	public T get_Front() {
		if(!isEmpty()) {
			return queue[top_index];
		}else {
			throw new IndexOutOfBoundsException("Couldn't get the front item due to empty queue !");
		}
	}

	@Override
	public boolean isEmpty() {
		return (top_index == (bottom_index + 1) % queue.length);
	}

	@Override
	public void Clear() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[queue.length];
		queue = temp;
		top_index = 0 ; bottom_index = queue.length - 1;
	}
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[queue.length - 1];
		
		for(int index = top_index; index < queue.length - 1; index++) {
			temp[index] = queue[index % queue.length];
		}
		
		return temp;
	}
	
	private void extend_queue() {
			if(queue.length != MAX_SIZE) {
				
			T[] items = toArray();
			
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[items.length + 11];
			
			for(int index = 0; index < items.length; index++) {
				temp[index] = items[index];
			}
			
			queue = temp;
			top_index = 0 ; bottom_index = (items.length - 1) % queue.length;
		}else {
			throw new IndexOutOfBoundsException("Couldn't extend the queue size due to max capacity allowed !");
		}
	}
	
	private boolean isFull() {
		return (top_index == (bottom_index+2) % queue.length);
	}

}
