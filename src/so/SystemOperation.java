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
                mm = new MemoryManager(Strategy.WORST_FIT);
            }
            if (cm == null) {
                cm = new CpuManager();
            }
            return new Process();
        } else if (type.equals(SystemCallType.WRITE_PROCESS)) {
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

}
