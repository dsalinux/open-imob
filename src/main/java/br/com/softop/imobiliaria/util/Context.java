/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softop.imobiliaria.util;

import br.com.softop.imobiliaria.entity.Login;
import br.com.softop.imobiliaria.entity.vo.ImovelVO;
import javax.faces.context.FacesContext;

/**
 *
 * @author danilo
 */
public class Context {
    
    private static final String USUARIO_LOGADO = "usuario_logado";
    private static ImovelVO imovelConsulta;
    
    public static Login getLogin(){
        Object l = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USUARIO_LOGADO);
        if(l == null){
            return null;
        }
        Login login = (Login) l;
        return login;
    }
    public static void setLogin(Login login){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USUARIO_LOGADO, login);
    }

    public static ImovelVO getImovelConsulta() {
        return imovelConsulta;
    }
    public static void setImovelConsulta(ImovelVO imovelConsulta) {
        Context.imovelConsulta = imovelConsulta;
    }
    
}
