package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraTest {
    
    @Mock
    private ServicoMatematico servicoMatematico;
    
    private Calculadora calculadora;
    
    @BeforeEach
    void setUp() {
        calculadora = new Calculadora(servicoMatematico);
    }
    
    @Test
    void testSomar() {

        when(servicoMatematico.somar(2, 3)).thenReturn(5);
        int resultado = calculadora.somar(2, 3);
        assertEquals(5, resultado);
    }
    
    @Test
    void testSomarComOutrosValores() {

        when(servicoMatematico.somar(10, 20)).thenReturn(30);
        int resultado = calculadora.somar(10, 20);
        assertEquals(30, resultado);
    }
    
    @Test
    void testSomarComValoresNegativos() {

        when(servicoMatematico.somar(-5, 3)).thenReturn(-2);
        int resultado = calculadora.somar(-5, 3);
        assertEquals(-2, resultado);
    }
}