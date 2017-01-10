package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import br.com.softop.imobiliaria.logic.ImovelLogic;
import br.com.softop.imobiliaria.logic.impl.ImovelLogicImpl;
import br.com.softop.imobiliaria.logic.impl.TipoImovelLogicImpl;
import br.com.softop.imobiliaria.util.Context;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.springframework.stereotype.Controller;

/**
 *
 * @author danilo
 */
@Controller
@SessionScoped
@ManagedBean
public class AlugarView extends JSFUtil {

    private List<Imovel> entitys;
    //Campos para filtragem!
    private ImovelVO imovelVO;
    //Lista dos combos
    private List<TipoImovel> tipoImovelList;
    private List<String> cidades;
    private List<String> bairros;
    private ImovelLogic imovelLogic;

    public AlugarView() {
        imovelVO = new ImovelVO();
//        imovelVO.setTipo_(Imovel.Tipo.ALUGUEL);
    }

    @PostConstruct
    public void init() {
        try {
            imovelLogic = new ImovelLogicImpl();
            cidades = imovelLogic.listarCidades();
            tipoImovelList = new TipoImovelLogicImpl().find(new TipoImovel());
            buscar();
        } catch (BusinessException ex) {
            Logger.getLogger(AlugarView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarBairro(ValueChangeEvent changeEvent) {
        if (changeEvent.getNewValue() != null) {
            String cidade = changeEvent.getNewValue().toString();
            if (!StringHelper.isEmpty(cidade)) {
                bairros = imovelLogic.buscarBairros(cidade);
            } else {
                bairros = null;
            }
        } else {
            bairros = null;
        }
    }

    public void limparPesquisa() {
        bairros = null;
        imovelVO = new ImovelVO();
        buscar();
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
        return cidades;
    }

    public List<TipoImovel> getTipoImovelList() {
        return tipoImovelList;
    }
}
