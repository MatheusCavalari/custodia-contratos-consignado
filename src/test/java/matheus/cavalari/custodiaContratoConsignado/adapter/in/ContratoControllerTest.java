package matheus.cavalari.custodiaContratoConsignado.adapter.in;

import matheus.cavalari.custodiaContratoConsignado.application.IContratoService;
import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import matheus.cavalari.custodiaContratoConsignado.dto.ContratoInputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContratoControllerTest {

    @Mock
    private IContratoService contratoService;

    @InjectMocks
    private ContratoController contratoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarContrato() {
        ContratoInputDTO contratoInputDTO = new ContratoInputDTO();
        contratoInputDTO.setDataContrato(LocalDate.now());
        contratoInputDTO.setIdSimulacao(1L);

        Contrato contrato = new Contrato();
        contrato.setDataContrato(contratoInputDTO.getDataContrato());
        contrato.setIdSimulacao(contratoInputDTO.getIdSimulacao());

        when(contratoService.armazenarContrato(contratoInputDTO)).thenReturn(contrato);

        ResponseEntity<?> responseEntity = contratoController.criarContrato(contratoInputDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(contrato, responseEntity.getBody());
    }

    @Test
    public void testListarContratos() {
        when(contratoService.listarContratos()).thenReturn(Arrays.asList(new Contrato(), new Contrato()));

        ResponseEntity<?> responseEntity = contratoController.listarContratos();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, ((List<Contrato>) responseEntity.getBody()).size());
    }

    @Test
    public void testGetContrato() {
        Long id = 1L;
        Contrato contrato = new Contrato();
        contrato.setId(id);

        when(contratoService.getContrato(id)).thenReturn(contrato);

        ResponseEntity<?> responseEntity = contratoController.getContrato(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(contrato, responseEntity.getBody());
    }
}