/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "configuracoes")
@XmlRootElement
public class Configuracoes implements Serializable {
    @Size(max = 50)
    @Column(name = "senha_envio")
    private String senhaEnvio;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email_envio")
    private String emailEnvio;
    @Size(max = 45)
    @Column(name = "emails_recebimento")
    private String emailsRecebimento;

    public Configuracoes() {
    }

    public Configuracoes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailEnvio() {
        return emailEnvio;
    }

    public void setEmailEnvio(String emailEnvio) {
        this.emailEnvio = emailEnvio;
    }

    public String getEmailsRecebimento() {
        return emailsRecebimento;
    }

    public void setEmailsRecebimento(String emailsRecebimento) {
        this.emailsRecebimento = emailsRecebimento;
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
        if (!(object instanceof Configuracoes)) {
            return false;
        }
        Configuracoes other = (Configuracoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.softop.projetobase.entity.Configuracoes[ id=" + id + " ]";
    }

    public String getSenhaEnvio() {
        return senhaEnvio;
    }

    public void setSenhaEnvio(String senhaEnvio) {
        this.senhaEnvio = senhaEnvio;
    }
    
}
