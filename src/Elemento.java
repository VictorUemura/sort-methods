public class Elemento<T> {
    private T elem;
    public Elemento<T> prox;

    public Elemento(T elem, Elemento<T> prox) {
        this.elem = elem;
        this.prox = prox;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public Elemento<T> getProx() {
        return prox;
    }

    public void setProx(Elemento<T> prox) {
        this.prox = prox;
    }
}