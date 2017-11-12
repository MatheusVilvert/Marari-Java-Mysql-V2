package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.*;
import com.marari.mararimysqlv2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;


    public Caixa salvar(Caixa caixa){
        //setando forma de pagamento
        if (caixa.getFormaPagamento().getId() == null){
            formaPagamentoRepository.save(caixa.getFormaPagamento());
        }
        FormaPagamento formaPagamento = formaPagamentoRepository.findOne(caixa.getFormaPagamento().getId());
        caixa.setFormaPagamento(formaPagamento);
        //setando tipo da despesa
        if (caixa.getTipoDespesa().getId() == null){
            tipoDespesaRepository.save(caixa.getTipoDespesa());
        }
        TipoDespesa tipoDespesa = tipoDespesaRepository.findOne(caixa.getTipoDespesa().getId() );
        caixa.setTipoDespesa(tipoDespesa);

        //setando cliente
        if (caixa.getCliente().getId() == null){
            clienteRepository.save(caixa.getCliente());
        }
        Cliente cliente = clienteRepository.findOne(caixa.getCliente().getId() );
        caixa.setCliente(cliente);

        //setando fornecedor
        if (caixa.getFornecedor().getId() == null){
            fornecedorRepository.save(caixa.getFornecedor());
        }
        Fornecedor fornecedor = fornecedorRepository.findOne(caixa.getFornecedor().getId() );
        caixa.setFornecedor(fornecedor);

        return caixaRepository.save(caixa);

    }

    public List<Caixa> fluxoDeCaixa(String dataInicio, String dataFinal){return caixaRepository.fluxoDeCaixa(dataInicio,dataFinal);}

    public List<Caixa> buscarTodos(){return caixaRepository.findAll();}

    public void excluir(Caixa caixa){caixaRepository.delete(caixa);}

    public void editar(Caixa caixa){
        Caixa caixaExistente = caixaRepository.findOne(caixa.getId());
        if (caixaExistente != null){
            caixaRepository.save(caixa);
        }
    }
}
