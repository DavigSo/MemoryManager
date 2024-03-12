<<<<<<< HEAD
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
=======
package so;

public class Execute {

	public static void main(String[] args) {
		Process p1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1);
		
		Process p2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		Process p3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3);
		
		Process p4 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p4);
		
		Process p5 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p5);
		
		Process p6 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p6);
		
		Process p7 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p7);
		
		Process p8 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p8);
		
		Process p9 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p9);
		
		Process p10 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p10);
		
	}
}	
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
