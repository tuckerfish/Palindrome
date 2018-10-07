public class MyStack implements StackInterface {

	// new linked list to be manipulated as a stack
	ListInterface list = new MyLinkedList();

	// returns true if the stack is empty.
	public boolean isEmpty(){
		return list.isEmpty();
	}

	// adds an object to the beginning of the stack.
	public void push(Object o){
		list.add(0,o);
	}

	// returns and removes an object from the beginning of the stack.
	public Object pop(){
		if(isEmpty()){
			throw new StackException("Tried to pop() an empty stack.");
		}
		Object temp = list.get(0);
		list.remove(0);
		return temp;
	}
	
	// returns an object from the beginning of the stack.
	public Object peek(){
		if(isEmpty()){
			throw new StackException("Tried to peek() an empty stack.");
		}
		return list.get(0);
	}

	// removes all objects from the stack.
	public void popAll(){
		list.removeAll();
	}

	// returns a string of the contents of the stack, punctuated by paranthesis and commas.
	public String toString(){
		return list.toString();
	}

	// default constructor
	public MyStack(){
		list = new MyLinkedList();
	}

	// copy constructor
	public MyStack(MyStack s){
		list = new MyLinkedList();
		for(int i = 0; i < s.list.size(); i ++)
			list.add(i, s.list.get(i));
	}
}