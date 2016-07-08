/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DAO.AlunoDAO;
import Entidades.Aluno;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import util.FilterInput;

/**
 *
 * @author Yuri
 */
public class AlterarAlunoBean {
    private String nome;
    private String email;
    private String password;
    private String confirmaSenha;
    private String msg;

    
    @PostConstruct
    public void init(){
       System.out.println("entrei aqui");
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       Aluno aluno = (Aluno)request.getSession().getAttribute("user");
       
       this.nome = aluno.getNome();
       this.email = aluno.getEmail();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String enviar(){
       FacesContext context = FacesContext.getCurrentInstance();
       HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest(); 
       
        try {
            Aluno aluno = (Aluno)request.getSession().getAttribute("user");
            if(FilterInput.noDangerousCharacters(nome) && FilterInput.noDangerousCharacters(password) && FilterInput.noDangerousCharacters(email)){
                aluno.setEmail(email);
                aluno.setNome(nome);
                aluno.setPassword(password);

                AlunoDAO.update(aluno);

                return "sucesso";
            }else{
                return "falhou";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "falhou";
    }
    
}
