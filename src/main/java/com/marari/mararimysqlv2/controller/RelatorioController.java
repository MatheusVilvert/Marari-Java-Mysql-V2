package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.*;
import com.marari.mararimysqlv2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class RelatorioController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
   private ProdutoService produtoService;
    @Autowired
    private CaixaService caixaService;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("teste")
    public ModelAndView teste(){
        ModelAndView mv = new ModelAndView("teste");

//
        mv.addObject("testes", clienteService.buscarTodos());
        mv.addObject("produtos",produtoService.buscarTodos());
        return mv;
    }

    @GetMapping("relatorio/listacliente")
    public ModelAndView listaDeCliente(@Param("nome")String nome,@Param("nomeVendedor")String nomeVendedor){
        ModelAndView mv = new ModelAndView("lista-clientes");
        if (nome.equals("") && nomeVendedor.equals("")){
            mv.addObject("clientes",clienteService.buscarTodos());
        }else if (nome != "" && nomeVendedor.equals("")){
            nome = '%'+nome+'%';
            mv.addObject("clientes",clienteService.buscarPorNome(nome));
        }else if (nome.equals("") && nomeVendedor != ""){
            mv.addObject("clientes",clienteService.buscarPorNomeVendedor(nomeVendedor));
        }
        return mv;
    }

    @GetMapping("relatorio/listapedido")
    public ModelAndView listaPedidos(@Param("dataIni")String dataIni, @Param("dataFin") String dataFin, @Param("nomeCliente")String nomeCliente,@Param("nomeVendedor")String nomeVendedor){
        ModelAndView mv = new ModelAndView("pedidos-emitidos");
        List<objGenerico> objGenericos = new ArrayList<objGenerico>();
        List<Pedido>  pedidos = null;


        if (dataIni != "" && dataFin != "" && nomeCliente != "" && nomeVendedor != "" ){
            nomeCliente = '%'+nomeCliente+'%';
            nomeVendedor = '%'+nomeVendedor+'%';
            pedidos= pedidoService.buscaParametro(dataIni,dataFin,nomeVendedor,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeCliente.equals("") && nomeVendedor.equals("")){
            pedidos= pedidoService.buscaPorData(dataIni,dataFin);
        }else if (dataIni != "" && dataFin != "" && nomeCliente!= ""){
            nomeCliente = '%'+nomeCliente+'%';
            pedidos= pedidoService.buscaPorCliente(dataIni,dataFin,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeVendedor!= ""){
            nomeVendedor = '%'+nomeVendedor+'%';
            pedidos=pedidoService.buscaPorVendedor(dataIni,dataFin,nomeVendedor);
        }
        DecimalFormat df = new DecimalFormat("#.00");

        for (int i =0; i<pedidos.size(); i++){
            String valorDecimal = df.format(pedidos.get(i).getValorTotal());
            objGenerico obj = new objGenerico();
            obj.setPedido(pedidos.get(i));
            obj.setValorTotal("R$ "+valorDecimal);
            objGenericos.add(obj);
        }
        mv.addObject("pedidos",objGenericos);
        return mv;
    }

    @GetMapping("relatorio/rentabilidadepedido")
    public ModelAndView RentabilidadePedido(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin){
        List<objGenerico> objGenericos = new ArrayList<objGenerico>();
        ModelAndView mv = new ModelAndView("rentabilidade-pedido");
        List<Pedido> pedidos = pedidoService.buscaPorData(dataIni,dataIni);
        double valorCusto =0;
        double margem =0;
        for (int i =0; i<pedidos.size();i++){
            objGenerico obj = new objGenerico();
            obj.setPedido(pedidos.get(i));
            objGenericos.add(obj);
            for (int j =0; j<pedidos.get(i).getItensPedido().size(); j++) {
                valorCusto += pedidos.get(i).getItensPedido().get(j).getProduto().getPrecoCusto();
            }
            objGenericos.get(i).setValorCusto(valorCusto);
            margem = ((objGenericos.get(i).getPedido().getValorTotal() - valorCusto) / objGenericos.get(i).getPedido().getValorTotal()) * 100;
            DecimalFormat df = new DecimalFormat("#.00");
            String margemDecimal = df.format(margem);
            objGenericos.get(i).setMargemPedido(margemDecimal+"%");
        }
        mv.addObject("pedidos",objGenericos);
        return mv;
    }

    @GetMapping("relatorio/movimentocaixa")
    public ModelAndView movimentoCaixa(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin){
        List<objGenerico> objGenericos = new ArrayList<objGenerico>();
        ModelAndView mv = new ModelAndView("movimento-caixa");
        List<Caixa> caixas = caixaService.movimentoCaixa(dataIni,dataFin);
        double entrada =0,saida = 0;
        for (int i=0;i<caixas.size();i++){
            objGenerico obj = new objGenerico();
            obj.setCaixa(caixas.get(i));
            objGenericos.add(obj);
            if (caixas.get(i).getTipoDespesa().getDescricao().equals("saida") || caixas.get(i).getTipoDespesa().getDescricao().equals("Saida") || caixas.get(i).getTipoDespesa().getDescricao().equals("saída")){
                saida+=caixas.get(i).getValor();
            }else{
                entrada+=caixas.get(i).getValor();
            }
        }
        objGenerico teste = new objGenerico();
        DecimalFormat df = new DecimalFormat("#.00");
        String entradaDecimal = df.format(entrada);
        String saidaDecimal = df.format(saida);
        teste.setEntrada("R$ "+entradaDecimal);
        teste.setSaida("R$ "+saidaDecimal);
        mv.addObject("teste",teste);
        mv.addObject("caixas",objGenericos);
        return mv;
    }

    @GetMapping("relatorio/posicaoestoque")
    public ModelAndView posicaoEstoque(@Param("nome")String nome){
        ModelAndView mv = new ModelAndView("posicao-estoque");
        List<Produto> produtoList = null;
        if (nome != "") {
            produtoList= produtoService.listaProduto(nome);
        }else {
            produtoList = produtoService.buscarTodos();
        }

        mv.addObject("produtos",produtoList);

        return mv;
    }

    @GetMapping("relatorio/rentabilidadeproduto")
    public ModelAndView rentabilidadeProduto(@Param("nome")String nome){
        ModelAndView mv = new ModelAndView("rentabilidade-produto");
        List<objGenerico> objGenericos = new ArrayList<objGenerico>();
        List<Produto> produtoList = null;
        double margem =0;
        if (nome != "") {
            produtoList= produtoService.listaProduto(nome);
        }else {
            produtoList = produtoService.buscarTodos();
        }
        for (int i=0;i<produtoList.size();i++){
            objGenerico obj = new objGenerico();
            obj.setProduto(produtoList.get(i));
            objGenericos.add(obj);
        }
        for (int i=0;i<objGenericos.size();i++){
            margem = ((objGenericos.get(i).getProduto().getPrecoVenda() - objGenericos.get(i).getProduto().getPrecoCusto()) / objGenericos.get(i).getProduto().getPrecoVenda()) * 100;
            DecimalFormat df = new DecimalFormat("#.00");
            String margemDecimal = df.format(margem);
            objGenericos.get(i).setMargemProduto(margemDecimal+"%");
        }
        mv.addObject("produtos",objGenericos);

        return mv;
    }


    //
}
