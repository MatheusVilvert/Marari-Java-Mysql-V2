package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Caixa;
import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.model.Pedido;
import com.marari.mararimysqlv2.model.objGenerico;
import com.marari.mararimysqlv2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

    //
}
