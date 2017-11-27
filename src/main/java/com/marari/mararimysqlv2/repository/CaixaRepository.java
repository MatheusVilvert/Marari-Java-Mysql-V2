package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaixaRepository extends JpaRepository<Caixa,Integer> {
    //@Query("SELECT c FROM Caixa c WHERE lower(c.info) LIKE lower(:parametro) OR lower(c.nome) LIKE lower(:parametro) OR lower(c.email) LIKE lower(:parametro) OR lower(c.cpfCnpj) LIKE lower(:parametro) OR lower(c.rg) LIKE lower(:parametro) OR lower(c.telefone) LIKE lower(:parametro)" )
    @Query("SELECT " +
            "(SELECT SUM(valor) " +
            "from Caixa where tipo_despesa_id = 1 AND data BETWEEN :dataInicio AND :dataFinal) AS saida," +
            "(SELECT SUM(valor) " +
            "from Caixa where tipo_despesa_id = 2 AND data BETWEEN :dataInicio AND :dataFinal)AS entrada FROM Caixa ORDER BY tipo_despesa_id")
    List<Caixa> fluxoDeCaixa(@Param("dataInicio") String dataInicio, @Param("dataFinal") String dataFinal);

    @Query("select c from Caixa c where c.data between :dataIni and :dataFin")
    List<Caixa> movimentoCaixa(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin);
}
