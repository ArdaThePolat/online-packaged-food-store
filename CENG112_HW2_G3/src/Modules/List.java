package Modules;

import Modules.Interfaces.List_Interface;

public class List<T> implements List_Interface<T>{

	private final static int DEFAULT_SIZE = 30;
	private final static int MAX_SIZE = 1000;
	private T[] list;
	private int current_size;
	
	@SuppressWarnings("unchecked")
	public List(int size) {
		T[] temp;
		if(!(size > MAX_SIZE) && !(size <= 0)) {
			temp = (T[]) new Object[size];
		}else {
			System.out.println("Invalid size value! List initialized with default size (30)");
			temp = (T[]) new Object[DEFAULT_SIZE];
		}
		list = temp;
		current_size = 0;
	}
	
	public List() {
		this(DEFAULT_SIZE);
	}
	
	private void extend_list() {
		int new_size = list.length + 10;
		if(new_size > MAX_SIZE) new_size = MAX_SIZE;
		
		T[] items = to_Array();
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[new_size];
		
		for(int i = 0; i < items.length; i++) {
			temp[i] = items[i];
		}
		
		list = temp;
		current_size = items.length;
	}
	
	@Override
	public void add(T newEntry) {
		if(is_Full()) extend_list();
		list[current_size] = newEntry;
		current_size++;
	}

	@Override
	public void add(T new_Entry, int position) {
		if(position < 0 || position > list.length-1)
			throw new IndexOutOfBoundsException("Position parameter is invalid !");

		if(is_Full()) extend_list();
		T[] temp = to_Array();

		for(int i = 0; i < temp.length; i++) {
			if(i < position) {
				list[i] = temp[i];
			}else if(i == position) {
				list[i] = new_Entry;
			}else {
				list[i] = temp[i-1];
			}
		}
		current_size++;
	}

	@Override
	public T remove(int position) {
		position--;
		if(position < 0 || position > list.length-1)
			throw new IndexOutOfBoundsException("Position parameter is invalid !");
		
		if(!is_Empty()) {
			T entry = list[position];
			list[position]=null;


			return entry;
		}else {
			throw new ArrayIndexOutOfBoundsException("Array is empty, couldn't remove any item !");
		}
	}

	@Override
	public T remove(T entry) {
		int item_index = find(entry);
		return remove(item_index);
	}

	@Override
	public T get_Entry(int position) {
		if(position < 0 || position > list.length-1)
			throw new IndexOutOfBoundsException("Position parameter is invalid !");
		
		return list[position];
	}

	@Override
	public int get_Length() {
		return list.length;
	}

	@Override
	public boolean contains(T entry) {
		return find(entry) != -1;
	}

	@Override
	public T replace(T newEntry, int position) {
		if(position < 0 || position > list.length)
			throw new IndexOutOfBoundsException("Position parameter is invalid !");
		
		T old_entry = list[position];
		list[position] = newEntry;
		return old_entry;
	}

	@Override
	public boolean is_Empty() {
		int itemNumber = 0;
		for(int i = 0; i < list.length; i++) {
			if(!list[i].equals(null)) {
				itemNumber++;
			}
		}
		return itemNumber == 0;
	}
	
	@Override
	public boolean is_Full() {
		return current_size == list.length;
	}

	@Override
	public void Clear() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[list.length];
		list = temp;
		current_size = 0;
		
	}

	@Override
	public T[] to_Array() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[list.length];
		
		for(int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		return temp;
	}
	
	private int find(T entry) {
		for(int i = 0; i < list.length; i++) {
			if(list[i].equals(entry)) {
				return i;
			}
		}
		return -1;
	}

}
