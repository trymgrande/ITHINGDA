import javax.persistence.*;
import java.sql.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello");
        // se /oppgave1.txt for oppgave 1
        oppgave2();
        // oppgave 3 - kan få samme resultat ved parallell kjøring med flere klienter
//        trasfer(1, 2, 3);
        // oppgave 4 - framprovosering av feil gjør at metoden vil kalle seg selv fram til riktig resultat
//        optimalisertOverføring(1, 2, 3);

    }

    public static void oppgave2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
Thread.sleep(5000);
//        // oppgave 2
//        // oppretter to kontoer
//
//        Account account1 = new Account();
//        account1.setOwnerName("client1");
//
//        Account account2 = new Account();
//        account2.setOwnerName("client2");
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        account1.addBalance(3.14);
//
//        // lagrer kontoer til db
//        entityManager.persist(account1);
//        entityManager.persist(account2);
//
//        // finner alle kontoer med saldo større enn 3
//        Query query1 = entityManager.createQuery("SELECT account FROM Account account WHERE account.balance > 3");
//        List<Account> resultList = query1.getResultList();
//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("account number with balance > 3: " + resultList.get(i).getAccountNumber());
//        }
//
//        // endrer eier på første konto i resultatlisten
//        int selectedAccountNumber = resultList.get(0).getAccountNumber();
//        int updateQuery = entityManager.createQuery(
//                "update Account account set account.ownerName = 'newOwner4' where account.accountNumber = " + selectedAccountNumber).executeUpdate();
//
//        // committer spørringene til db
//        entityManager.getTransaction().commit();
//        entityManager.close();
        entityManagerFactory.close();
    }

    public static void trasfer(int k1, int k2, double amount) throws InterruptedException {
        // oppgave 3
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Account kont1 = entityManager.find(Account.class,k1);
        Account kont2 = entityManager.find(Account.class,k2);

        kont1.withdraw(amount);
        kont2.addBalance(amount);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }


    public static void optimalisertOverføring(int k1, int k2, double amount) throws InterruptedException {
        // oppgave 4
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Account acc1 = entityManager.find(Account.class,k1);
        Account acc2 = entityManager.find(Account.class,k2);
        Date date1 = acc1.getUpdated_at();
        Date date2 = acc2.getUpdated_at();

        acc1.withdraw(amount);
        acc2.addBalance(amount);

        Account accountFound1 = entityManager.find(Account.class,k1);
        Account accountFound2 = entityManager.find(Account.class,k2);

        if(date1 == accountFound1.getUpdated_at() && date2 == accountFound2.getUpdated_at()){
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
        }
        else{
            entityManager.close();
            entityManagerFactory.close();
            optimalisertOverføring(k1,k2,amount);
        }
    }
}

//    34  før acc1.add  entityManager.createQuery("ALTER TABLE account MODIFY COLUMN updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP");
