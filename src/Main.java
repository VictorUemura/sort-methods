import java.io.File;
import java.io.IOException;

public class Main {
    public static int reg;
    private Arquivos sorted, reversed, random, auxrev, auxrand, table;
    private int tini, tend, mov, com;

    private void initFiles() {
        this.sorted = new Arquivos("Ordenado.dat");
        this.reversed = new Arquivos("Reverso.dat");
        this.random = new Arquivos("Aleatorio.dat");
        this.table = new Arquivos("Tabela.txt");
        this.auxrand = new Arquivos();
        this.auxrev = new Arquivos();
    }

    private void writeHeader() throws IOException {
        table.getFile().writeBytes("Métodos de ordenação\n");

        table.getFile().writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        table.getFile().writeBytes(String.format("%-25s%-80s%-80s%-80s%n", "| Algoritmos", "| Arquivo Ordenado", "| Arquivo Reverso", "| Arquivo Randomico                                                                 |"));
        table.getFile().writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        table.getFile().writeBytes("|                        |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Tempo    |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Tempo    |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Tempo    |\n");
        table.getFile().writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

    }

    private void writeTable(String name, int cp, double ce, int mp, double me, double time) throws IOException {
        if (name != "")
            table.getFile().writeBytes(String.format("| %-22s |  %-13s |  %-13s |  %-12s |  %-13s |   %-8s ", name, (int) cp, (int) ce, (int) mp, (int) me, (int) time));
        else
            table.getFile().writeBytes(String.format("|  %-13s |  %-13s |  %-12s |  %-13s |   %-8s ", (int) cp, (int) ce, (int) mp, (int) me, (int) time));
    }

    private void BubbleSort() throws IOException {

        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BubbleSort();


        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Bubble Sort", com, (Math.pow(reg, 2) - reg) / 2, mov, 0, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();


        tini = (int) System.currentTimeMillis();
        auxrev.BubbleSort();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, Math.pow(reg, 2) / (4 + 3 * (reg - 1)), tend - tini);


        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.BubbleSort();


        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();


        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f), tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void ShakeSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.ShakeSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Shake Sort", com, (Math.pow(reg, 2) - reg) / 2, mov, 0, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.ShakeSort();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();


        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, 3 * (Math.pow(reg, 2) - reg) / 4, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.ShakeSort();


        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();


        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, 3 * (Math.pow(reg, 2) - reg) / 2, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void ShellSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.ShellSort();


        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();


        mov = sorted.getMov();
        writeTable("Shell Sort", com, -1, mov, -1, tend - tini);


        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.ShellSort();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.ShellSort();


        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void HeapSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.HeapSort();


        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Heap Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.HeapSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();


        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.HeapSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();


        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void QuickWithoutPivet() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Quick Sort 1", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();


        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void QuickWithPivet() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();


        mov = sorted.getMov();
        writeTable("Quick Sort 2", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();


        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);


        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();


        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f), tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void MergeSort1() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.Merge1();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Merge 1", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.Merge1();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.Merge1();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();


        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void MergeSort2() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.Merge2();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();


        writeTable("Merge 2", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.Merge2();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);


        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();


        tini = (int) System.currentTimeMillis();
        auxrand.Merge2();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void CountingSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.CountingSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();


        mov = sorted.getMov();
        writeTable("CountingSort", com, -1, mov, -1, tend - tini);


        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.CountingSort();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.CountingSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();


        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void BucketSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Gnome Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();


        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void RadixSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.RadixSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();


        mov = sorted.getMov();
        writeTable("Radix Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.RadixSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.RadixSort();


        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void CombSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.CombSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Comb Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();


        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.CombSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.CombSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();


        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void GnomeSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Gnome Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();


        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void TimSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.TimSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Tim Sort", com, -1, mov, -1, tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.TimSort();


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.TimSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }


    private void InsertionSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();


        mov = sorted.getMov();
        writeTable("Insertion Sort", com, reg - 1, mov, 3 * (reg - 1), tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) + reg - 4) / 4, mov, (Math.pow(reg, 2) + (3 * reg) - 4) / 2, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();


        tini = (int) System.currentTimeMillis();
        auxrand.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) + reg - 2) / 4, mov, (Math.pow(reg, 2) + (9 * reg) - 10) / 4, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void BinaryInsertion() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BinaryInsert(0, -1);
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Binary Insertion", com, 0, mov, 3 * (reg - 1), tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.BinaryInsert(0, -1);


        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, (Math.pow(reg, 2) + 3 * reg - 4) / 2, tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();


        tini = (int) System.currentTimeMillis();
        auxrand.BinaryInsert(0, -1);
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, reg * (Math.log(reg)), mov, (Math.pow(reg, 2) + 9 * reg - 10) / 4, tend - tini);
        table.getFile().writeBytes("\n");
    }

    private void SelectionSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("Select Sort", com, (Math.pow(reg, 2) - reg) / 2, mov, 3 * (reg - 1), tend - tini);

        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, Math.pow(reg, 2) / 4 + (3 * (reg - 1)), tend - tini);

        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f), tend - tini);
        table.getFile().writeBytes("\n");
    }

    public void deleteBack() {
        File temp;
        new File("auxMerge.dat").delete();
        new File("Ordenado.dat").delete();
        new File("Aleatorio.dat").delete();
        new File("Reverso.dat").delete();
        new File("temp.dat").delete();
        new File("Tabela.txt").delete();
    }

    public void criarTabela() {
        try {
            long timeInicio = System.currentTimeMillis();
            deleteBack();
            initFiles();
            writeHeader();

            System.out.println("Criando Arquivos");

            sorted.doSorted();
            reversed.doReversed();
            random.doRandom();

            System.out.println("Ordenando, aguarde...");

            InsertionSort();
            BinaryInsertion();
            SelectionSort();
            BubbleSort();
            ShakeSort();
            ShellSort();
            HeapSort();
            QuickWithoutPivet();
            QuickWithPivet();
            MergeSort1();
            MergeSort2();
            CountingSort();
            BucketSort();
            RadixSort();
            CombSort();
            GnomeSort();
            TimSort();

            long timeFinal = System.currentTimeMillis();
            table.getFile().writeBytes("Tempo total: " + (timeFinal - timeInicio) / 1000 + " segundos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NodeList lista = new NodeList();
        lista.gerarLista(10, 50, 100);
        System.out.println("Lista gerada:");
        lista.exibeLista();

//      tirar o comentário da lista para testar ordenacao
        lista.bucketSort();
//        lista.timSort();
//        lista.binaryInsertionSort();
//        lista.bubbleSort();
//        lista.shakeSort();
//        lista.selectionSort();
//        lista.shellSort();
//        lista.combSort();
//        lista.radixSort();
//        lista.gnomeSort();
//        lista.countingSort();
//        lista.mergePrimeiraImplementacao();
//        lista.mergeSegundaImplementacao();
//        lista.heapSort();
//        lista.quickSortCP();
//        lista.quickSortSP();

        System.out.println("Lista ordenada:");
        lista.exibeLista();

        Main main = new Main();
        reg = 1024;
        main.criarTabela();
    }
}