package Modules;

import Modules.Interfaces.Stack_Interface;

public class Stack<T> implements Stack_Interface<T>{

	private final int DEFAULT_SIZE = 30;
	private T[] stack;
	private int current_size;
	
	public Stack() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[DEFAULT_SIZE];
		stack = temp;
		current_size = 0;
	}
	
	@Override
	public void push(T newEntry){
		if(!isFull()) {
			stack[current_size] = newEntry;
			current_size++;
		}else {
			throw new StackOverflowError("Error! Couldn't add the entry due to full stack !");
		}
	}

	@Override
	public T pop() {
		if(!isEmpty()) {
			T entry = stack[current_size - 1];
			stack[current_size - 1] = null;
			current_size--;
			return entry;
		}else {
			throw new StackOverflowError("Error! Pop operation couldn't work due to empty stack !");
		}
	}

	@Override
	public T peek() {
		if(!isEmpty()) {
			T entry = stack[current_size - 1];
			return entry;
		}else {
			throw new StackOverflowError("Error! Peek operation couldn't work due to empty stack !");
		}
	}

	@Override
	public boolean isEmpty() {
		return current_size == 0;
	}

	@Override
	public void Clear() {
		for(int i = 0; i < current_size; i++) {
			stack[i] = null;
		}
		current_size = 0;
		
	}
	
	@Override
	public boolean isFull() {
		return current_size == DEFAULT_SIZE;
	}

}
