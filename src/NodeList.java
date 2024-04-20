import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NodeList {
    Node inicio, fim;
    int tl;

    public NodeList() {
        inicio = null;
        fim = null;
        tl = 0;
    }

    public Node getInicio() {
        return inicio;
    }

    public void setInicio(Node inicio) {
        this.inicio = inicio;
    }

    public Node getFim() {
        return fim;
    }

    public void setFim(Node fim) {
        this.fim = fim;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public void insereFim(int value) {
        Node newNode = new Node(fim, null, value);
        if (inicio == null)
            inicio = fim = newNode;
        else {
            fim.setProx(newNode);
            fim = newNode;
        }
        tl++;
    }

    public void insereInicio(int value) {
        Node newNode = new Node(null, inicio, value);
        if (inicio == null)
            inicio = fim = newNode;
        else {
            inicio.setAnt(newNode);
            inicio = newNode;
        }
        tl++;
    }

    public void clearAll() {
        inicio = fim = null;
        tl = 0;
    }

    public void gerarLista(int qtde) {
        int valor;
        clearAll();
        for (int i = 0; i < qtde; i++) {
            valor = Utils.randomInt();
            while (buscaExaustiva(valor) != null)
                valor = Utils.randomInt();
            insereFim(valor);
        }
        tl = qtde;
    }

    public void gerarLista(int qtde, int max) {
        int valor;
        clearAll();
        for (int i = 0; i < qtde; i++) {
            valor = Utils.randomInt(max);
            while (buscaExaustiva(valor) != null)
                valor = Utils.randomInt(max);
            insereFim(valor);
        }
        tl = qtde;
    }

    public void gerarLista(int qtde, int min, int max) {
        int valor;
        clearAll();
        for (int i = 0; i < qtde; i++) {
            valor = Utils.randomInt(min, max);
            while (buscaExaustiva(valor) != null)
                valor = Utils.randomInt(min, max);
            insereFim(valor);
        }
        tl = qtde;
    }

    public Node buscaExaustiva(int value) {
        Node aux = inicio;
        while (aux != null && aux.getInfo() != value)
            aux = aux.getProx();
        return aux;
    }

    public void exibeLista() {
        Node nodeAux = inicio;
        if (nodeAux == null)
            System.out.println("Lista vazia");
        while (nodeAux != null) {
            System.out.println("." + nodeAux.getInfo());
            nodeAux = nodeAux.getProx();
        }
    }

    // as posicoes sao contadas a partir de zero
    public Node index(int i) {
        int j = 0;
        Node search = inicio;
        if (i > tl)
            return null;
        while (j < i) {
            search = search.getProx();
            j++;
        }
        return search;
    }

    // metodos de ordenacao a seguir

    // insertion sort utilizando a troca de nos
    public void InsertionSort() {
        Node i = this.inicio;
        Node pos;
        int aux;

        while (i != null) {
            aux = i.getInfo();
            pos = i;
            while (pos != this.inicio && aux < pos.getAnt().getInfo()) {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }

    // binaryInsertionSort utilizando troca de nos
    public void binaryInsertionSort() {
        int i = 1, inicio, fim, meio;
        while (i < tl) {
            inicio = 0;
            fim = i - 1;
            // 1 -> busca posicao
            while (inicio <= fim) {
                meio = (inicio + fim) / 2;
                if (index(i).getInfo() < index(meio).getInfo())
                    fim = meio - 1;
                else
                    inicio = meio + 1;
            }
            // 2 -> remove o elemento da lista
            if (inicio != i) {
                Node noAtual = index(i);
                if (noAtual == this.fim) {
                    this.fim = this.fim.getAnt();
                    this.fim.setProx(null);
                } else {
                    noAtual.getProx().setAnt(noAtual.getAnt());
                    noAtual.getAnt().setProx(noAtual.getProx());
                    noAtual.setProx(null);
                    noAtual.setAnt(null);
                }
                // 3 -> insere na posicao indicada
                Node aux = index(inicio);
                if (aux != null) {
                    if (aux == this.inicio) {
                        aux.setAnt(noAtual);
                        noAtual.setProx(aux);
                        this.inicio = noAtual;
                    } else {
                        aux.getAnt().setProx(noAtual);
                        noAtual.setAnt(aux.getAnt());
                        aux.setAnt(noAtual);
                        noAtual.setProx(aux);
                    }
                }
            }
            i++;
        }
    }

    // selectionSort realizando a troca de valores
    public void selectionSort() {
        Node nodeI, nodeAux, nodeAtual = inicio;
        int aux;
        if (tl > 1) {
            while (nodeAtual != fim) {
                nodeI = nodeAtual;
                nodeAux = nodeAtual.getProx();
                while (nodeAux != null) {
                    if (nodeAux.getInfo() < nodeI.getInfo())
                        nodeI = nodeAux;
                    nodeAux = nodeAux.getProx();
                }
                aux = nodeAtual.getInfo();
                nodeAtual.setInfo(nodeI.getInfo());
                nodeI.setInfo(aux);
                nodeAtual = nodeAtual.getProx();
            }
        }
    }

    // bubblesort realizando a troca de valores
    public void bubbleSort() {
        Node nodeAux, nodeUltimo = null;
        while (inicio.getProx() != nodeUltimo) {
            nodeAux = inicio;
            while (nodeAux.getProx() != nodeUltimo) {
                if (nodeAux.getInfo() > nodeAux.getProx().getInfo()) {
                    int aux = nodeAux.getInfo();
                    nodeAux.setInfo(nodeAux.getProx().getInfo());
                    nodeAux.getProx().setInfo(aux);
                }
                nodeAux = nodeAux.getProx();
            }
            nodeUltimo = nodeAux;
        }
    }

    // shake sort utilizando a troca de elementos
    public void shakeSort() {
        Node nodeAtualEsq = inicio, nodeAtualDir = null, nodeAux;
        boolean notOrd = true;
        while (nodeAtualEsq.getProx() != nodeAtualDir && notOrd) {
            nodeAux = nodeAtualEsq;
            notOrd = false;
            while (nodeAux.getProx() != nodeAtualDir) {
                if (nodeAux.getInfo() > nodeAux.getProx().getInfo()) {
                    int aux = nodeAux.getInfo();
                    nodeAux.setInfo(nodeAux.getProx().getInfo());
                    nodeAux.getProx().setInfo(aux);
                    notOrd = true;
                }
                nodeAux = nodeAux.getProx();
            }
            if (notOrd) {
                notOrd = false;
                nodeAtualDir = nodeAux;
                nodeAux = nodeAux.getAnt();
                while (nodeAux.getAnt() != nodeAtualEsq.getAnt()) {
                    if (nodeAux.getInfo() < nodeAux.getAnt().getInfo()) {
                        int aux = nodeAux.getInfo();
                        nodeAux.setInfo(nodeAux.getAnt().getInfo());
                        nodeAux.getAnt().setInfo(aux);
                        notOrd = true;
                    }
                    nodeAux = nodeAux.getAnt();
                }
                nodeAtualEsq = nodeAtualEsq.getProx();
            }
        }
    }

    // shellsort realizando a troca de valores
    public void shellSort() {
        // calculo da distancia
        double k = Math.log(tl + 1) / Math.log(3);
        k = Math.floor(k + 0.5);
        int h = ((int) Math.pow(3, k) - 1) / 2;
        Node nodeAux, nodeCompare;
        while (h > 0) {
            int i = 0, prox;
            while (i < tl - h) {
                prox = i + h;
                nodeAux = index(i);
                nodeCompare = index(prox);
                if (nodeAux.getInfo() > nodeCompare.getInfo()) {
                    int valueAnt = i;
                    while (valueAnt >= 0) {
                        nodeCompare = index(valueAnt);
                        nodeAux = index(prox);
                        if (nodeCompare.getInfo() > nodeAux.getInfo()) {
                            int valueAux = nodeCompare.getInfo();
                            nodeCompare.setInfo(nodeAux.getInfo());
                            nodeAux.setInfo(valueAux);
                        }
                        prox -= h;
                        valueAnt -= h;
                    }
                }
                i++;
            }
            // formula inversa para a proxima vez que percorrer
            h = (h - 1) / 3;
        }
    }

    public void heapSort() {
        int pai, fe, fd, maior, aux;

        while (tl > 0) {
            pai = tl / 2 - 1;
            while (pai >= 0) {
                fe = 2 * pai + 1;
                fd = 2 * pai + 2;

                if (fd < tl && this.index(fd).getInfo() > this.index(fe).getInfo()) {
                    maior = fd;
                } else {
                    maior = fe;
                }

                if (this.index(maior).getInfo() > this.index(pai).getInfo()) {
                    aux = this.index(pai).getInfo();
                    this.index(pai).setInfo(this.index(maior).getInfo());
                    this.index(maior).setInfo(aux);
                }
                pai--;
            }
            tl--;
            aux = this.index(pai).getInfo();
            this.index(pai).setInfo(this.index(tl).getInfo());
            this.index(tl).setInfo(aux);
        }
    }


    public void particao(int[] vet1, int[] vet2) {
        int i = 0, j;

        while (i < tl / 2) {
            vet1[i] = this.index(i).getInfo();
            i++;
        }
        j = 0;

        while (i < tl) {
            vet2[j++] = this.index(i).getInfo();
            i++;
        }
    }

    public void fusao(int[] vet1, int[] vet2, int seq) {
        int i = 0;
        int j = 0;
        int k = 0;
        int aux_seq = seq;

        while (k < tl) {

            while (i < seq && j < seq) {

                if (vet1[i] < vet2[j]) {
                    this.index(k++).setInfo(vet1[i++]);
                } else {
                    this.index(k++).setInfo(vet2[j++]);
                }
            }

            while (i < seq) {
                this.index(k++).setInfo(vet1[i++]);
            }

            while (j < seq) {
                this.index(k++).setInfo(vet2[j++]);
            }

            seq += aux_seq;
        }
    }

    public void mergeSegundaImplementacao() {
        this.mergeSort(0, tl - 1);
    }

    public void mergePrimeiraImplementacao() {
        int vet1[] = new int[tl / 2];
        int vet2[] = new int[tl / 2];
        int seq = 1;
        while (seq < tl) {
            this.particao(vet1, vet2);
            this.fusao(vet1, vet2, seq);
            seq = seq * 2;
        }
    }

    public void merge(int left, int mid, int right) {
        int len = mid - left + 1;
        int len_aux = right - mid;
        int vet1[] = new int[len];
        int vet2[] = new int[len_aux];

        for (int i = 0; i < len; i++)
            vet1[i] = this.index(left + i).getInfo();

        for (int i = 0; i < len_aux; i++)
            vet2[i] = this.index(mid + 1 + i).getInfo();
        int i = 0;
        int j = 0;
        int k = left;

        while (i < len && j < len_aux) {

            if (vet1[i] < vet2[j])
                this.index(k++).setInfo(vet1[i++]);
            else
                this.index(k++).setInfo(vet2[j++]);
        }

        while (i < len)
            this.index(k++).setInfo(vet1[i++]);
        while (j < len_aux)
            this.index(k++).setInfo(vet2[j++]);
    }

    public void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            this.mergeSort(left, mid);
            this.mergeSort(mid + 1, right);

            this.merge(left, mid, right);
        }
    }

    public void countingSort() {
        int count[] = new int[tl];
        int vet_aux[] = new int[tl];
        Node aux = this.inicio;

        for (int i = 0; i < tl; i++) {
            count[aux.getInfo()]++;
            aux = aux.getProx();
        }

        for (int i = 0; i < tl - 1; i++)
            count[i + 1] += count[i];

        aux = this.fim;

        while (aux != null) {
            vet_aux[--count[aux.getInfo()]] = aux.getInfo();
            aux = aux.getAnt();
        }

        aux = this.inicio;

        for (int i = 0; i < tl; i++) {
            aux.setInfo(vet_aux[i]);
            aux = aux.getProx();
        }
    }

    public void gnomeSort() {
        Node i = this.inicio;
        Node j;
        while (i != this.fim) {


            if (i.getInfo() > i.getProx().getInfo()) {
                j = i.getProx();
                while (j != this.inicio && j.getInfo() < j.getAnt().getInfo()) {
                    int aux = j.getInfo();
                    j.setInfo(j.getAnt().getInfo());
                    j.getAnt().setInfo(aux);
                    j = j.getAnt();
                }
            }
            i = i.getProx();
        }
    }

    public void counting(int exp) {
        int aux[] = new int[tl];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < tl; i++) {
            count[(this.index(i).getInfo() / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = tl - 1; i >= 0; i--) {
            aux[count[(this.index(i).getInfo() / exp) % 10] - 1] = this.index(i).getInfo();
            count[(this.index(i).getInfo() / exp) % 10]--;
        }

        for (i = 0; i < tl; i++) {
            this.index(i).setInfo(aux[i]);
        }
    }

    public void radixSort() {
        int maior = this.index(0).getInfo();

        for (int i = 1; i < tl; i++) {
            if (this.index(i).getInfo() > maior)
                maior = this.index(i).getInfo();
        }

        for (int exp = 1; maior / exp > 0; exp *= 10)
            this.counting(exp);
    }

    public int next(int fator) {
        fator = (int) (fator * 10) / 13;

        if (fator < 1) {
            return 1;
        }
        return fator;
    }

    public void combSort() {
        int n = tl;
        int fator = n;

        boolean swit = true;

        while (fator != 1 || swit == true) {
            fator = next(fator);
            swit = false;

            for (int i = 0; i < n - fator; i++) {

                if (this.index(i).getInfo() > this.index(i + fator).getInfo()) {
                    int aux = this.index(i).getInfo();
                    this.index(i).setInfo(this.index(i + fator).getInfo());
                    this.index(i + fator).setInfo(aux);
                    swit = true;
                }
            }
        }
    }


    public void bucketSort() {
        int maior = tl;
        int divisor = (int) ((maior + 1) * 100) / 10;
        ArrayList<ArrayList<Integer>> lista = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            lista.add(new ArrayList<>());
        }

        for (int i = 0; i < tl; i++) {
            int j = this.index(i).getInfo() / divisor;
            lista.get(j).add(this.index(i).getInfo());
        }

        for (int i = 0; i < 10; i++) {
            Collections.sort(lista.get(i));
        }
        int pos = 0;

        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < lista.get(i).size(); x++) {
                this.index(pos++).setInfo(lista.get(i).get(x));
            }
        }
    }


    public void InserTim(int left, int right) {
        int aux, j;

        for (int i = left + 1; i <= right; i++) {
            aux = this.index(i).getInfo();
            j = i - 1;

            while (j >= left && this.index(j).getInfo() > aux) {
                this.index(j + 1).setInfo(this.index(j--).getInfo());
            }

            this.index(j + 1).setInfo(aux);
        }
    }

    public void timSort() {
        int run = 32;
        int mid;
        int right;

        for (int i = 0; i < tl; i += run) {
            this.InserTim(i, Math.min((i + 31), (tl - 1)));
        }

        for (int i = run; i < tl; i = 2 * i) {

            for (int esq = 0; esq < tl; esq += 2 * i) {
                mid = esq + i - 1;
                right = Math.min((esq + 2 * i - 1), (tl - 1));
                merge(esq, mid, right);
            }
        }
    }

    public void quickSortSP() {
        quickSP(0, tl);
    }

    public void quickSP(int ini, int fim) {
        int i = ini, j = fim, aux;
        boolean flag = true;
        while (i < j) {
            if (flag)
                while (i < j && index(i).getInfo() <= index(j).getInfo())
                    i++;
            else
                while (i < j && index(j).getInfo() >= index(i).getInfo())
                    j--;
            aux = index(j).getInfo();
            index(j).setInfo(index(i).getInfo());
            index(i).setInfo(aux);
            flag = !flag;
        }
        if (ini < i - 1)
            quickSP(ini, i - 1);
        if (j + 1 < fim)
            quickSP(j + 1, fim);
    }

    public void quickSortCP() {
        quickCP(0, tl);
    }

    public void quickCP(int ini, int fim) {
        int i = ini, j = fim, aux;
        int pivo = index((ini + fim) / 2).getInfo();
        while (i < j) {
            while (index(i).getInfo() < pivo)
                i++;
            while (index(j).getInfo() > pivo)
                j--;
            if (i <= j) {
                aux = index(i).getInfo();
                index(i).setInfo(index(j).getInfo());
                index(j).setInfo(aux);
                i++;
                j--;
            }
        }
        if (ini < j)
            quickCP(ini, j);
        if (i < fim)
            quickCP(i, fim);
    }
}