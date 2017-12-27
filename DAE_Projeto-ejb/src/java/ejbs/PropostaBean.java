/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


import dtos.PropostaDTO;
import entities.Proposta;
import entities.Teacher;
import enums.TipoTrabalho;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author luisvf7
 */
@Stateless
public class PropostaBean {
    @PersistenceContext
    private EntityManager em;
    
    public void create(TipoTrabalho tipoTrabalho, 
                    String titulo, String areas_cientificas,
                    String proponentes, String resumo,
                    String objetivos, String bibliografia,
                    String plano_trabalhos, String local_realizacao, 
                    String requisitos, float orcamento, 
                    String apoios, String orientadores,
                    String supervisor)
            throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Proposta.class, titulo) != null) {
                throw new EntityAlreadyExistsException("A proprosal with that title already exists.");
            }
              
            em.persist(new Proposta(tipoTrabalho,
                    titulo,
                    parseListCommaSeparatedString(areas_cientificas),
                    parseListCommaSeparatedString(proponentes),
                    resumo,
                    objetivos,
                    parseListCommaSeparatedString(bibliografia),
                    plano_trabalhos,
                    local_realizacao,
                    requisitos,
                    orcamento,
                    apoios,
                    parseListTeachers(orientadores),
                    supervisor));

        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        
    }
    public List<PropostaDTO> getAll() {
        try {
            List<Proposta> propostas = (List<Proposta>) em.createNamedQuery("getAllPropostas").getResultList();
            return propostasToDTOs(propostas);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    List<PropostaDTO> propostasToDTOs(List<Proposta> propostas) {
        List<PropostaDTO> dtos = new ArrayList<>();
        for (Proposta p : propostas) {
            dtos.add(propostaToDTO(p));            
        }
        return dtos;
    }
    
    private PropostaDTO propostaToDTO(Proposta p){
        
        //parse das areas cientificas
        return new PropostaDTO(p.getTipoTrabalho(),
                    p.getTitulo(),
                    p.getAreas_cientificas().toString(),
                    p.getProponentes().toString(),
                    p.getResumo(),
                    p.getObjetivos(),
                    p.getBibliografia().toString(),
                    p.getPlano_trabalhos(),
                    p.getLocal_realizacao(),
                    p.getRequisitos(),
                    p.getOrcamento(),
                    p.getApoios(),
                    p.getOrientadores().toString(),
                    p.getSupervisor());
    }

    public void update(int id,TipoTrabalho tipoTrabalho, String titulo,
            String areas_cientificas,
            String proponentes, String resumo,
            String objetivos, String bibliografia,
            String plano_trabalhos, String local_realizacao,
            String requisitos, float orcamento, String apoios,
            String orientadores, String supervisor) throws MyConstraintViolationException, EntityDoesNotExistsException {
        try {
            Proposta proposta = em.find(Proposta.class, titulo);
            if (proposta == null) {
                throw new EntityDoesNotExistsException("There is no proposta with that code.");
            }           
            proposta.setApoios(apoios);
            proposta.setAreas_cientificas(parseListCommaSeparatedString(areas_cientificas));
            proposta.setBibliografia(parseListCommaSeparatedString(bibliografia));
            proposta.setLocal_realizacao(local_realizacao);
            proposta.setObjetivos(objetivos);
            proposta.setOrcamento(orcamento);
            proposta.setOrientadores(parseListTeachers(orientadores));
            proposta.setPlano_trabalhos(plano_trabalhos);
            proposta.setProponentes(parseListCommaSeparatedString(proponentes));
            proposta.setRequisitos(requisitos);
            proposta.setResumo(resumo);
            proposta.setSupervisor(supervisor);
            proposta.setTipoTrabalho(tipoTrabalho);
            proposta.setTitulo(titulo);
            em.merge(proposta);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(int id) throws EntityDoesNotExistsException {
        try {
            Proposta proposta = em.find(Proposta.class, id);
            if (proposta == null) {
                throw new EntityDoesNotExistsException("There is no proposta with that code");
            }
            em.remove(proposta);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public LinkedList<String> parseListCommaSeparatedString(String s){
        String[] parts = s.split(",");
        LinkedList<String> list = new LinkedList<>();
        for (String str : parts) {
            list.add(str);
        }
        return list;
    }
    
    public LinkedList<Teacher> parseListTeachers(String s){
        String[] parts = s.split(",");
        LinkedList<Teacher> list = new LinkedList<>();
        for (String str : parts) {
            list.add( em.find(Teacher.class, str));
        }
        return list;
    }
}
