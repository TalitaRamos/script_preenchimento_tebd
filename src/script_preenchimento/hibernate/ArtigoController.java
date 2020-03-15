package script_preenchimento.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ArtigoController {
    EntityManagerFactory emf;
    EntityManager em;

    public ArtigoController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarArtigo(Artigo artigo){
        try {
            em.getTransaction().begin();
            em.persist(artigo);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void atualizarArtigo(Artigo artigo){
        try {
            em.getTransaction().begin();
            em.merge(artigo);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public List<Artigo> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Artigo> cq = cb.createQuery(Artigo.class);
        Root<Artigo> rootEntry = cq.from(Artigo.class);
        CriteriaQuery<Artigo> all = cq.select(rootEntry);
        TypedQuery<Artigo> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Artigo>findArtigoWithRevisao(){
        List<Artigo> artigoAll = findAll();
        List<Artigo> artigosSemRevisao = new ArrayList<>();
        RevisaoController revisaoController = new RevisaoController();
        for(int i=0;i<artigoAll.size();i++){
            if(artigoAll.get(i).getRevisaoList().size()<=0){
                artigosSemRevisao.add(artigoAll.get(i));
            }

        }
        revisaoController.fechar();
        return  artigosSemRevisao;
    }

    public void fechar() {
        emf.close();
    }
}
