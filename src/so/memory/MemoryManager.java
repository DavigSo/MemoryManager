package so.memory;

import so.Process;

public class MemoryManager {
    private String[] physicMemory;
    private Strategy strategy;

    // Quem for usar paginação
    private String[] logicMemory;

    public MemoryManager(Strategy strategy) {
        physicMemory = new String[128];
        this.strategy = strategy;
    }

    // Método para escrever um processo na memória com base na estratégia selecionada
    public void write(Process process) {
        // Verifica a estratégia selecionada e chama o método correspondente
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

    private void writeWithWorstFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");
        
        int largestEmptyBlockSize = 0;
        int startOfLargestBlock = -1;
        int currentEmptyBlockSize = 0;

        // Percorre toda a memória física em busca do maior bloco vazio
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

        // Verifica se o maior bloco vazio encontrado é suficiente para o processo
        if (largestEmptyBlockSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - largestEmptyBlockSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            for (int i = startOfLargestBlock; i < startOfLargestBlock + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus(); // Imprime o status da memória
    }

    private void writeWithBestFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");
        
        int smallestEmptyBlockSize = Integer.MAX_VALUE;
        int startOfSmallestBlock = -1;
        int currentEmptyBlockSize = 0;

        // Percorre toda a memória física em busca do menor bloco vazio
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

        // Verifica se o menor bloco vazio encontrado é suficiente para o processo
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
        printMemoryStatus(); // Imprime o status da memória
    }

    private void writeWithFirstFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");

        int actualSize = 0;
        int start = -1;
        
        // Itera sobre toda a memória física
        for (int i = 0; i < this.physicMemory.length; i++) {
            // Verifica se a posição atual na memória está vazia
            if (this.physicMemory[i] == null) {
                if (start == -1) {
                    start = i;
                }
                actualSize++; // Incrementa o tamanho do espaço vazio contíguo
            } else {
                if (actualSize >= process.getSizeInMemory()) {
                    break; // Interrompe a busca se encontrar um espaço adequado
                } else {
                    start = -1; // Reinicia o início do espaço contíguo
                    actualSize = 0;
                }
            }
        }

        // Verifica se o espaço encontrado é suficiente para o processo
        if (actualSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - actualSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            // Escreve o processo na memória
            for (int i = start; i < start + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus(); // Imprime o status da memória
    }

 // Método para imprimir o status da memória
    private void printMemoryStatus() {
        System.out.println("Estratégia de alocação que está sendo usada: " + strategy);
        System.out.println("Status da Memória:");
        
        // Itera sobre todas as posições da memória física
        for (int i = 0; i < physicMemory.length; i++) {
            // Quebra de linha a cada 10 elementos para melhorar a legibilidade
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
            
            // Verifica se a posição atual está ocupada ou livre
            if (physicMemory[i] != null) {
                System.out.print("[ x ] "); // Representa uma posição ocupada na memória
            } else {
                System.out.print("[ - ] "); // Representa uma posição livre na memória
            }
        }
        
        System.out.println(); // Adiciona uma linha em branco no final para melhorar a legibilidade
    }


    public void delete(Process process) {
        // Implementação da remoção de um processo da memória
    }
}
