package br.com.softop.imobiliaria.view;

import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@RequestScoped
public class Teste {
    
    public void teste(){
        long tempo = Calendar.getInstance().getTimeInMillis();
        tempo += 3000;
        while(tempo > Calendar.getInstance().getTimeInMillis()){ }
    }
    
    public String getMsgTeste(){
        long tempo = Calendar.getInstance().getTimeInMillis();
        tempo += 3000;
        while(tempo > Calendar.getInstance().getTimeInMillis()){ }
        return "Testado";
    }
}
