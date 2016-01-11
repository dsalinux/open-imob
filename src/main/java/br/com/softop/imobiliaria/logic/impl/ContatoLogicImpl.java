package br.com.softop.imobiliaria.logic.impl;

import br.com.softop.imobiliaria.entity.Configuracoes;
import br.com.softop.imobiliaria.entity.Imovel;
import br.com.softop.imobiliaria.entity.vo.ContatoVO;
import br.com.softop.imobiliaria.logic.ContatoLogic;
import br.com.softop.imobiliaria.util.Assert;
import br.com.softop.imobiliaria.util.MailUtil;
import br.com.softop.imobiliaria.util.StringHelper;
import br.com.softop.imobiliaria.util.exception.BusinessException;

/**
 *
 * @author danilo
 */
public class ContatoLogicImpl implements ContatoLogic {

    @Override
    public void enviarMensagem(ContatoVO contatoVO) throws BusinessException {
        if (StringHelper.isEmpty(contatoVO.getNome())) {
            throw new BusinessException("Por favor informe seu nome!");
        }
        if (StringHelper.isEmpty(contatoVO.getEmail()) && StringHelper.isEmpty(contatoVO.getTelefone())) {
            throw new BusinessException("Por favor informe o email ou telefone para contato!");
        }
        StringBuilder contato = new StringBuilder();
        if (!StringHelper.isEmpty(contatoVO.getEmail())) {
            if (!Assert.isValidEmail(contatoVO.getEmail())) {
                throw new BusinessException("Informe um email válido!");
            } else {
                contato.append("Email para contato com usuário: ").append(contatoVO.getEmail()).append("<br/>");
            }
        }
        if (!StringHelper.isEmpty(contatoVO.getTelefone())) {
            contato.append("Telefone para contato com o usúario: ").append(contatoVO.getTelefone());
        }
        if (StringHelper.isEmpty(contatoVO.getMensagem())) {
            throw new BusinessException("Por favor informe a mensagem deste contato!");
        }
        StringBuilder mensagem = new StringBuilder("O cliente ");
        mensagem.append(contatoVO.getNome());
        mensagem.append(" entrou em contato com a seguinte mensagem: <br/><br/>");
        mensagem.append(contatoVO.getMensagem());
        mensagem.append("<br/>");
        mensagem.append(contato);
        Configuracoes configuracoes = new ConfiguracoesLogicImpl().recupararConfiguracoes();
        MailUtil.sendMail(configuracoes.getEmailsRecebimento().split(","), null, configuracoes.getEmailEnvio(), configuracoes.getSenhaEnvio(), "Imperial Imóveis", contatoVO.getNome() + " entrou em contato pelo site", mensagem.toString());
    }

    @Override
    public void solicitarImovel(Imovel imovel, ContatoVO contatoVO) throws BusinessException {
        if (imovel == null) {
            throw new BusinessException("Erro ao identificar o imóvel! Tente recaregar a página!");
        }
        if (StringHelper.isEmpty(contatoVO.getNome())) {
            throw new BusinessException("Por favor informe seu nome!");
        }
        if (StringHelper.isEmpty(contatoVO.getEmail()) && StringHelper.isEmpty(contatoVO.getTelefone())) {
            throw new BusinessException("Por favor informe o email ou telefone para contato!");
        }
        StringBuilder contato = new StringBuilder();
        if (!StringHelper.isEmpty(contatoVO.getEmail())) {
            if (!Assert.isValidEmail(contatoVO.getEmail())) {
                throw new BusinessException("Informe um email válido!");
            } else {
                contato.append("Email para contato com usuário: ").append(contatoVO.getEmail()).append("<br/>");
            }
        }
        if (!StringHelper.isEmpty(contatoVO.getTelefone())) {
            contato.append("Telefone para contato com o usúario: ").append(contatoVO.getTelefone());
        }
        if (StringHelper.isEmpty(contatoVO.getMensagem())) {
            throw new BusinessException("Por favor informe a mensagem deste contato!");
        }
        StringBuilder mensagem = new StringBuilder("O cliente ");
        mensagem.append(contatoVO.getNome());
        mensagem.append(" enviou uma mensagem a respeito do imóvel código ");
        mensagem.append(imovel.getId());
        mensagem.append(" http://imperialimoveis.com/");
        if(Imovel.Tipo.ALUGUEL.equals(imovel.getTipo_())){
            mensagem.append("alugar.jsf");
        } else {
            mensagem.append("comprar.jsf");
        }
        mensagem.append("?imovel_id=");
        mensagem.append(imovel.getId());
        mensagem.append("<br/><br/>");
        mensagem.append(contatoVO.getMensagem());
        mensagem.append("<br/><br/>");
        mensagem.append(contato);
        Configuracoes configuracoes = new ConfiguracoesLogicImpl().recupararConfiguracoes();
        MailUtil.sendMail(configuracoes.getEmailsRecebimento().split(","), null, configuracoes.getEmailEnvio(), configuracoes.getSenhaEnvio(), "Imperial Imóveis", contatoVO.getNome() + " entrou em contato pelo site", mensagem.toString());
    }

    @Override
    public void testarEnvioDeEmail(Configuracoes configuracoes){
        MailUtil.sendMail(configuracoes.getEmailsRecebimento().split(","), null, configuracoes.getEmailEnvio(), configuracoes.getSenhaEnvio(), "Imperial Imóveis", "Email de teste enviado pelo sistema!", "Se você recebeu este email, tudo está configurado corretamente! Qualquer dúvida entre em contato com Softop pelo site: www.softop.com.br");
    }
}
