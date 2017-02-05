package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import br.com.softop.imobiliaria.logic.ImovelLogic;
import br.com.softop.imobiliaria.logic.TipoImovelLogic;
import br.com.softop.imobiliaria.logic.impl.ImovelLogicImpl;
import br.com.softop.imobiliaria.logic.impl.TipoImovelLogicImpl;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
//@Controller
@SessionScoped
@ManagedBean
public class ImoveisView extends JSFUtil {

    private List<Imovel> entitys;
    //Campos para filtragem!
    private ImovelVO imovelVO;
    //Lista dos combos
    private List<TipoImovel> tipoImovelList;
    private List<String> cidades;
    private List<String> bairros;
    private ImovelLogic imovelLogic;
    private TipoImovelLogic tipoImovelLogic;
    
    private long ultimaConsultaTipoImovel;
    private long ultimaConsultaCidades;
    private long tempoEntreConsultas = 30000;

    public ImoveisView() {
        imovelVO = new ImovelVO();
        ultimaConsultaCidades = new Date().getTime()-tempoEntreConsultas;
        ultimaConsultaTipoImovel = new Date().getTime()-tempoEntreConsultas;
//        imovelVO.setTipo_(Imovel.Tipo.ALUGUEL);
    }

    @PostConstruct
    public void init() {
        try {
            imovelLogic = new ImovelLogicImpl();
            tipoImovelLogic = new TipoImovelLogicImpl();
            
            tipoImovelList = new TipoImovelLogicImpl().find(new TipoImovel());
            buscar();
        } catch (BusinessException ex) {
            Logger.getLogger(ImoveisView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public void alterarBairro() {
        if (imovelVO.getCidade() != null) {
            String cidade = imovelVO.getCidade();
            if (!StringHelper.isEmpty(cidade)) {
                bairros = imovelLogic.buscarBairros(cidade);
            } else {
                bairros = null;
            }
        } else {
            bairros = null;
        }
    }
    
    public void selecionarTipoNegocio(String tipo){
        Imovel.Tipo tipoNegocio = null;
        if(!StringHelper.isEmpty(tipo)){
            tipoNegocio = Imovel.Tipo.valueOf(tipo);
        }
        imovelVO.setTipo_(tipoNegocio);
        buscar();
    }

    public void limparPesquisa() {
        bairros = null;
        imovelVO = new ImovelVO();
        buscar();
    }

    public List<Imovel> getImoveisDestaque(){
        return imovelLogic.buscarAtivosDestaque();
    }
    
    public void buscar() {
//        if (Context.getImovelConsulta() != null) {
//            ImovelVO i = Context.getImovelConsulta();
//            if (Imovel.Tipo.ALUGUEL.equals(i.getTipo_())) {
//                imovelVO = i;
//            }
//            Context.setImovelConsulta(null);
//        }
        entitys = imovelLogic.buscarAtivosPorFiltro(imovelVO);
    }

    public List<Imovel> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<Imovel> entitys) {
        this.entitys = entitys;
    }

    public ImovelVO getImovelVO() {
        return imovelVO;
    }

    public void setImovelVO(ImovelVO imovelVO) {
        this.imovelVO = imovelVO;
    }

    public List<String> getBairros() {
        return bairros;
    }

    public List<String> getCidades() {
        if((ultimaConsultaCidades+tempoEntreConsultas)<new Date().getTime()){
            cidades = imovelLogic.listarCidades();
            ultimaConsultaCidades = tempoParaProximaConsulta();
        }
        return cidades;
    }

    public List<TipoImovel> getTipoImovelList() {
        try {
            if((ultimaConsultaTipoImovel+tempoEntreConsultas)<new Date().getTime()){
                tipoImovelList = tipoImovelLogic.find(null);
                ultimaConsultaTipoImovel = tempoParaProximaConsulta();
            }
            return tipoImovelList;
        } catch (BusinessException ex) {
            addMessageWarn(ex);
            return new ArrayList<TipoImovel>();
        }
    }

    
    private long tempoParaProximaConsulta(){
        return new Date().getTime()+tempoEntreConsultas;
    }
}
