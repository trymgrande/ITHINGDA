import java.io.*;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NodeGraph{
    int N,K;
    Node []node;

    public NodeGraph(String filename) throws IOException{
        File file = new File(System.getProperty("user.dir") +"/src/"+filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for (int i = 0; i < N; i++){
            node[i] = new Node();
            node[i].verdi = i;
        }
        K = Integer.parseInt(st.nextToken());
        for (int j = 0; j < K; j++){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            Kant k = new Kant(node[til], node[fra].kant1);
            node[fra].kant1 = k;
        }
    }

    public void initForgjenger(Node s){
        for (int i = N; i-- > 0;){
            node[i].d = new Forgjenger();
        }
        ((Forgjenger)s.d).distanse = 0;

    }

    public void bfs(Node s){
        initForgjenger(s);
        LinkedList<Node> kø = new LinkedList<Node>();
        //((Forgjenger)s.d)
        kø.add(s);
        String resultatTekst="";
        int[] verdier = new int[N];
        int[] distanser = new int[N];
        int[] forgjengere = new int[N-1];
        int counter = 0;
        int counter2 = 0;
        while (!kø.isEmpty()){
            Node n = (Node)kø.pop();
            //System.out.println(n.verdi+"     "+"      "+((Forgjenger)n.d).distanse);
            verdier[counter] = n.verdi;
            distanser[counter] = ((Forgjenger)n.d).distanse;
            for (Kant k = n.kant1; k != null; k=k.neste){
                Forgjenger f = (Forgjenger)k.til.d;
                if (f.distanse == f.uendelig){
                    f.distanse = ((Forgjenger)n.d).distanse + 1;
                    f.forgjenger = n;
                    kø.add(k.til);
                    forgjengere[counter2] = f.forgjenger.verdi;
                    counter2++;
                    // System.out.println(f.forgjenger.verdi);
                }
            }
            counter++;
        }
        System.out.println(verdier[0]+"         "+distanser[0]);
        for (int i = 0; i < forgjengere.length;i++){
            System.out.println(verdier[i+1]+"    "+forgjengere[i]+"    "+distanser[i+1]);
        }
    }

    public Node df_topo(Node n, Node l){
        Topo_lst nd = (Topo_lst)n.d;
        if (nd.funnet){
            return l;
        }
        nd.funnet = true;
        for (Kant k = n.kant1; k != null; k = k.neste){
            l = df_topo(k.til,l);
        }
        nd.neste = l;
        return n;
    }

    public Node topologisort(){
        Node l = null;
        for (int i = N; i-- > 0;){
            node[i].d = new Topo_lst();
            ((Topo_lst)node[i].d).verdi = i;
        }

        for (int i = N; i-- > 0;){
            l = df_topo(node[i], l);
        }
        return l;
    }

    public static void main(String[] args) throws IOException{
        NodeGraph nodeGraph = new NodeGraph("L7g1");
        //oppgave1
//        nodeGraph.bfs(nodeGraph.node[5]);
        //oppgave2
        nodeGraph.topologisort();
        for (Topo_lst i = (Topo_lst)nodeGraph.topologisort().d; i != null; i = (Topo_lst) i.neste.d){
            System.out.println(i.verdi);
        }
    }
}

class Kant{
    Kant neste;
    Node til;
    public Kant(Node n, Kant neste){
        til = n;
        this.neste = neste;
    }
}

class Node{
    Kant kant1;
    Object d;
    int verdi;

    public void setForgjenger(Node n){
        this.d = n;
    }
}

class Forgjenger{
    int verdi;
    int distanse;
    Node forgjenger;
    final int uendelig = 1000000000;

    public Forgjenger(){
        distanse = uendelig;
    }

    public int finnDistanse(){
        return distanse;
    }
    public Node finnForgjenger(){
        return forgjenger;
    }

}

class Topo_lst{
    int verdi;
    boolean funnet;
    Node neste;
}



