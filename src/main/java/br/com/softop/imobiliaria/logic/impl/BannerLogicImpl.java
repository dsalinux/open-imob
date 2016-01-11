package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Banner;
import br.com.softop.imobiliaria.logic.BannerLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import br.com.softop.imobiliaria.view.BannerView;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danilo
 */
public class BannerLogicImpl implements BannerLogic {

    @Override
    public Banner save(Banner entity) throws BusinessException {
        if(StringHelper.isEmpty(entity.getUrlImagem())){
            throw new BusinessException("Por favor selecione a imagem!");
        }
        return ContextDAO.getBannerDAO().save(entity);
    }

    @Override
    public void delete(Banner entity) throws BusinessException {
        entity = findById(entity.getId());
        File imgDel = new File(entity.getUrlImagem());
        if (!imgDel.delete()) {
            Logger.getLogger(BannerView.class.getName()).log(Level.SEVERE, null, "Erro ao deletar o arquivo " + imgDel.getName());
        }
        ContextDAO.getBannerDAO().delete(entity);
    }

    @Override
    public Banner findById(Integer id) {
        return ContextDAO.getBannerDAO().findById(id);
    }

    @Override
    public List<Banner> find(Banner entity) throws BusinessException {
        return ContextDAO.getBannerDAO().findAll();
    }

    @Override
    public List<Banner> buscarAtivos() {
        return ContextDAO.getBannerDAO().buscarAtivos();
    }
    
}
