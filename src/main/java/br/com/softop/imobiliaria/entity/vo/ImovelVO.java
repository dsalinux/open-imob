/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.entity.vo;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.Imovel.Tipo;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author danilo
 */
public class ImovelVO implements Serializable {
    
    private Integer valorMinimo;
    private Integer valorMaximo;
    private Integer quartos;
    private Integer banheiros;
    private Integer cozinhas;
    private Integer salas;
    private Integer garagens;
    private String cidade;
    private String bairro;
    private Boolean guardaRoupas;
    private Boolean armariosCozinha;
    private Integer tipoImovel;
    private Imovel.Tipo tipo_;

    public Integer getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Integer valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public Integer getQuartos() {
        return quartos;
    }

    public void setQuartos(Integer quartos) {
        this.quartos = quartos;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getCozinhas() {
        return cozinhas;
    }

    public void setCozinhas(Integer cozinhas) {
        this.cozinhas = cozinhas;
    }

    public Integer getSalas() {
        return salas;
    }

    public void setSalas(Integer salas) {
        this.salas = salas;
    }

    public Integer getGaragens() {
        return garagens;
    }

    public void setGaragens(Integer garagens) {
        this.garagens = garagens;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Boolean getGuardaRoupas() {
        return guardaRoupas;
    }

    public void setGuardaRoupas(Boolean guardaRoupas) {
        this.guardaRoupas = guardaRoupas;
    }

    public Boolean getArmariosCozinha() {
        return armariosCozinha;
    }

    public void setArmariosCozinha(Boolean armariosCozinha) {
        this.armariosCozinha = armariosCozinha;
    }

    public Integer getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(Integer tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Tipo getTipo_() {
        return tipo_;
    }
    
    public Tipo[] getListaTipo(){
        return Tipo.values();
    }

    public void setTipo_(Tipo tipo) {
        this.tipo_ = tipo;
    }
    
    
    
}
