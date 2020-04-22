package patterns.delegation.office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.BinaryOperator;

public class Manager implements Employee {
	
	//Different operations, writing it as a lambda expression
	
	private static BinaryOperator<Double> gange = (x,y) -> x*y;
	private static BinaryOperator<Double> dele = (x,y) -> x/y;
	private static BinaryOperator<Double> pluss = (x,y) -> x+y;
	private static BinaryOperator<Double> minus = (x,y) -> x-y;
	
	private Collection<Employee> employees = new ArrayList<>();
	private int taskCount = 0;
	
	public Manager (Collection<Employee> employees) {
		if (employees.size() == 0) {
			throw new IllegalArgumentException("Ingen arbeidere i listen");
		}
		this.employees = employees;
	}

	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		taskCount++;
		Employee clerk = getRandomClerk();
		return clerk.doCalculations(operation, value1, value2);
		
	}

	@Override
	public void printDocument(String document) {
		taskCount++;
		Employee clerk = getRandomClerk();
		clerk.printDocument(document);
	}

	@Override
	public int getTaskCount() {
		return taskCount;
	}

	@Override
	public int getResourceCount() {
		int resourceCount = 1;
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			resourceCount += employee.getResourceCount();
		}
		return resourceCount;
	}
	
	private Employee getRandomClerk() {
		int num = (int) (Math.random() * employees.size());
	    for(Employee t: employees) if (--num < 0) return t;
	    throw new AssertionError();
	}
	
	
	public static void main(String[] args) {
		Printer printer = new Printer();
		
		//Employees
		
		Employee erik = new Clerk(printer);
		Employee markus = new Clerk(printer);
		Employee maria = new Clerk(printer);
		Employee roger = new Clerk(printer);
		Employee oda = new Clerk(printer);
		
		//First manager with his staff + doing calculations
		Collection<Employee> team1 = new ArrayList<Employee>();
		team1.add(erik);
		team1.add(markus);
		Employee manager = new Manager(team1);
		manager.doCalculations(pluss, 2, 6);
		manager.doCalculations(minus, 5, 3);
		manager.printDocument("Yo");
		manager.printDocument("Ingenting");
		System.out.println((double) manager.getTaskCount()/ (double) manager.getResourceCount());
		
		//Med to managers, en over den andre. 
		Collection<Employee> team2 = new ArrayList<Employee>();
		team2.add(maria);
		team2.add(roger);
		team2.add(oda);
		Employee manager1 = new Manager(team2);
		manager1.doCalculations(pluss, 2, 6);
		manager1.doCalculations(minus, 5, 3);
		manager1.printDocument("Yo");
		manager1.printDocument("Ingenting");
		System.out.println((double) manager1.getTaskCount() / (double) manager.getResourceCount());
		
		//Putting another manager over that
		Collection<Employee> everyone = new ArrayList<Employee>();
		everyone.add(manager1);
		everyone.add(manager);
		Employee boss = new Manager(everyone);
		boss.printDocument("ja");
		boss.printDocument("Dette funker");
		boss.doCalculations(dele, 3, 98);
		boss.doCalculations(gange, 456, 1);
		System.out.println((double) boss.getTaskCount() / (double) boss.getResourceCount());
		System.out.println();
		
	}

}
