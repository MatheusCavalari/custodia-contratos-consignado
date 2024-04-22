package matheus.cavalari.custodiaContratoConsignado.application;

import matheus.cavalari.custodiaContratoConsignado.adapter.out.ContratoRepository;
import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import matheus.cavalari.custodiaContratoConsignado.domain.Simulacao;
import matheus.cavalari.custodiaContratoConsignado.dto.ContratoInputDTO;
import matheus.cavalari.custodiaContratoConsignado.enums.Convenio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ContratoService contratoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testArmazenarContrato() {
        ContratoInputDTO contratoInputDTO = new ContratoInputDTO();
        contratoInputDTO.setDataContrato(LocalDate.now());
        contratoInputDTO.setIdSimulacao(1L);

        Simulacao simulacao = Simulacao.builder()
                .id(1L)
                .cpfCliente("111.111.111-11")
                .convenioCliente(Convenio.EMPRESA_PRIVADA)
                .valorSolicitado(BigDecimal.valueOf(1000))
                .quantidadeParcelas(12)
                .build();

        ResponseEntity<Simulacao> responseEntity = new ResponseEntity<>(simulacao, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(Simulacao.class))).thenReturn(responseEntity);

        Contrato contrato = contratoService.armazenarContrato(contratoInputDTO);

        assertEquals(contratoInputDTO.getDataContrato(), contrato.getDataContrato());
        assertEquals(contratoInputDTO.getIdSimulacao(), contrato.getIdSimulacao());
        verify(contratoRepository, times(1)).save(any(Contrato.class));
    }

    @Test
    public void testGetContrato() {
        Long id = 1L;
        Contrato contrato = new Contrato();
        contrato.setId(id);

        when(contratoRepository.findById(id)).thenReturn(Optional.of(contrato));

        Contrato contratoRetornado = contratoService.getContrato(id);

        assertEquals(contrato.getId(), contratoRetornado.getId());
    }

    @Test
    public void testListarContratos() {
        when(contratoRepository.findAll()).thenReturn(Arrays.asList(new Contrato(), new Contrato()));

        List<Contrato> contratos = contratoService.listarContratos();

        assertEquals(2, contratos.size());
    }
}