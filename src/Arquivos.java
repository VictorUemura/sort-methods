import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Arquivos {
    private String filename;
    private RandomAccessFile file;
    private int comp;
    private int mov;
    private int cont;

    public Arquivos(String filename) {
        try {
            this.cont = 0;
            this.file = new RandomAccessFile(filename, "rw");
            this.filename = filename;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Arquivos() {
        this.cont = 0;
    }

    public void truncate(long pos) {
        try {
            this.file.setLength(pos * Registro.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFile(RandomAccessFile file) {
        try {
            Registro R = new Registro();
            int i = 0;
            int len = (int) file.length() / Registro.length();
            this.file = new RandomAccessFile("temp.dat", "rw");
            this.truncate(0);
            file.seek(0);
            while(i < len) {
                R.read(file);
                R.write(this.file);
                i++;
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public RandomAccessFile getFile() {
        return this.file;
    }

    public boolean EOF() {
        boolean response = false;
        try {
            if(this.file.getFilePointer() == this.file.length())
                response = true;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public int filesize() {
        try {
            return (int) (this.file.length() / Registro.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void initComp() {
        this.comp = 0;
    }

    public void initMov() {
        this.mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public void show() {
        Registro aux = new Registro();
        this.seek(0);
        while(!this.EOF()) {
            aux.read(this.file);
            aux.show();
        }
    }

    public void seek(int pos) {
        try {
            this.file.seek(pos * Registro.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doSorted() {
        for(int i = 0; i < Main.reg; i++) {
            Registro R = new Registro(i);
            R.write(this.file);
        }
    }

    public void doReversed() {
        for(int i = 0; i < Main.reg; i++) {
            Registro R = new Registro(Main.reg - i - 1);
            R.write(this.file);
        }
    }

    public void doRandom() {
        Random rand = new Random();
        for(int i = 0; i < Main.reg; i++) {
            Registro R = new Registro(rand.nextInt(Main.reg));
            R.write(this.file);
        }
    }

    public void BubbleSort() {
        Registro reg = new Registro();
        Registro aux = new Registro();
        int TL = filesize();

        while(TL > 1) {
            int i = 0;
            while(i < TL - 1) {
                this.seek(i);
                reg.read(this.file);
                aux.read(this.file);
                this.comp++;
                if(aux.getCodigo() < reg.getCodigo()) {
                    this.mov += 2;
                    this.seek(i);
                    aux.write(this.file);
                    reg.write(this.file);
                }
                i++;
            }
            TL--;
        }
    }

    public void ShakeSort() {
        int ini = 0;
        int end = filesize();
        Registro reg = new Registro();
        Registro aux = new Registro();

        while(ini < end) {

            int i;

            for(i = ini; i < end - 1; i++) {
                this.seek(i);
                reg.read(this.file);
                aux.read(this.file);

                this.comp++;
                if(reg.getCodigo() > aux.getCodigo()) {
                    this.mov += 2;
                    this.seek(i);
                    aux.write(this.file);
                    reg.write(this.file);
                }
            }

            for(; i > ini; i--) {
                this.seek(i - 1);
                reg.read(this.file);
                aux.read(this.file);
                this.comp++;
                if(aux.getCodigo() < reg.getCodigo()) {
                    mov += 2;
                    this.seek(i - 1);
                    aux.write(this.file);
                    reg.write(this.file);
                }
            }
            end--;
            ini++;
        }
    }

    public void HeapSort() {
        Registro reg = new Registro();
        Registro aux = new Registro();
        int TL = filesize();
        int pai, fd, fe, maior;

        while(TL > 1) {

            for(pai = TL / 2 - 1; pai >= 0; pai--) {
                fe = pai + pai + 1;
                fd = fe + 1;
                maior = fe;
                this.seek(fe);
                reg.read(this.file);
                this.seek(fd);
                aux.read(this.file);
                if(fd < TL) {
                    comp++;
                    if(aux.getCodigo() > reg.getCodigo())
                        maior = fd;
                }
                this.seek(pai);
                reg.read(this.file);
                this.seek(maior);
                aux.read(this.file);
                this.comp++;
                if(aux.getCodigo() > reg.getCodigo()) {
                    this.mov++;
                    this.seek(pai);
                    aux.write(this.file);
                    this.mov++;
                    this.seek(maior);
                    reg.write(this.file);
                }
            }

            this.seek(0);
            reg.read(this.file);
            this.seek(TL - 1);
            aux.read(this.file);
            this.mov++;
            this.seek(0);
            aux.write(this.file);
            this.mov++;
            this.seek(TL - 1);
            reg.write(this.file);
            TL--;
        }
    }

    public void ShellSort() {
        Registro reg = new Registro();
        Registro aux = new Registro();
        int TL = filesize();

        for(int dist = 4; dist > 0; dist /= 2) {

            for(int i = 0; i < dist; i++) {

                for(int j = i; j + dist < TL; j += dist) {
                    this.seek(j);
                    reg.read(this.file);
                    this.seek(j + dist);
                    aux.read(this.file);
                    this.comp++;
                    if(reg.getCodigo() > aux.getCodigo()) {
                        this.mov += 2;
                        this.seek(j);
                        aux.write(this.file);
                        this.seek(j + dist);
                        reg.write(this.file);
                        int k = j;
                        if(k - dist >= 0) {
                            this.comp++;
                            this.seek(k);
                            reg.read(this.file);
                            this.seek(k - dist);
                            aux.read(this.file);
                        }


                        for(; k >= 0 && reg.getCodigo() < aux.getCodigo(); k -= dist) {
                            this.mov += 2;
                            this.seek(k);
                            aux.write(this.file);
                            this.seek(k - dist);
                            reg.write(this.file);
                            if(k - dist >= 0) {
                                this.comp++;
                                this.seek(k);
                                reg.read(this.file);
                                this.seek(k - dist);
                                aux.read(this.file);
                            }
                        }
                    }
                }
            }
        }
    }

    public void QuickWithoutPivet() {
        this.QuickSortWithoutPivet(0, this.filesize() - 1);
    }

    private void QuickSortWithoutPivet(int ini, int end) {
        int i = ini;
        int j = end;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(i < j) {
            this.seek(i);
            reg1.read(this.file);
            this.seek(j);
            reg2.read(this.file);

            while(i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                this.comp++;
                i++;
                this.seek(i);
                reg1.read(this.file);
            }

            if(i < j) {
                this.mov += 2;
                this.seek(i);
                reg2.write(this.file);
                this.seek(j);
                reg1.write(this.file);
            }
            this.seek(i);
            reg1.read(this.file);
            this.seek(j);
            reg2.read(this.file);
            while(j > i && reg2.getCodigo() >= reg1.getCodigo()) {
                this.comp++;
                j--;
                this.seek(j);
                reg2.read(this.file);
            }

            if(i < j) {
                this.mov += 2;
                this.seek(i);
                reg2.write(this.file);
                this.seek(j);
                reg1.write(this.file);
            }
        }

        if(ini < j - 1)
            this.QuickSortWithoutPivet(ini, j - 1);

        if(end > i + 1)
            this.QuickSortWithoutPivet(j + 1, end);
    }

    public void QuickWithPivet() {
        this.QuickSortWithPivet(0, this.filesize() - 1);
    }

    private void QuickSortWithPivet(int ini, int end) {
        int i = ini;
        int j = end;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        Registro pivet = new Registro();

        this.seek((ini + end) / 2);
        pivet.read(this.file);

        while(i < j)
        {
            this.seek(i);
            reg1.read(this.file);

            comp ++;

            while(reg1.getCodigo() < pivet.getCodigo())
            {
                i++;
                this.seek(i);
                reg1.read(this.file);

                comp ++;
            }
            this.seek(j);
            reg2.read(this.file);

            comp ++;

            while(reg2.getCodigo() > pivet.getCodigo())
            {
                j--;
                this.seek(j);
                reg2.read(this.file);

                comp ++;
            }

            if(i <= j)
            {
                mov += 2;

                this.seek(i);
                reg2.write(this.file);
                this.seek(j);
                reg1.write(this.file);

                i++; j--;
            }
        }

        if(ini < j)
            this.QuickSortWithPivet(ini, j);

        if(end > i)
            this.QuickSortWithPivet(i, end);
    }

    public void Merge1() {
        int seq = 1;
        int TL = filesize();
        int mid = TL / 2;
        int i, j, k, aux_seq, aux_seq2;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        this.truncate(2 * TL);

        while(seq < TL) {

            for(i = 0; i < mid; i++) {
                this.seek(i);
                reg1.read(this.file);
                this.seek(TL + i);
                reg1.write(this.file);
                this.seek(i + mid);
                reg2.read(this.file);
                this.seek(TL + mid + i);
                reg2.write(this.file);
            }
            k = i = j = 0;
            aux_seq = seq;
            aux_seq2 = seq;

            while(k < TL) {

                while(i < aux_seq2 && j < aux_seq2) {
                    this.seek(TL + i);
                    reg1.read(this.file);
                    this.seek(TL + mid + j);
                    reg2.read(this.file);
                    this.comp++;
                    if(reg1.getCodigo()< reg2.getCodigo()) {
                        this.mov++;
                        this.seek(k);
                        reg1.write(this.file);
                        i++;
                    }
                    else {
                        this.mov++;
                        this.seek(k);
                        reg2.write(this.file);
                        j++;
                    }
                    k++;
                }


                while(i < aux_seq2) {
                    this.mov++;
                    this.seek(k++);
                    reg1.write(this.file);
                    this.seek(++i + TL);
                    reg1.read(this.file);
                }


                while(j < aux_seq2) {
                    this.mov++;
                    this.seek(k++);
                    reg2.write(this.file);
                    this.seek(++j + TL + mid);
                    reg2.read(this.file);
                }

                aux_seq2 += aux_seq;
            }
            seq += seq;
        }
        truncate(TL);
    }

    private void fusion(Arquivos aux, int ini_1, int end_1, int ini_2, int end_2) {
        int k = 0;
        int i = ini_1;
        int j = ini_2;
        Registro reg1= new Registro();
        Registro reg2 = new Registro();

        aux.seek(0);

        while(i <= end_1 && j <= end_2) {
            this.seek(i);
            reg1.read(this.file);
            this.seek(j);
            reg2.read(this.file);

            this.comp++;
            if(reg1.getCodigo() < reg2.getCodigo()) {
                this.seek(k++);
                reg1.write(aux.getFile());
                i++;
            }
            else {
                this.seek(k++);
                reg2.write(aux.getFile());
                j++;
            }
        }

        while(i <= end_1) {
            this.seek(i++);
            reg1.read(this.file);

            this.seek(k++);
            reg1.write(aux.getFile());
        }

        while(j <= end_2)
        {
            this.seek(j++);
            reg2.read(this.file);

            this.seek(k++);
            reg2.write(aux.getFile());
        }


        aux.seek(0);
        for(i = 0; i < k; i++)
        {
            this.seek(i + ini_1);
            mov++;
            reg1.read(aux.getFile());
            reg1.write(this.file);
        }
        aux.truncate(0);
    }

    public void SelectionSort() {
        Registro aux = new Registro();
        Registro reg = new Registro();
        int TL = this.filesize();

        for(int i = 0; i < TL - 1; i++) {
            int pos = i;
            this.seek(pos);
            reg.read(this.file);

            int j = i + 1;

            while(j < TL) {
                this.seek(j);
                aux.read(this.file);

                this.comp++;
                if(aux.getCodigo()< reg.getCodigo()) {
                    pos = j;
                    this.seek(pos);
                    reg.read(this.file);
                }
                j++;
            }

            this.seek(i);
            aux.read(this.file);

            this.mov++;
            this.seek(pos);
            aux.write(this.file);

            this.mov++;
            this.seek(i);
            reg.write(this.file);
        }
    }

    private void insertion(int ini, int end) {
        Registro aux = new Registro();
        Registro reg = new Registro();
        int i = ini + 1;
        int TL = end;
        int pos;

        while(i < TL) {
            pos = i;
            this.seek(pos - 1);
            reg.read(this.file);
            aux.read(this.file);

            this.comp++;

            while(pos > ini && aux.getCodigo() < reg.getCodigo()) {
                mov++;
                this.seek(pos);
                reg.write(this.file);

                pos--;

                this.seek(pos - 1);
                reg.read(this.file);

                this.comp++;
            }

            this.mov++;
            this.seek(pos);
            aux.write(this.file);

            i++;
        }
    }

    public void InsertionSort() {
        this.insertion(0, this.filesize());
    }

    private int BinarySearch(int ele, int ini, int TL) {
        int end = TL - 1;
        int mid = (ini + end) / 2;
        Registro reg = new Registro();

        this.seek(mid);
        reg.read(this.file);

        this.comp++;

        while(mid != ini && ele != reg.getCodigo()) {
            this.comp++;
            if(ele < reg.getCodigo())
                end = mid;
            else
                ini = mid;

            mid = (ini + end) / 2;
            this.seek(mid);
            reg.read(this.file);
            this.comp++;
        }
        this.comp++;
        this.seek(TL - 1);
        reg.read(this.file);
        if(ele > reg.getCodigo())
            return TL;
        this.comp++;
        this.seek(mid);
        reg.read(this.file);
        if(ele > reg.getCodigo())
            return mid + 1;
        return mid;
    }

    public void BinaryInsert(int ini, int end) {
        Registro aux = new Registro();
        Registro reg = new Registro();
        Registro reg_pos = new Registro();
        int TL = (end == -1) ? filesize() : end;
        int i = ini + 1;

        while(i < TL) {
            this.seek(i);
            aux.read(this.file);
            int pos = BinarySearch(aux.getCodigo(), ini, i);

            for(int j = i; j > pos; j--) {
                this.seek(j - 1);
                reg.read(this.file);
                reg_pos.read(this.file);
                this.mov += 2;
                this.seek(j - 1);
                reg_pos.write(this.file);
                reg.write(this.file);
            }
            i++;
        }
    }



    public void Merge2() {
        int mid, left, right;
        Stack<Integer> p1 = new Stack<>();
        Stack<Integer> p2 = new Stack<>();
        Arquivos aux = new Arquivos("auxMerge.dat");
        p1.push(0);
        p1.push(filesize() - 1);
        while(!p1.isEmpty()) {
            right = p1.pop();
            left = p1.pop();

            if(left < right) {
                p2.push(left);
                p2.push(right);
                mid = (left + right) / 2;
                p1.push(left);
                p1.push(mid);
                p1.push(mid + 1);
                p1.push(right);
            }
        }

        while(!p2.isEmpty()) {
            right = p2.pop();
            left = p2.pop();
            mid = (left + right) / 2;

            this.fusion(aux, left, mid, mid + 1, right);
        }
        aux.truncate(0);
    }

    public void CountingSort() {
        int range = Main.reg;
        int TL = filesize();
        Registro reg = new Registro();
        Arquivos auxc = new Arquivos("auxMerge.dat");
        auxc.truncate(TL);
        int count[] = new int[range];

        for(int i = 0; i < TL; i++) {
            this.seek(i);
            reg.read(this.file);
            count[reg.getCodigo()]++;
        }

        for(int i = 0; i < range - 1; i++)
            count[i + 1] += count[i];

        for(int i = TL - 1; i >= 0; i--) {
            this.seek(i);
            reg.read(this.file);
            auxc.seek(--count[reg.getCodigo()]);
            reg.write(auxc.getFile());
        }

        this.seek(0);
        auxc.seek(0);

        for(int i = 0; i < TL; i++) {
            mov++;
            reg.read(auxc.getFile());
            reg.write(this.file);
        }
        auxc.truncate(0);
    }

    public void GnomeSort() {
        int TL = filesize();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        for(int i = 0; i < TL - 1; i++) {
            this.seek(i);
            reg1.read(this.file);
            reg2.read(this.file);

            this.comp++;

            if(reg1.getCodigo() > reg2.getCodigo()) {
                int j = i;
                this.comp++;
                while(j >= 0 && reg2.getCodigo() < reg1.getCodigo()) {
                    this.mov += 2;
                    this.seek(j);
                    reg2.write(this.file);
                    reg1.write(this.file);
                    j--;
                    if(j >= 0) {
                        this.seek(j);
                        reg1.read(this.file);
                        reg2.read(this.file);
                        this.comp++;
                    }
                }
            }
        }
    }

    public void BucketSort() {
        Registro mat[][] = new Registro[10][Main.reg];
        int[] index = new int[10];
        Registro reg;
        int i, tl = filesize(), j, k;

        for(i = 0; i < tl; i++) {
            reg = new Registro();
            this.seek(i);
            reg.read(this.file);
            mat[(int) ((reg.getCodigo() / (float) Main.reg) * 10)][index[(int) ((reg.getCodigo() / (float) Main.reg) * 10)]++] = reg;
        }

        for(i = 0; i < 10; i++) {
            j = 1;

            while(j < index[i]) {
                k = j;
                this.comp++;
                while(mat[i][k].getCodigo() < mat[i][k - 1].getCodigo()) {
                    reg = mat[i][k];
                    mat[i][k] = mat[i][k - 1];
                    mat[i][k - 1] = reg;
                    this.comp++;
                }
                j++;
            }

            this.seek(0);
            for(i = 0; i < 10; i++)
                for(j = 0; j < index[i]; j++) {
                    this.mov++;
                    mat[i][j].write(this.file);
                }
        }
    }

    public void RadixSort() {
        int max = Main.reg;
        int TL = filesize();
        Registro reg = new Registro();
        Arquivos aux = new Arquivos("auxMerge.dat");
        for(int i = 1; i < max; i *= 10) {
            aux.truncate(TL);
            aux.seek(0);
            int[] count = new int[10];

            this.seek(0);

            for(int j = 0; j < TL; j++) {
                reg.read(this.file);
                count[(reg.getCodigo() / i) % 10]++;
            }

            for(int j = 0; j < 9; j++)
                count[j + 1] += count[j];

            for(int j = TL - 1; j >= 0; j--) {
                this.seek(j);
                reg.read(this.file);
                aux.seek(--count[(reg.getCodigo() / i) % 10]);
                reg.write(aux.getFile());
            }

            aux.seek(0);
            this.seek(0);

            for(int j = 0; j < TL; j++) {
                this.mov++;
                reg.read(aux.getFile());
                reg.write(this.file);
            }
        }
        aux.truncate(0);
    }

    public void CombSort() {
        int i = 0, TL = filesize(), fator = (int) (TL / 1.3);
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(fator > 0 && i != TL - 1) {
            i = 0;

            while(i + fator < TL) {
                this.seek(i);
                reg1.read(this.file);
                this.seek(fator + i);
                reg2.read(this.file);
                this.comp++;
                if(reg1.getCodigo() > reg2.getCodigo()) {
                    this.mov += 2;
                    this.seek(i);
                    reg2.write(this.file);
                    this.seek(fator + i);
                    reg1.write(this.file);
                }
                i++;
            }
            fator = (int) (fator / 1.3);
        }
    }

    public void TimSort() {
        int TL = filesize();
        int run = 32;
        int right, mid;
        Arquivos aux = new Arquivos("auxMerge.dat");
        aux.truncate(TL);

        for(int i = 0; i < TL; i += run) {
            if(i + run < TL)
                this.BinaryInsert(i, i + run);
            else
                this.BinaryInsert(i, TL);
        }

        for(int size = run; size < TL; size *= 2)

            for(int left = 0; left < TL; left += 2 * size) {
                if(left + 2 * size < TL)
                    right = left + 2 * size - 1;
                else
                    right = TL - 1;
                mid = (left + right) / 2;
                this.fusion(aux, left, mid, mid + 1, right);
            }
    }
}
