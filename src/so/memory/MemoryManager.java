package so.memory;

import so.Process;

<<<<<<< HEAD
// Classe responsável por gerenciar a memória do sistema operacional
public class MemoryManager {
    private String[] physicMemory; // Array para representar a memória física
    private Strategy strategy; // Estratégia de alocação de memória

    // Array para quem for usar paginação
    private String[] logicMemory; // Array para representar a memória lógica (se aplicável)

    // Construtor da classe MemoryManager
    public MemoryManager(Strategy strategy) {
        physicMemory = new String[128]; // Inicialização do array de memória física com 128 posições
        this.strategy = strategy; // Atribuição da estratégia de alocação de memória
=======
public class MemoryManager {
    private String[] physicMemory;
    private Strategy strategy;

    // Quem for usar paginação
    private String[] logicMemory;

    public MemoryManager(Strategy strategy) {
        physicMemory = new String[128];
        this.strategy = strategy;
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
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

<<<<<<< HEAD
 // Método para escrever um processo na memória usando a estratégia First Fit
    private void writeWithFirstFit(Process process) {
        // Verifica se o processo tem tamanho maior que zero
        if (process.getSizeInMemory() <= 0) {
            System.out.println("O processo não pode ser inserido na memória pois possui tamanho zero.");
            printMemoryStatus(); // Imprime o status da memória
            return;
        }

        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");

        int actualSize = 0; // Tamanho atual do espaço vazio contíguo
        int start = -1; // Índice de início do espaço vazio contíguo
        
        // Itera sobre toda a memória física
        for (int i = 0; i < this.physicMemory.length; i++) {
            // Verifica se a posição atual na memória está vazia
            if (this.physicMemory[i] == null) {
                if (start == -1) {
                    start = i; // Atualiza o início do espaço vazio contíguo
                }
                actualSize++; // Incrementa o tamanho do espaço vazio contíguo
                
                // Se o espaço atual for suficiente para o processo, insira-o e interrompa a busca
                if (actualSize >= process.getSizeInMemory()) {
                    System.out.println("Processo inserido com sucesso");
                    // Escreve o processo na memória
                    for (int j = start; j < start + process.getSizeInMemory(); j++) {
                        this.physicMemory[j] = process.getId();
                    }
                    printMemoryStatus(); // Imprime o status da memória
                    return; // Interrompe a busca após inserir o processo
                }
            } else {
                // Reinicia a contagem do tamanho do espaço vazio contíguo
                start = -1;
                actualSize = 0;
            }
        }

        // Se o loop terminar sem encontrar espaço suficiente, exibe a mensagem de erro
        int remainingSpace = process.getSizeInMemory() - actualSize;
        System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        printMemoryStatus(); // Imprime o status da memória
    }




    // Método para escrever um processo na memória usando a estratégia Best Fit
    private void writeWithBestFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");
        
     // Variável para armazenar o tamanho do menor bloco vazio encontrado na memória.
     // Inicializa com o maior valor possível para garantir que qualquer tamanho de bloco vazio
     // encontrado na memória será menor do que esse valor na primeira iteração.
        int smallestEmptyBlockSize = Integer.MAX_VALUE;
     // Variável para armazenar o índice de início do menor bloco vazio encontrado na memória.
     // É atualizada quando um novo menor bloco é encontrado durante a iteração.
        int startOfSmallestBlock = -1;
     // Variável para calcular o tamanho do bloco vazio atualmente em análise durante a iteração.
     // É reiniciada sempre que um bloco ocupado é encontrado na memória.
        int currentEmptyBlockSize = 0;
     // Indicador booleano que indica se foi encontrado um espaço na memória grande o suficiente
     // para alocar o processo. Se nenhum espaço for encontrado, permanece como false.
        boolean foundSpace = false;

        // Percorre toda a memória física em busca do menor bloco vazio
        for (int i = 0; i < this.physicMemory.length; i++) {
            if (this.physicMemory[i] == null) {
                currentEmptyBlockSize++;
            } else {
                // Verifica se o bloco vazio encontrado é suficiente e menor que o menor bloco até agora
                if (currentEmptyBlockSize >= process.getSizeInMemory() && currentEmptyBlockSize < smallestEmptyBlockSize) {
                    smallestEmptyBlockSize = currentEmptyBlockSize;
                    startOfSmallestBlock = i - currentEmptyBlockSize; // Atualiza o início do menor bloco vazio
                    foundSpace = true;
                }
                currentEmptyBlockSize = 0; // Reinicia o tamanho do bloco vazio atual
            }
        }

        // Adição de verificação para lidar com o último bloco vazio
        if (currentEmptyBlockSize >= process.getSizeInMemory() && currentEmptyBlockSize < smallestEmptyBlockSize) {
            smallestEmptyBlockSize = currentEmptyBlockSize;
            startOfSmallestBlock = this.physicMemory.length - currentEmptyBlockSize; // Atualiza o início do menor bloco vazio
            foundSpace = true;
        }

        if (!foundSpace) {
            smallestEmptyBlockSize = 0; // Define o menor bloco como 0 se nenhum espaço for encontrado
        }

        // Verifica se o menor bloco vazio encontrado é suficiente para o processo
        if (smallestEmptyBlockSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - smallestEmptyBlockSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
            // Escreve o processo na memória
            for (int i = startOfSmallestBlock; i < startOfSmallestBlock + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus(); // Imprime o status da memória
    }

    // Método para escrever um processo na memória usando a estratégia Worst Fit
    private void writeWithWorstFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");
        
     // Variável para armazenar o tamanho do maior bloco vazio encontrado na memória.
     // Inicializa com 0 para garantir que qualquer bloco vazio encontrado na memória será maior do que esse valor
     // na primeira iteração.
     int largestEmptyBlockSize = 0;

     // Variável para armazenar o índice de início do maior bloco vazio encontrado na memória.
     // É atualizada quando um novo maior bloco é encontrado durante a iteração.
     int startOfLargestBlock = -1;

     // Variável para calcular o tamanho do bloco vazio atualmente em análise durante a iteração.
     // É reiniciada sempre que um bloco ocupado é encontrado na memória.
     int currentEmptyBlockSize = 0;
=======
    private void writeWithWorstFit(Process process) {
        System.out.println("Escrevendo processo na memória: " + process.getId() + " (Tamanho: " + process.getSizeInMemory() + ")");
        
        int largestEmptyBlockSize = 0;
        int startOfLargestBlock = -1;
        int currentEmptyBlockSize = 0;
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279

        // Percorre toda a memória física em busca do maior bloco vazio
        for (int i = 0; i < this.physicMemory.length; i++) {
            if (this.physicMemory[i] == null) {
                currentEmptyBlockSize++;
<<<<<<< HEAD
                // Atualiza o tamanho e o início do maior bloco vazio, se necessário
=======
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
                if (currentEmptyBlockSize > largestEmptyBlockSize) {
                    largestEmptyBlockSize = currentEmptyBlockSize;
                    startOfLargestBlock = i - currentEmptyBlockSize + 1;
                }
            } else {
<<<<<<< HEAD
                currentEmptyBlockSize = 0; // Reinicia o tamanho do bloco vazio atual
=======
                currentEmptyBlockSize = 0;
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
            }
        }

        // Verifica se o maior bloco vazio encontrado é suficiente para o processo
        if (largestEmptyBlockSize < process.getSizeInMemory()) {
            int remainingSpace = process.getSizeInMemory() - largestEmptyBlockSize;
            System.out.println("Não há espaço suficiente na memória. Faltaram " + remainingSpace + " unidades.");
        } else {
            System.out.println("Processo inserido com sucesso");
<<<<<<< HEAD
            // Escreve o processo na memória
=======
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
            for (int i = startOfLargestBlock; i < startOfLargestBlock + process.getSizeInMemory(); i++) {
                this.physicMemory[i] = process.getId();
            }
        }
        printMemoryStatus(); // Imprime o status da memória
    }

<<<<<<< HEAD
    // Método para imprimir o status da memória
=======
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
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
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

<<<<<<< HEAD
    // Método para remover um processo da memória
    public void delete(Process process) {
        System.out.println("Removendo processo da memória: " + process.getId());
        
        for (int i = 0; i < physicMemory.length; i++) {
        	if(physicMemory[i] != null && physicMemory[i].equals(process.getId() )) {
        		physicMemory[i] = null; // marca posição como vazia
        	}
        }
        System.out.println("Processo removido com sucesso");
        printMemoryStatus();
=======

    public void delete(Process process) {
        // Implementação da remoção de um processo da memória
>>>>>>> 87f7cc76362f2592b6e43b7bb200f2d0fc18a279
    }
}
