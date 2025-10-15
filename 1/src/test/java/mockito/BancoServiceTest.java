package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BancoServiceTest {


    @Test
    public void testConsultarSaldo() {
        // Criando mock do repositório
        ContaRepository contaRepository = Mockito.mock(ContaRepository.class);

        // Criando conta simulada
        Conta conta = new Conta("12345", 1000.0);

        // Definindo comportamento do mock
        when(contaRepository.buscarConta("12345")).thenReturn(conta);

        // Criando serviço com o mock
        BancoService bancoService = new BancoService(contaRepository);

        // Testando consulta de saldo
        double saldo = bancoService.consultarSaldo("12345");

        // Verificando se o saldo está correto
        assertEquals(1000.0, saldo);

        // Verificando se o método buscarConta foi chamado
        verify(contaRepository).buscarConta("12345");
    }


    @Test
    public void testDepositar() {
        // Criando mock do repositório
        ContaRepository contaRepository = Mockito.mock(ContaRepository.class);

        // Criando conta simulada
        Conta conta = new Conta("12345", 1000.0);

        // Definindo comportamento do mock
        when(contaRepository.buscarConta("12345")).thenReturn(conta);

        // Criando serviço com o mock
        BancoService bancoService = new BancoService(contaRepository);

        // Executando depósito
        bancoService.depositar("12345", 999.90);

        // Verificando se o saldo foi atualizado
        double saldoConta = bancoService.consultarSaldo("12345");

        assertEquals(1999.9, saldoConta);

        // Verificando se os métodos foram chamados corretamente
        verify(contaRepository).buscarConta("12345");
        verify(contaRepository).salvar(conta);
    }
}