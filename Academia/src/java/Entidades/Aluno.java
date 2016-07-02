/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author Yuri
 */
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_sequence_generator")
    @SequenceGenerator(name = "aluno_sequence_generator", sequenceName = "aluno_id_seq", allocationSize = 1)
    private Long codigo;
    private String nome;
    private Long cpf;
    private char sexo;
    private boolean atestado;
    private String endereco;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimento;
    
    @ManyToOne
    private Professor instrutor;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public boolean isAtestado() {
        return atestado;
    }

    public void setAtestado(boolean atestado) {
        this.atestado = atestado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 

    public Professor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Professor instrutor) {
        this.instrutor = instrutor;
    }
    
    public Aluno(){
        
    }
    public Aluno(String nome, Long cpf, char sexo, boolean atestado, String endereco, Date nascimento, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.atestado = atestado;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.email = email;
        this.password = null;
        this.instrutor = null;
    }
    
    
    
}
