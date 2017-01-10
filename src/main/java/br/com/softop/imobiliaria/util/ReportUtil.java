package br.com.softop.imobiliaria.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author danilo
 */
public class ReportUtil {

    public static byte[] reportToPDF(List list, InputStream reportInputStream, HashMap<String, Object> map) throws Exception {
        try {
            JasperPrint jasperPrint;
            if(map == null){
                map = new HashMap<String, Object>();
            }
            JasperReport report = JasperCompileManager.compileReport(reportInputStream);
            jasperPrint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(list));
            byte[] rep = JasperExportManager.exportReportToPdf(jasperPrint);
            return rep;
        } catch (JRException e) {
            throw new Exception("Desculpe! Ocorreu um erro enquanto gerávamos seu Relatório!", e);
        } catch (Exception e) {
            throw new Exception("Desculpe! Ocorreu um erro interno ao gerar um relatório!", e);
        }
    }

}
