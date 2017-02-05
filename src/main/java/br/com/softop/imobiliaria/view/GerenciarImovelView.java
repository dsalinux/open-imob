package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Cliente;
import br.com.softop.imobiliaria.entity.Foto;
import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.TipoImovel;
import br.com.softop.imobiliaria.logic.impl.ClienteLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ImovelLogicImpl;
import br.com.softop.imobiliaria.logic.impl.TipoImovelLogicImpl;
import br.com.softop.imobiliaria.util.FileUtil;
import br.com.softop.imobiliaria.util.ImagemUtil;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@SessionScoped
public class GerenciarImovelView extends GenericBean<Imovel, ImovelLogicImpl> {

    private List<TipoImovel> tipoImovelList;
    private List<Cliente> clienteList;
    private int cont;

    @PostConstruct
    public void init() {
        try {
            tipoImovelList = new TipoImovelLogicImpl().find(new TipoImovel());
            clienteList = new ClienteLogicImpl().find(new Cliente());
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        }
    }
    
    public void ativarDesativar(){
        if(getEntity().getDataDesativacao() == null){
            getEntity().setDataDesativacao(new Date());
            addMessage(FacesMessage.SEVERITY_INFO, "Desativado, não esqueça de salvar!");
        } else {
            getEntity().setDataDesativacao(null);
            addMessage(FacesMessage.SEVERITY_INFO, "Ativado, não esqueça de salvar!");
        }
    }
    
    public void marcarDesmarcarDestaque(){
        marcarDesmarcarDestaque(getEntity());
    }
    
    public void marcarDesmarcarDestaque(Imovel imovel){
        try {
            imovel = (Imovel) getGenericLogic().findById(imovel.getId());
            imovel.setImovelDestaque(!imovel.isImovelDestaque());
            imovel = save(imovel);
            int posicao = getEntitys().indexOf(imovel);
            getEntitys().remove(posicao);
            getEntitys().add(posicao, imovel);
            addMessageInfo(imovel.isImovelDestaque()?"Imóvel colocado como destaque!":"Imóvel removido do destaque!");
        } catch (BusinessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Desculpe, mas parece que ocorreu um erro na aplicação!\n"+ex.getMessage()));
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> completeCidade(String query){
        addMessage(FacesMessage.SEVERITY_INFO, getEntity().getCidade());
        List<String> valores = getLogic().listarCidades(query);
        return valores;
    }
    
    public List<String> completeBairro(String query){
        List<String> valores = getLogic().buscarBairros(getEntity().getCidade(),query);
        return valores;
    }

    public Converter getTipoImovelConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (TipoImovel tipoImovel : tipoImovelList) {
                        if (tipoImovel.getId().equals(id)) {
                            return tipoImovel;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                TipoImovel tipoImovel = null;
                if (o != null) {
                    tipoImovel = (TipoImovel) o;
                }
                return tipoImovel.getId() + "";
            }
        };
    }

    public Converter getClienteConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Cliente cliente : clienteList) {
                        if (cliente.getId().equals(id)) {
                            return cliente;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Cliente cliente = null;
                if (o != null) {
                    cliente = (Cliente) o;
                }
                return cliente.getId() + "";
            }
        };
    }
    public void removerImagem(Foto foto){
        if(getEntity().getFotoListRemover() == null){
            getEntity().setFotoListRemover(new ArrayList<Foto>());
        }
        getEntity().getFotoList().remove(foto);
        getEntity().getFotoListRemover().add(foto);
        addMessageInfo("Foto incluida para deletar. Foto será deletada quando clicar em salvar!");
    }
    public void uploadImagem(FileUploadEvent event) {
        StringBuilder img = new StringBuilder();
        img.append(new Date().getTime());
        img.append(cont++);
        img.append(event.getFile().getFileName());
        File imagem = FileUtil.createFileHomeUser(img.toString(), "/imoveis", event.getFile().getContents());
        ImagemUtil.diminuirImagemProporcialmente(imagem, 640, 640);
        img.append("mini");
        File mini = FileUtil.createFileHomeUser(img.toString(), "/imoveis", event.getFile().getContents());
        ImagemUtil.diminuirImagemProporcialmente(mini, 200, 200);
        Foto foto = new Foto(null, imagem.getAbsolutePath(), event.getFile().getContentType());
        if(getEntity().getFotoList() == null){
            getEntity().setFotoList(new ArrayList<Foto>());
        }
        getEntity().getFotoList().add(foto);
    }

    public List<TipoImovel> getTipoImovelList() {
        return tipoImovelList;
    }

    public void setTipoImovelList(List<TipoImovel> tipoImovelList) {
        this.tipoImovelList = tipoImovelList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
}
