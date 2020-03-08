package script_preenchimento.hibernate;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtigoAutorController {
    EntityManagerFactory emf;
    EntityManager em;

    public ArtigoAutorController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarArtigo(ArtigoAutor artigoAutor){
        em.getTransaction().begin();
        em.persist(artigoAutor);
        em.getTransaction().commit();
    }

    public void fechar() {
        emf.close();
    }
}
