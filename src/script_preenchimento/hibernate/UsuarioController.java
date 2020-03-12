package script_preenchimento.hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioController {
    EntityManagerFactory emf;
    EntityManager em;

    public UsuarioController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em = emf.createEntityManager();
    }

    public void salvarUsuario(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public List<Usuario> findAll() {
//        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> rootEntry = cq.from(Usuario.class);
        CriteriaQuery<Usuario> all = cq.select(rootEntry);
        TypedQuery<Usuario> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void AdicionarUsuarioCartao(Usuario usuario, Cartao cartao)throws Exception{
        usuario.setCartao(cartao);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void AdicionarArtigoAutor()throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
//        Root<Usuario> root = criteria.from(Usuario.class);
//        criteria.where(
//                builder.equal(root.get("usuario"))
//        );
        List<Usuario> usuarioList =  em.createQuery(criteria).getResultList();

        CriteriaBuilder builder2 = em.getCriteriaBuilder();
        CriteriaQuery<Artigo> criteria2 = builder2.createQuery(Artigo.class);
//        Root<Usuario> root2 = criteria2.from(Usuario.class);
//        criteria.where(
//                builder.equal(root.get("usuario"))
//        );
        List<Artigo> artigoList =  em.createQuery(criteria2).getResultList();
//
//
//        nota.setAluno(aluno);
//        em.getTransaction().begin();
//        em.persist(nota);
//        em.getTransaction().commit();
    }

    public void fechar() {
        emf.close();
    }
}
