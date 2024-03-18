package so;

import so.cpu.CpuManager;
import so.memory.MemoryManager;
import so.memory.Strategy;
import so.scheduler.Scheduler;

public class SystemOperation {

    private static CpuManager cm;
    private static MemoryManager mm;
    private static Scheduler scheduler;

    public static Process systemCall(SystemCallType type, Process p) {
        if (type.equals(SystemCallType.CREATE_PROCESS)) {
            if (mm == null) {
                // Inicializa o MemoryManager com a estratégia WORST_FIT por padrão
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            if (cm == null) {
                cm = new CpuManager();
            }
            // Cria um novo processo
            return new Process();
        } else if (type.equals(SystemCallType.WRITE_PROCESS)) {
            if (mm == null) {
                // Se o MemoryManager não foi inicializado anteriormente, inicializa-o com a estratégia WORST_FIT
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            // Escreve o processo na memória
            mm.write(p);
            return p; // Retorna o próprio processo que foi passado como argumento
        } else if (type.equals(SystemCallType.CLOSE_PROCESS)) {
            if (mm == null) {
                // Se o MemoryManager não foi inicializado anteriormente, inicializa-o com a estratégia WORST_FIT
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            // Remove o processo da memória
            mm.delete(p);
            return p; // Retorna o próprio processo que foi passado como argumento
        }
        return null;
    }
}
