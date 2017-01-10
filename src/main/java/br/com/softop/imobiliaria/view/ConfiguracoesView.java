package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.logic.ConfiguracoesLogic;
import br.com.softop.imobiliaria.logic.impl.ConfiguracoesLogicImpl;
import br.com.softop.imobiliaria.logic.impl.ContatoLogicImpl;
import br.com.softop.imobiliaria.util.Constants;
import br.com.softop.imobiliaria.util.FileUtil;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.util.Date;
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
import org.springframework.stereotype.Controller;

/**
 *
 * @author danilo
 */
@Controller
@ManagedBean
@ApplicationScoped
public class ConfiguracoesView extends JSFUtil {

    public Configuracoes configuracoes;
    public ConfiguracoesLogic configuracoesLogic;

    public long ultimaConsulta = 0;
    public long tempoEntreConsultas = 20000;
    
    @PostConstruct
    public void init() {
        configuracoesLogic = new ConfiguracoesLogicImpl();
    }

    
    public Configuracoes getConfiguracoes() {
        if(ultimaConsulta < new Date().getTime()){
            ultimaConsulta = new Date().getTime() + tempoEntreConsultas;
            configuracoes = configuracoesLogic.recupararConfiguracoes();
        }
        return configuracoes;
    }

    public void setConfiguracoes(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
    }


}
