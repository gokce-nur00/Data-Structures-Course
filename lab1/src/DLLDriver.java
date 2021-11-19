
public class DLLDriver {
	public static void main(String[] args) {
		DoublyLinkedList<String> dll = new DoublyLinkedList<>();
		dll.addFirst("G");
		dll.addFirst("D");
		dll.addLast("A");
		dll.addFirst("C");

		System.out.println(dll);
		//get, add, remove, find, sort
		System.out.println("index 2:"+dll.get(2));
		dll.add(2,"O");
		System.out.println("after adding O to second index:\n"+dll);
		System.out.println("after removing:"+dll.remove(1));
		System.out.println(dll);
		System.out.println("after finding A's index:"+dll.find("A"));
		dll.sort();
		System.out.println("after sorting:");
		System.out.println(dll);



		
	}
}
