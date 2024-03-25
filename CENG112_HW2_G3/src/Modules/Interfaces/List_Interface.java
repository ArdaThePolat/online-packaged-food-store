package Modules.Interfaces;

public interface List_Interface<T> {

	public void add(T newEntry);
	
	public void add(T new_Entry, int position);
	
	public T remove(int position);
	
	public T remove(T entry);
	
	public T get_Entry(int position);
	
	public int get_Length();
	
	public boolean contains(T entry);
	
	public T replace(T newEntry, int position);
	
	public boolean is_Empty();
	
	public boolean is_Full();
	
	public void Clear();
	
	public T[] to_Array();
	
}
