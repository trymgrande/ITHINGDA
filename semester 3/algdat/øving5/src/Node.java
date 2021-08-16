public class Node {
    String element;
    Node neste;

    public Node(String e) {
        element = e;
        neste = null;
    }

    public String finnElement() {
        return element;
    }

    public Node finnNeste() {
        return neste;
    }

    public void setNeste(Node neste) {
        this.neste = neste;
    }
}