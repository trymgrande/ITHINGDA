class Josephus{

    public static void solve(Liste l, int intervall){
        
        Node n = l.getHead();
        Node p = l.getHead();

        while(n.getLink() != n){
            int num = 1; 

            while(num != intervall){
                p = n;
                n = n.getLink();
                num++;
            }
            System.out.println("Dreper "+n.getNr());
            p.setLink(n.getLink());
            n = p.getLink();
        }
        System.out.println(n.getNr() +" overlevde");
    }

    public static Liste lagDatasett(int ant){
        Node head = new Node(1, null);
        Liste l = new Liste(head);

        for(int i = 2; i <= ant; i++){
            Node t = new Node(i, null);
            l.addElement(t);
        }
        return l;
    }

    public static void main(String[] args) {
        Liste l = lagDatasett(10);
        solve(l, 4);
    }
}

class Liste{
    private Node head;
    private int antElementer = 1;

    public Liste(Node head){
        this.head = head;
    }

    public int getAntElem(){
        return antElementer;
    }
    public Node getHead(){
        return head;
    }

    public void writeall(){
        Node n = head; 
        do{
            System.out.println(n.getNr());
            n = n.getLink();
        }while(n != head);
    }

    public void addElement(Node n){
        if(antElementer == 1){
            head.setLink(n);
            n.setLink(head);
            System.out.println("Setter link på element " + head.getNr()+" til "+ n.getNr());
            antElementer += 1;
        } else {
            Node temp = head;

            while(temp.getLink() != head) temp = temp.getLink();

            System.out.println("Setter link på element " + temp.getNr()+" til "+ n.getNr());
            temp.setLink(n);
            n.setLink(head);
            antElementer += 1;
        }
    }

    public void removeElement(int n){
        
        Node t = head; 
        Node prev = t;
        
        for(int i = 1; i < n; i++){
            prev = t;
            t = t.getLink();
        }

        if(t.equals(head)) head = t.getLink();

        System.out.println("Fjernet element " + prev.getLink().getNr());
        prev.setLink(t.getLink());
        antElementer -= 1;
    }
}

class Node{
    private int nummer;
    private Node link;

    public Node(int nummer, Node link){
        this.nummer = nummer;
        this.link = link;
    }

    public void setLink(Node n){
        link = n;
    }

    public Node getLink(){
        return link;
    }
    public int getNr(){
        return nummer;
    }

    public boolean equals(Node n) {
        if(nummer == n.getNr() && link == n.getLink()) return true;
        return false;
    }
    
}