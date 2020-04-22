package patterns.delegation.office;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
	
	
	private Map<Employee, List<String>> dictionary = new HashMap<Employee, List<String>>();
	

	public void printDocument(String document, Employee employee) {
		System.out.println(document);
		List<String> tmp = new ArrayList<>();
		tmp.add(document);
		List<String> resultOfAdding = dictionary.putIfAbsent(employee, tmp);
		if (!(resultOfAdding == null)) {
			resultOfAdding.add(document);
			dictionary.remove(employee);
			dictionary.put(employee, resultOfAdding);
		}
		
	}
	
	public List<String> getPrintHistory(Employee employee){
		if (dictionary.get(employee) == null) {
			List<String> hei = Arrays.asList();
			return hei;
		}
		return dictionary.get(employee);
		
	}
	
	public static void main(String[] args) {
//		Printer printer = new Printer();
//		Clerk clerk = new Clerk(printer, "Erik");
//		clerk.printDocument("Hei");
//		clerk.printDocument("Hvordan går det?");
//		Clerk clerk1 = new Clerk(printer, "Marie");
//		clerk1.printDocument("Document1");
//		clerk1.printDocument("document2");
//		
//		System.out.println(printer.getPrintHistory(clerk));
		
		
		
		
		
	}
	
	
}


