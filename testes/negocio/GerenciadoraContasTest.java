package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraContasTest {
	
	private GerenciadoraContas gerContas;

	@Test
	public void testTransferValue() {

		/* ========== Montagem do cenário ========== */

		// criando alguns clientes
		int idCliente01 = 1;
		int idCliente02 = 2;
		
		ContaCorrente conta01 = new ContaCorrente(idCliente01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idCliente02, 0, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		/* ========== Execução ========== */
		gerContas.transfereValor(idCliente01, 100, idCliente02);

		/* ========== Verificações ========== */
		assertThat(conta02.getSaldo(), is(100.0));
		assertThat(conta01.getSaldo(), is(100.0));
	}
	
	// Teste transferencia de valor quando o saldo não é suficiente.
	@Test
	public void testTransferValue_SaldoInsuficiente() {

		/* ========== Montagem do cenário ========== */

		// criando alguns clientes
		int idCliente01 = 1;
		int idCliente02 = 2;
		
		ContaCorrente conta01 = new ContaCorrente(idCliente01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idCliente02, 0, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		/* ========== Execução ========== */
		boolean transferSucess = gerContas.transfereValor(idCliente01, 300, idCliente02);

		/* ========== Verificações ========== */
		assertFalse(transferSucess);
		assertThat(conta02.getSaldo(), is(0.0));
		assertThat(conta01.getSaldo(), is(200.0));
	}
	
	@Test
	public void testTransferValue_Nulo() {

		/* ========== Montagem do cenário ========== */

		// criando alguns clientes
		int idCliente01 = 1;
		int idCliente02 = 2;
		
		ContaCorrente conta01 = new ContaCorrente(idCliente01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idCliente02, 0, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		/* ========== Execução ========== */
		boolean transferSucess = gerContas.transfereValor(idCliente01, 0, idCliente02);

		/* ========== Verificações ========== */
		assertTrue(transferSucess);
		assertThat(conta02.getSaldo(), is(0.0));
		assertThat(conta01.getSaldo(), is(200.0));
	}
}
