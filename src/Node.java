public class Node {
    private Node prox, ant;
    private int info;

    public Node(Node ant, Node prox, int info) {
        this.prox = prox;
        this.ant = ant;
        this.info = info;
    }

    public Node(int info) {
        this.info = info;
        prox = ant = null;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }

    public Node getAnt() {
        return ant;
    }

    public void setAnt(Node ant) {
        this.ant = ant;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
