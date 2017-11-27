package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.data = :data")
     double vendasHoje(@Param("data")String data);

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.data BETWEEN :dataIni and :dataFin")
     double vendasPeriodo(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin);

    @Query("SELECT p.data FROM Pedido p WHERE p.data BETWEEN :dataIni and :dataFin group by p.data")
    List<String> vendasDiaPeriodo(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin);

    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.data BETWEEN :dataIni and :dataFin group by p.data")
    List<Double> vendasValorPeriodo(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin);

    @Query("select p from Pedido p where p.data between :dataIni and :dataFin")
    List<Pedido> buscaPorData(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin);

    @Query("select p from Pedido p where p.data between :dataIni and :dataFin and p.cliente.nome like lower(:nomeCliente) ")
    List<Pedido> buscaPorCliente(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin,@Param("nomeCliente")String nomeCliente);

    @Query("select p from Pedido p where p.data between :dataIni and :dataFin and p.usuario.nome like lower(:nomeVendedor) ")
    List<Pedido> buscaPorVendedor(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin,@Param("nomeVendedor")String nomeVendedor);


    @Query("select p from Pedido p where (p.data between :dataIni and :dataFin )" +
            " and (p.usuario.nome like lower(:nomeVendedor) " +
            "or p.usuario.nome is null) and (p.cliente.nome like lower(:nomeCliente) or p.cliente.nome is null)")
    List<Pedido> buscaParametro(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin,@Param("nomeVendedor")String nomeVendedor,@Param("nomeCliente")String nomeCliente);

    @Query("select p from Pedido p where p.usuario.nome = 'jao'")
    List<Pedido> teste();

}
