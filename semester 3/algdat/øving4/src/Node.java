public class Node {
    int element;
    Node neste;

    public Node(int e, Node n) {
        element = e;
        neste = n;
    }

    public double finnElement() {
        return element;
    }

    public Node finnNeste() {
        return neste;
    }

    public void setNeste(Node neste) {
        this.neste = neste;
    }
}