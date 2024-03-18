package so.memory;

import so.Process;

public class MemoryManager {
    private String[] physicMemory;
    private Strategy strategy;
    private String[] logicMemory;

    public MemoryManager(Strategy strategy) {
        physicMemory = new String[128];
        this.strategy = strategy;
    }

    public void write(Process process) {
        if (this.strategy.equals(Strategy.FIRST_FIT)) {
            this.writeWithFirstFit(process);
        } else if (this.strategy.equals(Strategy.BEST_FIT)) {
            this.writeWithBestFit(process);
        } else if (this.strategy.equals(Strategy.WORST_FIT)) {
            this.writeWithWorstFit(process);
        } else if (this.strategy.equals(Strategy.PAGING)) {
            // Implementação da estratégia de paginação
        }
    }

    private void writeWithFirstFit(Process process) {
        if (process.getSizeInMemory() <= 0) {
            System.out.println("O processo não pode ser inserido na memória pois possui tamanho zero.");
            printMemoryStatus();
            return;
        }

        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");

        int actualSize = 0;
        int start = -1;

        for (int i = 0; i < this.physicMemory.length; i++) {
            if (this.physicMemory[i] == null) {
                if (start == -1) {
                    start = i;
                }
                actualSize++;
            } else {
                if (actualSize >= process.getSizeInMemory()) {
                    break;
                } else {
                    start = -1;
                    actualSize = 0;
                }
            }
        }

        if (actualSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - actualSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            for (int i = start; i < start + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus();
    }

    private void writeWithBestFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");

        int smallestEmptyBlockSize = Integer.MAX_VALUE;
        int startOfSmallestBlock = -1;
        int currentEmptyBlockSize = 0;

        for (int i = 0; i < this.physicMemory.length; i++) {
            if (this.physicMemory[i] == null) {
                currentEmptyBlockSize++;
            } else {
                if (currentEmptyBlockSize > 0 && currentEmptyBlockSize < smallestEmptyBlockSize) {
                    smallestEmptyBlockSize = currentEmptyBlockSize;
                    startOfSmallestBlock = i - currentEmptyBlockSize;
                }
                currentEmptyBlockSize = 0;
            }
        }

        if (smallestEmptyBlockSize == Integer.MAX_VALUE) {
            smallestEmptyBlockSize = 0;
        }

        if (smallestEmptyBlockSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - smallestEmptyBlockSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            for (int i = startOfSmallestBlock; i < startOfSmallestBlock + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus();
    }

    private void writeWithWorstFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");

        int largestEmptyBlockSize = 0;
        int startOfLargestBlock = -1;
        int currentEmptyBlockSize = 0;

        for (int i = 0; i < this.physicMemory.length; i++) {
            if (this.physicMemory[i] == null) {
                currentEmptyBlockSize++;
                if (currentEmptyBlockSize > largestEmptyBlockSize) {
                    largestEmptyBlockSize = currentEmptyBlockSize;
                    startOfLargestBlock = i - currentEmptyBlockSize + 1;
                }
            } else {
                currentEmptyBlockSize = 0;
            }
        }

        if (largestEmptyBlockSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - largestEmptyBlockSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            for (int i = startOfLargestBlock; i < startOfLargestBlock + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus();
    }

    private void printMemoryStatus() {
        System.out.println("Estratégia de alocação que está sendo usada: " + strategy);
        System.out.println("Status da Memória:");

        for (int i = 0; i < physicMemory.length; i++) {
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }

            if (physicMemory[i] != null) {
                System.out.print("[ x ] ");
            } else {
                System.out.print("[ - ] ");
            }
        }

        System.out.println();
    }

    public void delete(Process process) {
        System.out.println("Removendo processo da memória: " + process.getId());

        for (int i = 0; i < physicMemory.length; i++) {
            if (physicMemory[i] != null && physicMemory[i].equals(process.getId())) {
                physicMemory[i] = null;
            }
        }

        System.out.println("Processo removido com sucesso");
        printMemoryStatus();
    }
}
