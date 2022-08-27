package negocio;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

public class GerenciadoraClientesTest {
	
	private GerenciadoraClientes gerClientes;
	private final int idCliente01 = 1;
	private final int idCliente02 = 2;
	private final int idClienteInexistente = 9999;
	
	@Before
	public void setUp() {
		/* ========== Montagem do cenário ========== */
		
		// criando alguns clientes
		Cliente cliente01 = new Cliente(idCliente01, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}
	
	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	@Test
	public void testSearchClientById() {
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		/* ========== Verificações ========== */
		assertThat(cliente.getId(), is(idCliente01));
	}
	
	@Test
	public void testSearchClientById_ClientInexistente() {
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idClienteInexistente);

		/* ========== Verificações ========== */
		assertNull(cliente);
	}

	@Test
	public void testRemoveClienteById() {
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);
		
		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));
	}
	
	@Test
	public void testRemoveClienteById_ClienteInexistente() {
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idClienteInexistente);
		
		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	
	@Test
	public void testClienteIdadeAceitavel_24() throws IdadeNaoPermitidaException {
		/* ========== Montagem do cenário ========== */
		Cliente cliente = new Cliente(1, "Victor", 24, "victor@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		 boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel_18() throws IdadeNaoPermitidaException {
		/* ========== Montagem do cenário ========== */
		Cliente cliente = new Cliente(1, "Victor", 18, "victor@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		 boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel_65() throws IdadeNaoPermitidaException {
		/* ========== Montagem do cenário ========== */
		Cliente cliente = new Cliente(1, "Victor", 65, "victor@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		 boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificações ========== */
		assertTrue(idadeValida);
	}

	@Test
	public void testClienteIdadeAceitavel_17() throws IdadeNaoPermitidaException {
		/* ========== Montagem do cenário ========== */
		Cliente cliente = new Cliente(1, "Victor", 17, "victor@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

	@Test
	public void testClienteIdadeAceitavel_66() throws IdadeNaoPermitidaException {
		/* ========== Montagem do cenário ========== */
		Cliente cliente = new Cliente(1, "Victor", 66, "victor@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}
}
