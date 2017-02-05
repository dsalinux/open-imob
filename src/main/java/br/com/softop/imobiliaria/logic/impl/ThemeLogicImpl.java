package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Theme;
import br.com.softop.imobiliaria.logic.ThemeLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.util.List;

/**
 *
 * @author danilo
 */
public class ThemeLogicImpl implements ThemeLogic {

    @Override
    public Theme save(Theme entity) throws BusinessException {
        validarDados(entity);
        return ContextDAO.getThemeDAO().save(entity);

    }

    private void validarDados(Theme entity) throws BusinessException {
        if (StringHelper.isEmpty(entity.getHeaderBackground())) {
            throw new BusinessException("Informe a Cor de Fundo do Cabeçalho!");
        }
        if (StringHelper.isEmpty(entity.getHeaderBackgroundHover())) {
            throw new BusinessException("Informe a Cor de Destaque do Cabeçalho!");
        }
        if (StringHelper.isEmpty(entity.getFooterTextColor())) {
            throw new BusinessException("Informe a Cor do Texto do Cabeçalho!");
        }
        if (StringHelper.isEmpty(entity.getFooterBackground())) {
            throw new BusinessException("Informe a Cor de Fundo do Rodapé!");
        }
        if (StringHelper.isEmpty(entity.getFooterBorderColor())) {
            throw new BusinessException("Informe a Cor da Borda do Rodapé!");
        }
        if (StringHelper.isEmpty(entity.getFooterTextColor())) {
            throw new BusinessException("Informe a Cor do Texto do Rodapé!");
        }

        
    }

    @Override
    public void delete(Theme entity) throws BusinessException {
        entity = findById(entity.getId());
        try {
            ContextDAO.getThemeDAO().delete(entity);
        } catch (Exception e) {
            throw new BusinessException("Este tema pode estar em uso, verifique ou entre em contato com o adiministrador!");
        }
    }

    @Override
    public Theme findById(Integer id) {
        return ContextDAO.getThemeDAO().findById(id);
    }

    @Override
    public List<Theme> find(Theme entity) throws BusinessException {
        return ContextDAO.getThemeDAO().findAll();
    }

    @Override
    public Theme findById(Theme entity) {
        return findById(entity.getId());
    }
    
}
