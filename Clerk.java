package patterns.delegation.office;

import java.util.function.BinaryOperator;



public class Clerk implements Employee{
	
	private Printer printer;
	private int taskCount = 0;
//	private String name;
	
	
	public Clerk(Printer printer) {
		this.printer = printer;
//		this.name = name;
	}
	
	

//	@Override
//	public String toString() {
//		return name;
//	}



	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		taskCount++;
		return operation.apply(value1, value2);
	}

	@Override
	public void printDocument(String document) {
		taskCount++;
		this.printer.printDocument(document, this);
		
	}
	

	@Override
	public int getTaskCount() {
		return taskCount;
	}

	@Override
	public int getResourceCount() {
		return 1;
	}

	public static void main(String[] args) {
//		Clerk clerk = new Clerk();
		
	}

}
