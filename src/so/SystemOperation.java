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
<<<<<<< HEAD
                mm = new MemoryManager(Strategy.WORST_FIT);
=======
                mm = new MemoryManager(Strategy.BEST_FIT);
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
            }
            if (cm == null) {
                cm = new CpuManager();
            }
            return new Process();
        } else if (type.equals(SystemCallType.WRITE_PROCESS)) {
<<<<<<< HEAD
            if (mm == null) {
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            mm.write(p);
            return p;
        } else if (type.equals(SystemCallType.CLOSE_PROCESS)) {
            if (mm == null) {
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            mm.delete(p);
            return p;
        }
        return null;
    }

=======
            mm.write(p);
            return p; // Retorna o próprio processo que foi passado como argumento
        } else if (type.equals(SystemCallType.CLOSE_PROCESS)) {
            mm.delete(p);
            return p; // Retorna o próprio processo que foi passado como argumento
        }
        return null;
    }
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
}
