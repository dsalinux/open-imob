package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.entity.Theme;
import br.com.softop.imobiliaria.logic.ConfiguracoesLogic;
import br.com.softop.imobiliaria.logic.impl.ConfiguracoesLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.util.Constants;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.FileUtil;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@ApplicationScoped
public class GerenciarConfiguracoesView extends JSFUtil {

    public Configuracoes configuracoes;
    public ConfiguracoesLogic configuracoesLogic;
    public String senha;
    private MapModel mapaImobiliariaModel;
    private String imagemApagar = null;

    @PostConstruct
    public void init() {
        configuracoesLogic = new ConfiguracoesLogicImpl();
        configuracoes = configuracoesLogic.recupararConfiguracoes();

        mapaImobiliariaModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(configuracoes.getLatitude(), configuracoes.getLongitude());
        Marker imobiliaria = new Marker(coord1, "Imobiliária");
        imobiliaria.setDraggable(true);
        mapaImobiliariaModel.addOverlay(imobiliaria);
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        LatLng latLng = event.getMarker().getLatlng();
        configuracoes.setLatitude(latLng.getLat());
        configuracoes.setLongitude(latLng.getLng());
    }

    public void testarEnvioDeEmail() {
        try {
            new ContatoLogicImpl().testarEnvioDeEmail(configuracoes);
            addMessage(FacesMessage.SEVERITY_INFO, "Email teste enviado! Confira o recebimento do email de teste com os destinatários!\n Não esqueça de salvar as configurações!");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu erro no envio: " + e.getMessage());
        }
    }

    public void salvar() {
        try {
            if (!StringHelper.isEmpty(senha)) {
                configuracoes.setSenhaEnvio(senha);
            }
            if(imagemApagar != null){
                File f = new File(imagemApagar);
                f.delete();
                imagemApagar = null;
            }
            configuracoes = configuracoesLogic.salvar(configuracoes);
            addMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Parece que nossos servidores estão com algum problema! Aguarde alguns instantes e tente novamente!");
            Logger.getLogger(GerenciarConfiguracoesView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void atualizar() {
        configuracoes = null;
        configuracoes = configuracoesLogic.recupararConfiguracoes();
        if (configuracoes == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar as configurções! Entre em contato com o administrador ou tente novamente em alguns instantes.");
        } else {
            mapaImobiliariaModel = new DefaultMapModel();
            LatLng coord1 = new LatLng(configuracoes.getLatitude(), configuracoes.getLongitude());
            Marker imobiliaria = new Marker(coord1, "Imobiliária");
            imobiliaria.setDraggable(true);
            mapaImobiliariaModel.addOverlay(imobiliaria);
        }
    }
    
    public void selecionarTheme(Theme theme){
        configuracoes.setTheme(theme);
    }

     public void uploadImagem(FileUploadEvent event) {
         if(!StringHelper.isEmpty(configuracoes.getLogo())){
            imagemApagar = configuracoes.getLogo();
         }
        StringBuilder builder = new StringBuilder();
        builder.append(new Date().getTime());
        builder.append(event.getFile().getFileName());
        File arquivo = FileUtil.createFileHomeUser(builder.toString(), Constants.CLIENTE_NAME + "_logo", event.getFile().getContents());
        configuracoes.setLogo(arquivo.getAbsolutePath());
    }
    
     public List<Theme> getThemes(){
         return ContextDAO.getThemeDAO().findAll();
     }
     
    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public MapModel getMapaImobiliariaModel() {
        return mapaImobiliariaModel;
    }

}
