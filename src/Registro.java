import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {
    //public final int TF = 1022;
    public final int TF = 0;
    private int codigo;
    private char[] lixo;

    public Registro() {
        this.lixo = new char[this.TF];
    }

    public Registro(int codigo) {
        this.lixo = new char[this.TF];
        this.codigo = codigo;
        for (int i = 0; i < this.TF; i++)
            lixo[i] = 'X';
    }


    public int getCodigo() {
        return this.codigo;
    }

    public String getLixo() {
        return new String(this.lixo);
    }


    public void write(RandomAccessFile file) {
        try {
            file.writeInt(this.codigo);
            for (int i = 0; i < this.TF; i++)
                file.writeChar(lixo[i]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void read(RandomAccessFile file) {
        try {
            this.codigo = file.readInt();
            for (int i = 0; i < this.TF; i++)
                lixo[i] = file.readChar();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void show() {
        System.out.println("codigo guardado: " + this.codigo);
    }


    public static int length() {
        return 2048;
    }

}