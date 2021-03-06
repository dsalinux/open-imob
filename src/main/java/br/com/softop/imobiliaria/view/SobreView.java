package br.com.softop.imobiliaria.view;

import br.com.softop.imobiliaria.entity.Sobre;
import br.com.softop.imobiliaria.logic.SobreLogic;
import br.com.softop.imobiliaria.logic.impl.SobreLogicImpl;
import br.com.softop.imobiliaria.util.FileUtil;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author danilo
 */
//@Controller
@ManagedBean
@SessionScoped
public class SobreView extends JSFUtil {

    private Sobre sobre;
    private SobreLogic sobreLogic;
    private int cont = 0;
    private TreeNode selecionado;
    private File arquivoVisualizado;
    private String novoNome;

    @PostConstruct
    public void init() {
        sobreLogic = new SobreLogicImpl();
        sobre = sobreLogic.recupararSobre();
    }

    public void salvar() {
        try {
            sobre = sobreLogic.salvar(sobre);
            addMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");
        } catch (BusinessException ex) {
            addMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Parece que nossos servidores estão com algum problema! Aguarde alguns instantes e tente novamente!");
            Logger.getLogger(SobreView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void atualizar() {
        sobre = sobreLogic.recupararSobre();
        if (sobre == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar as orientações! Entre em contato com o administrador ou tente novamente em alguns instantes.");
        }
    }

    public void uploadArquivo(FileUploadEvent event) {

        File homeCliente = FileUtil.getHomeCliente();

        File dirSobre = new File(homeCliente, "sobre");
        if (!dirSobre.exists()) {
            dirSobre.mkdir();
        }

        //List<File> arquivos = Arrays.asList(dirSobre.listFiles());

        OutputStream os = null;
        File parentArquivo = new File(dirSobre, new Date().getTime() + "" + cont);
        parentArquivo.mkdir();
        try {
            File arquivo = new File(parentArquivo, event.getFile().getFileName());
            os = new FileOutputStream(arquivo);
            os.write(event.getFile().getContents());
            os.flush();
            os.close();
        } catch (IOException ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar o arquivo!");
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException ex) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar o arquivo!");
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getTextoComLinks() {
        StringBuilder texto = new StringBuilder(sobre.getTexto());
        int i = 0;
        while (true) {
            int p = texto.indexOf("$file{", i);
            if (i < p) {
                int f = texto.indexOf("}", p) + 1;
                String codigo = texto.substring(p, f);
                codigo = codigo.replace("$file{", "");
                codigo = codigo.replace("}", "");
                StringBuilder link = new StringBuilder();
                File homeCliente = FileUtil.getHomeCliente();
                File dirSobre = new File(homeCliente, "sobre");
                File dirArq = new File(dirSobre, codigo);
                if(dirArq.exists()){
                    if(dirArq.listFiles() != null && dirArq.listFiles().length > 0){
                        File arquivo = dirArq.listFiles()[0];
                        link.append(" <a href=\"fileServlet?fileName=");
                        try {
                            link.append(arquivo.getCanonicalPath().replaceAll(" ", "&nbsp;"));
                        } catch (IOException ex) {
                            Logger.getLogger(SobreView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        link.append("\">");
                        link.append(arquivo.getName());
                        link.append("</a> ");
                    }
                }
                texto.replace(p, f, link.toString());
                i = f;
            } else {
                break;
            }
        }

        return texto.toString();
    }

    public TreeNode getListaArquivos() {

        File homeCliente = FileUtil.getHomeCliente();

        File dirSobre = new File(homeCliente, "sobre");
        if (!dirSobre.exists()) {
            dirSobre.mkdir();
        }

        TreeNode root = new DefaultTreeNode("root", null);
        root.setExpanded(true);
        criarNodes(root, dirSobre);
        return root;
    }

    public void criarNodes(TreeNode root, File parent) {
        List<File> childs = Arrays.asList(parent.listFiles());
        for (File file : childs) {
            TreeNode tree;
            if (file.isFile()) {
                tree = new DefaultTreeNode("file", file.getName(), root);
            } else {
                tree = new DefaultTreeNode(file.getName(), root);
            }
            tree.setExpanded(true);
            if (file.listFiles() != null && file.listFiles().length > 0) {
                criarNodes(tree, file);
            }
        }
    }
    
    public void renomear(){
        if(StringHelper.isEmpty(novoNome)){
            addMessage(FacesMessage.SEVERITY_WARN, "O nome do arquivo não pode ficar em branco!");
        }
        File arquivoRename = arquivoVisualizado.listFiles()[0];
        if(!novoNome.equals(arquivoRename.getName())){
            int posicao = arquivoRename.getName().lastIndexOf(".");
            String mine = "";
            if(posicao > 0){
                mine = arquivoRename.getName().substring(posicao);
            }
            arquivoRename.renameTo(new File(arquivoRename.getParent(), novoNome+mine));
        }
    }

    public void onNodeSelected(NodeSelectEvent event) {
            if ("file".equals(selecionado.getType())) {
            final String nomeDir = (String) selecionado.getParent().getData();
            File dirSobre = new File(FileUtil.getHomeCliente(), "sobre");
            arquivoVisualizado = dirSobre.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return nomeDir.equals(name);
                }
            })[0];
            int posicao = selecionado.toString().lastIndexOf(".");
            if(posicao > 1){
                novoNome = selecionado.toString().substring(0, posicao);
            } else {
                novoNome = selecionado.toString();
            }
        } else {
            arquivoVisualizado = null;
            selecionado = null;
            novoNome = "";
        }
    }

    public void fecharVisualizacaoArquivo() {
        arquivoVisualizado = null;
        selecionado = null;
        novoNome = "";
    }

    public void deletarArquivo() {
        if (arquivoVisualizado != null) {
            if (delete(arquivoVisualizado)) {
                addMessage(FacesMessage.SEVERITY_INFO, "Arquivo deletado com sucesso!");
                arquivoVisualizado = null;
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar o arquivo!");
            }
        }
    }

    public boolean delete(File file) {
        if (file.listFiles() != null) {
            List<File> filhos = Arrays.asList(file.listFiles());
            for (File filho : filhos) {
                if (!delete(filho)) {
                    return false;
                }
            }
        }
        file.setWritable(true);
        return file.delete();
    }

    public Sobre getSobre() {
        return sobre;
    }

    public void setSobre(Sobre sobre) {
        this.sobre = sobre;
    }

    public TreeNode getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(TreeNode selecionado) {
        this.selecionado = selecionado;
    }

    public File getArquivoVisualizado() {
        return arquivoVisualizado;
    }

    public void setArquivoVisualizado(File arquivoVisualizado) {
        this.arquivoVisualizado = arquivoVisualizado;
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }
    
}
