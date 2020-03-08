package script_preenchimento.hibernate;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="artigo")
public class Artigo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "artigo_id")
    int artigo_id;
    @Column(name = "artigo_titulo")
    String artigo_titulo;
    @Column(name = "artigo_resumo")
    String artigo_resumo;
    @Column(name = "artigo_arquivo")
    String artigo_arquivo;
    @Column(name = "artigo_confirma_submissao")
    int artigo_confirma_submissao;
    @Column(name = "artigo_qtd_revisores")
    int artigo_qtd_revisores;
    @Column(name = "artigo_media")
    float artigo_media;

    @OneToMany(mappedBy = "artigo", targetEntity = ArtigoAutor.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArtigoAutor> artigoAutorList;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Revisao revisao;

    public Artigo(String artigo_titulo, String artigo_resumo, String artigo_arquivo, int artigo_confirma_submissao, int artigo_qtd_revisores, float artigo_media) {
        super();
        this.artigo_titulo = artigo_titulo;
        this.artigo_resumo = artigo_resumo;
        this.artigo_arquivo = artigo_arquivo;
        this.artigo_confirma_submissao = artigo_confirma_submissao;
        this.artigo_qtd_revisores = artigo_qtd_revisores;
        this.artigo_media = artigo_media;
    }

    public int getArtigo_id() {
        return artigo_id;
    }

    public void setArtigo_id(int artigo_id) {
        this.artigo_id = artigo_id;
    }

    public String getArtigo_resumo() {
        return artigo_resumo;
    }

    public void setArtigo_resumo(String artigo_resumo) {
        this.artigo_resumo = artigo_resumo;
    }

    public String getArtigo_arquivo() {
        return artigo_arquivo;
    }

    public void setArtigo_arquivo(String artigo_arquivo) {
        this.artigo_arquivo = artigo_arquivo;
    }

    public int getArtigo_confirma_submissao() {
        return artigo_confirma_submissao;
    }

    public void setArtigo_confirma_submissao(int artigo_confirma_submissao) {
        this.artigo_confirma_submissao = artigo_confirma_submissao;
    }

    public int getArtigo_qtd_revisores() {
        return artigo_qtd_revisores;
    }

    public void setArtigo_qtd_revisores(int artigo_qtd_revisores) {
        this.artigo_qtd_revisores = artigo_qtd_revisores;
    }

    public float getArtigo_media() {
        return artigo_media;
    }

    public void setArtigo_media(float artigo_media) {
        this.artigo_media = artigo_media;
    }

    public List<ArtigoAutor> getArtigoAutorList() {
        return artigoAutorList;
    }

    public void setArtigoAutorList(List<ArtigoAutor> artigoAutorList) {
        this.artigoAutorList = artigoAutorList;
    }

    public Revisao getRevisao() {
        return revisao;
    }

    public void setRevisao(Revisao revisao) {
        this.revisao = revisao;
    }
}
