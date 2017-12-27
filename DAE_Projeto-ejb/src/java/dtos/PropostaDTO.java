package dtos;

import entities.Teacher;
import enums.TipoTrabalho;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class PropostaDTO  implements Serializable{

    @Id
    private int id;
    
    private TipoTrabalho tipoTrabalho;
    private String titulo;
    private String areas_cientificas; //parse
    //Quem propoe - Instituições ou Professores ?
    private String proponentes; //parse
    private String orientadores; //parse para List<Teacher>
    private String supervisor;
    private String resumo;
    private String objetivos;
    private String bibliografia; //max: 5 referencias //parse
    private String plano_trabalhos;
    private String local_realizacao;
    private String requisitos;
    private float orcamento;
    private String apoios; //financeiros ou outro tipo 

    public PropostaDTO() {
    }

    public PropostaDTO(TipoTrabalho tipoTrabalho, 
                    String titulo, String areas_cientificas,
                    String proponentes, String resumo,
                    String objetivos, String bibliografia,
                    String plano_trabalhos, String local_realizacao, 
                    String requisitos, float orcamento, 
                    String apoios, String orientadores,
                    String supervisor) {
        this.tipoTrabalho = tipoTrabalho;
        this.titulo = titulo;
        this.areas_cientificas = areas_cientificas;
        this.proponentes = proponentes;
        this.resumo = resumo;
        this.objetivos = objetivos;
        this.bibliografia = bibliografia;
        this.plano_trabalhos = plano_trabalhos;
        this.local_realizacao = local_realizacao;
        this.requisitos = requisitos;
        this.orcamento = orcamento;
        this.apoios = apoios;
        this.orientadores = orientadores;
    }
    
    public void reset(){
        this.setTipoTrabalho(null);
        this.setTitulo(null);
        this.setAreas_cientificas(null);
        this.setProponentes(null);
        this.setResumo(null);
        this.setObjetivos(null);
        this.setBibliografia(null);
        this.setPlano_trabalhos(null);
        this.setLocal_realizacao(null);
        this.setRequisitos(null);
        this.setOrcamento(0);
        this.setApoios(null);   
        this.setOrientadores(null);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipoTrabalho
     */
    public TipoTrabalho getTipoTrabalho() {
        return tipoTrabalho;
    }

    /**
     * @param tipoTrabalho the tipoTrabalho to set
     */
    public void setTipoTrabalho(TipoTrabalho tipoTrabalho) {
        this.tipoTrabalho = tipoTrabalho;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the areas_cientificas
     */
    public String getAreas_cientificas() {
        return areas_cientificas;
    }

    /**
     * @param areas_cientificas the areas_cientificas to set
     */
    public void setAreas_cientificas(String areas_cientificas) {
        this.areas_cientificas = areas_cientificas;
    }

    /**
     * @return the proponentes
     */
    public String getProponentes() {
        return proponentes;
    }

    /**
     * @param proponentes the proponentes to set
     */
    public void setProponentes(String proponentes) {
        this.proponentes = proponentes;
    }

    /**
     * @return the orientadores
     */
    public String getOrientadores() {
        return orientadores;
    }

    /**
     * @param orientadores the orientadores to set
     */
    public void setOrientadores(String orientadores) {
        this.orientadores = orientadores;
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the resumo
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     * @return the objetivos
     */
    public String getObjetivos() {
        return objetivos;
    }

    /**
     * @param objetivos the objetivos to set
     */
    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    /**
     * @return the bibliografia
     */
    public String getBibliografia() {
        return bibliografia;
    }

    /**
     * @param bibliografia the bibliografia to set
     */
    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    /**
     * @return the plano_trabalhos
     */
    public String getPlano_trabalhos() {
        return plano_trabalhos;
    }

    /**
     * @param plano_trabalhos the plano_trabalhos to set
     */
    public void setPlano_trabalhos(String plano_trabalhos) {
        this.plano_trabalhos = plano_trabalhos;
    }

    /**
     * @return the local_realizacao
     */
    public String getLocal_realizacao() {
        return local_realizacao;
    }

    /**
     * @param local_realizacao the local_realizacao to set
     */
    public void setLocal_realizacao(String local_realizacao) {
        this.local_realizacao = local_realizacao;
    }

    /**
     * @return the requisitos
     */
    public String getRequisitos() {
        return requisitos;
    }

    /**
     * @param requisitos the requisitos to set
     */
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * @return the orcamento
     */
    public float getOrcamento() {
        return orcamento;
    }

    /**
     * @param orcamento the orcamento to set
     */
    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    /**
     * @return the apoios
     */
    public String getApoios() {
        return apoios;
    }

    /**
     * @param apoios the apoios to set
     */
    public void setApoios(String apoios) {
        this.apoios = apoios;
    }

    
}
