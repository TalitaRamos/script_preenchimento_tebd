package script_preenchimento.hibernate;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioController {
    EntityManagerFactory emf;
    EntityManager em;

    public UsuarioController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarUsuario(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void AdicionarUsuarioCartao()throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
//        Root<Usuario> root = criteria.from(Usuario.class);
//        criteria.where(
//                builder.equal(root.get("usuario"))
//        );
        List<Usuario> usuarioList =  em.createQuery(criteria).getResultList();

        for(int i =0;i<usuarioList.size();i++){
            String cartao_numero = "558418451604135"+i;
            String cartao_data_venciment="11/202"+i;
            String cartao_marca="mastercard";

            Cartao cartao = new Cartao(cartao_numero,cartao_data_venciment,cartao_marca,usuarioList.get(i));
            CartaoController cartaoController = new CartaoController();
            cartaoController.salvarCartao(cartao);
            cartaoController.fechar();
        }
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
