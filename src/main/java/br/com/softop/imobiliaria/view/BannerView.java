package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Banner;
import br.com.softop.imobiliaria.logic.impl.BannerLogicImpl;
import br.com.softop.imobiliaria.util.Constants;
import br.com.softop.imobiliaria.util.FileUtil;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
@SessionScoped
public class BannerView extends GenericBean<Banner, BannerLogicImpl> {

    private String fotoSelecionada;
    private String fotoAnterior;
    private int cont = 1;

    public void ativarDesativar(Banner entity){
        try {
            if(entity.getDataDesativacao() == null){
                entity.setDataDesativacao(new Date());
            } else {
                entity.setDataDesativacao(null);
            }
            super.save(entity);
        }catch(BusinessException ex){
            addMessageWarn(ex);
        } catch (Exception ex) {
            Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, ex);
            addMessageFatal("Desculpe, ocorreu um erro no sistema!");
        }
    }
    
    @Override
    public void newRegistre() {
        if (!StringHelper.isEmpty(fotoSelecionada)) {
            File apagar = new File(fotoSelecionada);
            if (apagar.delete()) {
                Logger.getLogger(BannerView.class.getName()).log(Level.WARNING, null, "Arquivo " + fotoSelecionada + " deletado!");
            } else {
                Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + fotoSelecionada);
            }
        }
        fotoAnterior = null;
        fotoSelecionada = null;
        super.newRegistre();
    }

    @Override
    public void save() {
        super.save();
        if (getEntity() != null && StringHelper.isEmpty(getEntity().getUrlImagem()) && !StringHelper.isEmpty(fotoAnterior) && !fotoAnterior.equals(fotoSelecionada)) {
            File apagar = new File(fotoAnterior);
            if (apagar.delete()) {
                Logger.getLogger(BannerView.class.getName()).log(Level.WARNING, null, "Arquivo " + fotoAnterior + " deletado!");
            } else {
                Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + fotoAnterior);
            }
        }
        fotoAnterior = null;
        fotoSelecionada = null;
    }
    
    public void delete(Banner banner){
        setEntity(banner);
        this.delete();
    }

    @Override
    public void delete() {
        super.delete();
        if (!StringHelper.isEmpty(fotoSelecionada)) {
            File apagar = new File(fotoSelecionada);
            if (apagar.delete()) {
                Logger.getLogger(BannerView.class.getName()).log(Level.WARNING, null, "Arquivo " + fotoSelecionada + " deletado!");
            } else {
                Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + fotoSelecionada);
            }
        }
        fotoAnterior = null;
        fotoSelecionada = null;
    }

    @Override
    public void search() {
        if (!StringHelper.isEmpty(fotoSelecionada)) {
            File apagar = new File(fotoSelecionada);
            if (apagar.delete()) {
                Logger.getLogger(BannerView.class.getName()).log(Level.WARNING, null, "Arquivo " + fotoSelecionada + " deletado!");
            } else {
                Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + fotoSelecionada);
            }
        }
        fotoAnterior = null;
        fotoSelecionada = null;
        super.search();
    }

    public void uploadImagem(FileUploadEvent event) {
        if (CurrentState.EDIT.equals(getCurrentState()) && !StringHelper.isEmpty(getEntity().getUrlImagem())) {
            fotoAnterior = getEntity().getUrlImagem();
        }
        if (!StringHelper.isEmpty(fotoSelecionada)) {
            File apagar = new File(fotoSelecionada);
            if (apagar.delete()) {
                Logger.getLogger(BannerView.class.getName()).log(Level.WARNING, null, "Arquivo " + fotoSelecionada + " deletado!");
            } else {
                Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + fotoSelecionada);
            }
            fotoSelecionada = null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(new Date().getTime());
        builder.append(cont++);
        builder.append(event.getFile().getFileName());
        File arquivo = FileUtil.createFileHomeUser(builder.toString(), Constants.CLIENTE_NAME + "_banner", event.getFile().getContents());
        getEntity().setUrlImagem(arquivo.getAbsolutePath());
        fotoSelecionada = getEntity().getUrlImagem();
    }
}
