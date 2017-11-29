package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

   @Query("SELECT c FROM Cliente c WHERE lower(c.info) LIKE lower(:parametro) OR lower(c.nome) LIKE lower(:parametro) OR lower(c.email) LIKE lower(:parametro) OR lower(c.cpfCnpj) LIKE lower(:parametro) OR lower(c.rg) LIKE lower(:parametro) OR lower(c.telefone) LIKE lower(:parametro)" )
   List<Cliente> buscarPorParametro(@Param("parametro") String parametro);


   /*List<Cliente> listaDeCliente(@Param("nome")String nome);
   @Query("SELECT c FROM Cliente c WHERE (c.vendedor.id = :nomeVendedor or c.vendedor.id is null)")
   List<Cliente> listaDeClientePorVendedor(@Param("nomeVendedor")Integer nomeVendedor);*/

   @Query("SELECT c FROM Cliente c WHERE c.nome LIKE lower(:nome)" )
   List<Cliente>buscaNome(@Param("nome")String nome);

   @Query("SELECT c FROM Cliente c WHERE c.vendedor.nome LIKE lower(:nomeVendedor)" )
   List<Cliente>buscaPorVendedor(@Param("nomeVendedor")String nomeVendedor);

   @Query("select c from Cliente c where " +
           "  (c.nome like lower(:nome) " +
           "or c.nome is null) and (c.vendedor.nome like lower(:nomeVendedor) or c.vendedor.nome is null)")
   List<Cliente> buscaParametro(@Param("nome")String nome,@Param("nomeVendedor")String nomeVendedor);


   List<Cliente> findByVendedorNome(String nome);
   List<Cliente> findByNome(String nome);
   List<Cliente> findByNomeAndVendedorNome(String nome,String nomeVendedor);



}
