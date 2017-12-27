/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.TipoTrabalho;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author luisvf7
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllPropostas",
    query = "SELECT p FROM Proposta p")})
public class Proposta implements Serializable {

    
    
    
    private TipoTrabalho tipoTrabalho;
    
    @Id
    @Column(name="TITULO")
    private String titulo;
    
    private List<String> areas_cientificas;
    
    //Quem propoe - Instituições ou Professores ?
    private List<String> proponentes;
    
    //@ManyToMany(mappedBy="proposta", cascade = CascadeType.REMOVE)
    private List<Teacher> orientadores;
    private String supervisor;
    
    private String resumo;
    private String objetivos;
    private List<String> bibliografia; //max: 5 referencias
    private String plano_trabalhos;
    private String local_realizacao;
    private String requisitos;
    private float orcamento;
    private String apoios; //financeiros ou outro tipo

    public Proposta() {
        areas_cientificas = new LinkedList<>();
        proponentes = new LinkedList<>();
        orientadores = new LinkedList<>();
        bibliografia=new LinkedList<>();
    }

    public Proposta(TipoTrabalho tipoTrabalho, 
                    String titulo, List<String> areas_cientificas,
                    List<String> proponentes, String resumo,
                    String objetivos, List<String> bibliografia,
                    String plano_trabalhos, String local_realizacao, 
                    String requisitos, float orcamento, 
                    String apoios, List<Teacher> orientadores,
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
 
    @Override
    public String toString() {
        return "Proposta: "+titulo;
    }

    public TipoTrabalho getTipoTrabalho() {
        return tipoTrabalho;
    }

    public void setTipoTrabalho(TipoTrabalho tipoTrabalho) {
        this.tipoTrabalho = tipoTrabalho;
    }

    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAreas_cientificas() {
        return areas_cientificas;
    }

    /**
     * @param areas_cientificas the areas_cientificas to set
     */
    public void setAreas_cientificas(List<String> areas_cientificas) {
        this.areas_cientificas = areas_cientificas;
    }

    /**
     * @return the proponentes
     */
    public List<String> getProponentes() {
        return proponentes;
    }

    /**
     * @param proponentes the proponentes to set
     */
    public void setProponentes(List<String> proponentes) {
        this.proponentes = proponentes;
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
    public List<String> getBibliografia() {
        return bibliografia;
    }

    /**
     * @param bibliografia the bibliografia to set
     */
    public void setBibliografia(List<String> bibliografia) {
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

    /**
     * @return the orientadores
     */
    public List<Teacher> getOrientadores() {
        return orientadores;
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
     * @param orientadores the orientadores to set
     */
    public void setOrientadores(List<Teacher> orientadores) {
        this.orientadores = orientadores;
    }
    
}
