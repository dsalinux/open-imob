package br.com.softop.imobiliaria.util;

import br.com.softop.imobiliaria.persistence.dao.BannerDAO;
import br.com.softop.imobiliaria.persistence.dao.ClienteDAO;
import br.com.softop.imobiliaria.persistence.dao.ConfiguracoesDAO;
import br.com.softop.imobiliaria.persistence.dao.FotoDAO;
import br.com.softop.imobiliaria.persistence.dao.ImovelDAO;
import br.com.softop.imobiliaria.persistence.dao.InquilinoDAO;
import br.com.softop.imobiliaria.persistence.dao.LoginDAO;
import br.com.softop.imobiliaria.persistence.dao.OrientacoesDAO;
import br.com.softop.imobiliaria.persistence.dao.SobreDAO;
import br.com.softop.imobiliaria.persistence.dao.ThemeDAO;
import br.com.softop.imobiliaria.persistence.dao.TipoImovelDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateBannerDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateClienteDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateConfiguracoesDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateFotoDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateImovelDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateInquilinoDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateLoginDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateOrientacoesDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateSobreDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateThemeDAO;
import br.com.softop.imobiliaria.persistence.dao.hibernate.HibernateTipoImovelDAO;

/**
 *
 * @author danilo
 */
public class ContextDAO {
    
    private static LoginDAO loginDAO;
    private static ClienteDAO clienteDAO;
    private static TipoImovelDAO tipoImovelDAO;
    private static ConfiguracoesDAO configuracoesDAO;
    private static BannerDAO bannerDAO;
    private static ImovelDAO imovelDAO;
    private static FotoDAO fotoDAO;
    private static OrientacoesDAO orientacoesDAO;
    private static InquilinoDAO inquilinoDAO;
    private static ThemeDAO themeDAO;
    private static SobreDAO sobreDAO;
    
    public static LoginDAO getLoginDAO() {
        if(loginDAO == null){
            loginDAO = new HibernateLoginDAO();
        }
        return loginDAO;
    }
    
    public static ClienteDAO getClienteDAO(){
        if(clienteDAO == null){
            clienteDAO = new HibernateClienteDAO();
        }
        return clienteDAO;
    }

    public static TipoImovelDAO getTipoImovelDAO() {
        if(tipoImovelDAO == null){
            tipoImovelDAO = new HibernateTipoImovelDAO();
        }
        return tipoImovelDAO;
    }

    public static ConfiguracoesDAO getConfiguracoesDAO() {
        if(configuracoesDAO == null){
            configuracoesDAO = new HibernateConfiguracoesDAO();
        }
        return configuracoesDAO;
    }

    public static BannerDAO getBannerDAO() {
        if(bannerDAO == null){
            bannerDAO = new HibernateBannerDAO();
        }
        return bannerDAO;
    }
    
    public static ImovelDAO getImovelDAO(){
        if(imovelDAO == null){
            imovelDAO = new HibernateImovelDAO();
        }
        return imovelDAO;
    }
    
    public static FotoDAO getFotoDAO(){
        if(fotoDAO == null){
            fotoDAO = new HibernateFotoDAO();
        }
        return fotoDAO;
    }
    
    public static OrientacoesDAO getOrientacoesDAO(){
        if(orientacoesDAO == null){
            orientacoesDAO = new HibernateOrientacoesDAO();
        }
        return orientacoesDAO;
    }

    public static InquilinoDAO getInquilinoDAO() {
        if(inquilinoDAO == null){
            inquilinoDAO = new HibernateInquilinoDAO();
        }
        return inquilinoDAO;
    }

    public static ThemeDAO getThemeDAO() {
        if(themeDAO == null){
            themeDAO = new HibernateThemeDAO();
        }
        return themeDAO;
    }
    public static SobreDAO getSobreDAO() {
        if(sobreDAO == null){
            sobreDAO = new HibernateSobreDAO();
        }
        return sobreDAO;
    }
    
    
}
