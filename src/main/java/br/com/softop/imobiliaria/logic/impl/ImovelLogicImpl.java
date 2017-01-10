package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Foto;
import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import br.com.softop.imobiliaria.logic.ImovelLogic;
import br.com.softop.imobiliaria.util.ContextDAO;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

/**
 *
 * @author danilo
 */
@Service
public class ImovelLogicImpl implements ImovelLogic {

    @Override
    public Imovel save(Imovel entity) throws BusinessException {
        if(StringHelper.isEmpty(entity.getDescricao())){
            throw new BusinessException("Por favor informe a descrição!");
        }
        if(entity.getTipo_() == null){
            throw new BusinessException("Por favor informe o tipo (Aluguel ou Venda)!");
        }
        if(entity.getTipoImovel() == null){
            throw new BusinessException("Por favor informe o tipo de imóvel!");
        }
        List<Foto> fotosParaSalvar = null;
        List<Foto> fotosParaDeletar = entity.getFotoListRemover();
        if(entity.getFotoList() != null){
             fotosParaSalvar = entity.getFotoList();
             entity.setFotoList(new ArrayList<Foto>());
        }
        if(entity.getDataCadastro() == null){
            entity.setDataCadastro(new Date());
        }
        entity = ContextDAO.getImovelDAO().save(entity);
        if(fotosParaSalvar != null){
            for (Foto foto : fotosParaSalvar) {
                foto.setImovel(entity);
                ContextDAO.getFotoDAO().save(foto);
            }
        }
        if(fotosParaDeletar != null){
            for (Foto foto : fotosParaDeletar) {
                String arquivoDeletar = foto.getUrl();
                if(foto.getId() != null){
                    foto = ContextDAO.getFotoDAO().findById(foto.getId());
                    ContextDAO.getFotoDAO().delete(foto);
                }
                new File(arquivoDeletar).delete();
                new File(arquivoDeletar+"mini").delete();
            }
        }
        return entity;
    }

    @Override
    public void delete(Imovel entity) throws BusinessException {
        entity = findById(entity.getId());
        ContextDAO.getImovelDAO().delete(entity);
    }

    @Override
    public Imovel findById(Integer id) {
        Imovel imovel = ContextDAO.getImovelDAO().findById(id);
        return imovel;
    }

    @Override
    public List<Imovel> find(Imovel entity) throws BusinessException {
        return ContextDAO.getImovelDAO().findAll();
    }
    
    @Override
    public List<String> listarCidades(){
        return ContextDAO.getImovelDAO().buscarCidades(null);
    }
    
    @Override
    public List<String> listarCidades(String cidade){
        return ContextDAO.getImovelDAO().buscarCidades(cidade);
    }
    
    @Override
    public List<Imovel> buscarAtivosPorFiltro(ImovelVO imovelVO){
        return ContextDAO.getImovelDAO().buscarAtivosPorFiltro(imovelVO);
    }
    
    @Override
    public List<String> buscarBairros(String cidade){
        return ContextDAO.getImovelDAO().buscarBairros(cidade, null);
    }
    
    @Override
    public List<String> buscarBairros(String cidade, String bairro){
        return ContextDAO.getImovelDAO().buscarBairros(cidade, bairro);
    }

    @Override
    public List<Imovel> buscarAtivosDestaque() {
        List<Imovel> imoveis = ContextDAO.getImovelDAO().buscarAtivosDestaquer();
        return imoveis;
    }

    @Override
    public Imovel findById(Imovel entity) {
        return findById(entity.getId());
    }
    
}
