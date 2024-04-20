public class Stack<T> {
    public Stack() {
        this.topo = null;
    }

    private Elemento<T> topo;
    public boolean isEmpty() {
        return topo == null;
    }
    public void push(T elemento){
        if(topo == null)
            topo = new Elemento<>(elemento, topo);
        else {
            Elemento<T> aux = new Elemento<>(elemento, topo);
            topo = aux;
        }
    }
    public T pop() {
        Elemento<T> aux = topo;
        topo = topo.getProx();
        return aux.getElem();
    }


}