import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class BankClient {
    public static void main(String[] args) throws InterruptedException {
        //Oppg4:
        BankClient bc = new BankClient();
        bc.opOverfoering(1,2,300.0);

//        //Oppg3: Kjører 2 kodestykker med thread.sleep, blir feil resultat pga når data blir hentet inn/ut
//        Thread a = new Oppg3A();
//        Thread b = new Oppg3B();
//
//        a.start();
//        b.start();
    }
    public void opOverfoering(int k1, int k2, double beloep) throws InterruptedException {
        //oppgave 4, optimistisk låsing med overføring mellom 2 kontoer
        //når man prøver å framprovosere feil, vil metoden gå i løkke fram til riktig svar kommer
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Account kont1 = entityManager.find(Account.class,k1);
        Account kont2 = entityManager.find(Account.class,k2);
        Date date1 = kont1.getUpdated_at();
        Date date2 = kont2.getUpdated_at();

        double saldo1 = kont1.getBalance();
        double saldo2 = kont2.getBalance();

        kont1.withdraw(beloep);
        kont2.addBalance(beloep);

        Account sjekk1 = entityManager.find(Account.class,k1);
        Account sjekk2 = entityManager.find(Account.class,k2);

        if(date1 == sjekk1.getUpdated_at() && date2 == sjekk2.getUpdated_at()){
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
        }
        else{
            entityManager.close();
            entityManagerFactory.close();
            opOverfoering(k1,k2,beloep);
        }
    }
}
