/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;
import Entidades.Professor;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FilterInput;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.FilterInput;
/**
 *
 * @author Sergio Marinho
 */
public class CadastrarProfessorBean implements Serializable{
    private String nome;
    private String cpf;
    private String email;
    private String password;
    private String confirmaSenha;
    private String msg;

    public CadastrarProfessorBean() {
        
    }
    
    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    private boolean check(String a){
        if(a!=null && !a.trim().equalsIgnoreCase("")){
            return true;
        }
        return false;
    }
    public String enviar(){
        if(check(this.nome)&&check(this.password)&&check(this.confirmaSenha)&&check(this.email)&&check(this.cpf)){
            if(FilterInput.noDangerousCharacters(nome) && FilterInput.noDangerousCharacters(password) && FilterInput.noDangerousCharacters(email)){
                if(!(password.equals(confirmaSenha)&&password.length()>5)){
                    setMsg("erro");
                    return "erro";
                }
                if(cpf.length()!=11){
                    setMsg("erro");
                    return "erro";
                }
                try{
                    Long.parseLong(cpf);
                }catch(Exception e){
                    setMsg("erro");
                    return "erro";
                }
                if(!DAO.ProfessorDAO.create(new Professor(nome, cpf, email, password))){
                    setMsg("erro");
                    return "erro";
                }
                return "professorCadastrado";
            }else{
                setMsg("erro");
                return "erro";
            }
        }
        setMsg("erro");
        return "erro";
    }
}