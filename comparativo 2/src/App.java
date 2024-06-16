import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        TimSort timSort = new TimSort();
        ReadFile file = new ReadFile();
        Scanner scanner = new Scanner(System.in);
        int opt = 0;

        do {
            System.out.println("Escolha 1 para BucketSort e 2 para TimSort e 3 para sair");
            opt = scanner.nextInt();

            if (opt == 1 || opt == 2) {
                // Use caminho absoluto para o arquivo
                String filename = "dados500_mil.txt";
                File fileObj = new File(filename);
                
                if (!fileObj.exists()) {
                    System.out.println("Arquivo não encontrado: " + filename);
                    continue;
                }

                int[] array = file.readArrayFromFile(filename);

                if (array != null) {
                    switch (opt) {
                        case 1:
                            System.out.println("Array antes da ordenação:");
                            bucketSort.printArray(array);

                            long startTimeBucket = System.currentTimeMillis();
                            bucketSort.sort(array);
                            long endTimeBucket = System.currentTimeMillis();

                            System.out.println("Array após a ordenação:");
                            bucketSort.printArray(array);
                            System.out.println("Tempo de execução: " + (endTimeBucket - startTimeBucket) + " milissegundos");
                            System.out.println("Quantidade de comparações: " + bucketSort.getComparisonCount());
                            System.out.println("Quantidade de movimentações: " + bucketSort.getMovementCount());
                            break;
                        case 2:
                            System.out.println("Array antes da ordenação:");
                            timSort.printArray(array);

                            long startTimeTim = System.currentTimeMillis();
                            timSort.sort(array);
                            long endTimeTim = System.currentTimeMillis();

                            System.out.println("Array após a ordenação:");
                            timSort.printArray(array);
                            System.out.println("Tempo de execução: " + (endTimeTim - startTimeTim) + " milissegundos");
                            System.out.println("Quantidade de comparações: " + timSort.getComparisonCount());
                            System.out.println("Quantidade de movimentações: " + timSort.getMovementCount());
                            break;
                    }
                } else {
                    System.out.println("Falha ao ler os dados do arquivo.");
                }
            } else if (opt != 3) {
                System.out.println("Opção inválida");
            }
        } while (opt != 3);

        System.out.println("Saindo");
        scanner.close();
    }
}
