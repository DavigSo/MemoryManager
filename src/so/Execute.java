package so;

public class Execute {

	public static void main(String[] args) {

		System.out.println("Começando testes...");
		Process p1 = new Process();
		p1.setSizeInMemory(20);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1);

		Process p2 = new Process();
		p2.setSizeInMemory(38);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2);

		Process p3 = new Process();
		p3.setSizeInMemory(38);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3);

		Process p4 = new Process();
		p4.setSizeInMemory(20);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p4);
		

		SystemOperation.systemCall(SystemCallType.CLOSE_PROCESS, p2);
		
		Process p5 = new Process();
		p5.setSizeInMemory(8);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p5);
	}

}	

