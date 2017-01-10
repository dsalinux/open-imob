/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.entity;

import br.com.softop.imobiliaria.util.StringHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "imovel")
public class Imovel implements Serializable {
    
    public enum Tipo {
        ALUGUEL("Aluguar"),
        VENDA("Comprar");

        private String descricao;
        
        Tipo(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
        
        public String getName(){
            return name();
        }
        
    }
    
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quartos")
    private int quartos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salas")
    private int salas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "banheiros")
    private int banheiros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "garagem")
    private int garagem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "armarios_embutidos")
    private boolean armariosEmbutidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guardaroupas_embutidos")
    private boolean guardaroupasEmbutidos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private Float valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Column(name = "data_desativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesativacao;
    @Basic(optional = false)
    @JoinColumn(name = "tipo_imovel_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoImovel tipoImovel;
    @Enumerated(EnumType.STRING)
    @Column(name="tipo")
    private Tipo tipo_ = Tipo.ALUGUEL;
    @Size(max = 100)
    @Column(name = "logradrouro")
    private String logradrouro;
    @Size(max = 20)
    @Column(name = "numero")
    private String numero;
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 100)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 9)
    @Column(name = "cep")
    private String cep;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    private List<Foto> fotoList;
    @Transient
    private List<Foto> fotoListRemover;
    @Column(name="area")
    private String area;
    @Column(name="imovel_destaque")
    private boolean imovelDestaque;

    public Imovel() {
    }

    public Imovel(Integer id) {
        this.id = id;
    }

    public Imovel(Integer id, String descricao, int quartos, int salas, int banheiros, int garagem, boolean armariosEmbutidos, boolean guardaroupasEmbutidos, Float valor, Date dataCadastro) {
        this.id = id;
        this.descricao = descricao;
        this.quartos = quartos;
        this.salas = salas;
        this.banheiros = banheiros;
        this.garagem = garagem;
        this.armariosEmbutidos = armariosEmbutidos;
        this.guardaroupasEmbutidos = guardaroupasEmbutidos;
        this.valor = valor;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuartos() {
        return quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getSalas() {
        return salas;
    }

    public void setSalas(int salas) {
        this.salas = salas;
    }

    public int getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(int banheiros) {
        this.banheiros = banheiros;
    }

    public int getGaragem() {
        return garagem;
    }

    public void setGaragem(int garagem) {
        this.garagem = garagem;
    }

    public boolean getArmariosEmbutidos() {
        return armariosEmbutidos;
    }

    public void setArmariosEmbutidos(boolean armariosEmbutidos) {
        this.armariosEmbutidos = armariosEmbutidos;
    }

    public boolean getGuardaroupasEmbutidos() {
        return guardaroupasEmbutidos;
    }

    public void setGuardaroupasEmbutidos(boolean guardaroupasEmbitidos) {
        this.guardaroupasEmbutidos = guardaroupasEmbitidos;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public boolean isImovelDestaque() {
        return imovelDestaque;
    }

    public void setImovelDestaque(boolean imovelDestaque) {
        this.imovelDestaque = imovelDestaque;
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
        if (!(object instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.softop.projetobase.entity.Imovel[ id=" + id + " ]";
    }

    public List<Foto> getFotoList() {
        return fotoList;
    }

    public void setFotoList(List<Foto> fotoList) {
        this.fotoList = fotoList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public String getLogradrouro() {
        return logradrouro;
    }

    public void setLogradrouro(String logradrouro) {
        this.logradrouro = logradrouro;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Tipo getTipo_() {
        return tipo_;
    }
    public void setTipo_(Tipo tipo_) {
        this.tipo_ = tipo_;
    }

    public List<Foto> getFotoListRemover() {
        return fotoListRemover;
    }

    public void setFotoListRemover(List<Foto> fotoListRemover) {
        this.fotoListRemover = fotoListRemover;
    }
    
    @Transient
    public String getUrlFotoPrincipal(){
        if(fotoList != null && !fotoList.isEmpty()){
            return fotoList.get(0).getUrl();
        }
        return "";
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    @Transient
    public String getDescricaoCurta(){
        String desc = descricao;
        if(!StringHelper.isEmpty(desc) && desc.length() > 60){
            desc = desc.substring(0, 57);
            desc += "...";
        }
        return desc;
    }
    
}
