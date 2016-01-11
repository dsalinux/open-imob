/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "login")
@XmlRootElement
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
        
    public enum Permissao {
        ADMIN("Administrador"),
        USER("Usuario");
        
        private String descricao;
        
        Permissao(String descricao){
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
        
        public String getName(){
            return name();
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha")
    private String senha;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Column(name = "data_desativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesativacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Size(max = 30)
    @Column(name = "tema")
    private String tema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alterar_senha")
    private boolean alterarSenha;
    @Enumerated(EnumType.STRING)
    @Column(name="permissao")
    private Permissao permissao_;

    public Login() {
    }

    public Login(Integer id) {
        this.id = id;
    }

    public Login(Integer id, String login, String senha, String email, Date dataCadastro, boolean alterarSenha, Permissao permissao) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.alterarSenha = alterarSenha;
        this.permissao_ = permissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean getAlterarSenha() {
        return alterarSenha;
    }

    public void setAlterarSenha(boolean alterarSenha) {
        this.alterarSenha = alterarSenha;
    }

    public Permissao getPermissao_() {
        return permissao_;
    }

    public void setPermissao_(Permissao permissao_) {
        this.permissao_ = permissao_;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.softop.projetobase.entity.Login[ id=" + id + " ]";
    }
    
}
