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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "configuracoes")
@Cache(usage = CacheConcurrencyStrategy.NONE)
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
    @Size(max = 50)
    @Column(name = "nome_imobiliaria")
    private String nomeImobiliaria;
    @Size(max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 16)
    @Column(name = "numero")
    private String numero;
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 80)
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    @Size(max = 80)
    private String cidade;
    @Column(name = "uf")
    @Size(max = 2)
    private String uf;
    @Column(name = "cep")
    @Size(max = 10)
    private String cep;
    @Size(max = 20)
    @Column(name = "fone1")
    private String fone1;
    @Size(max = 20)
    @Column(name = "fone2")
    private String fone2;
    @Size(max = 20)
    @Column(name = "fone3")
    private String fone3;
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Max(400)
    @Column(name = "logo")
    private String logo;
    @ManyToOne
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private Theme theme;

    public Configuracoes() {
    }

    public String getNomeImobiliaria() {
        return nomeImobiliaria;
    }

    public void setNomeImobiliaria(String nomeImobiliaria) {
        this.nomeImobiliaria = nomeImobiliaria;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getFone3() {
        return fone3;
    }

    public void setFone3(String fone3) {
        this.fone3 = fone3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
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
