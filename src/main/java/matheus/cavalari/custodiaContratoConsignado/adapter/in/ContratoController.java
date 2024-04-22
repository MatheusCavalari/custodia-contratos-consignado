package matheus.cavalari.custodiaContratoConsignado.adapter.in;

import matheus.cavalari.custodiaContratoConsignado.application.IContratoService;
import matheus.cavalari.custodiaContratoConsignado.application.exception.SimulacaoNaoEncontradaException;
import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import matheus.cavalari.custodiaContratoConsignado.dto.ContratoInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contrato")
public class ContratoController {

    private final IContratoService contratoService;

    @Autowired
    public ContratoController(IContratoService simulacaoService) {
        this.contratoService = simulacaoService;
    }

    @PostMapping
    public ResponseEntity<?> criarContrato(@RequestBody ContratoInputDTO contratoInputDTO) {
        try {
            Contrato contrato = contratoService.armazenarContrato(contratoInputDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(contrato);
        } catch (SimulacaoNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Simulação não encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o contrato");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContrato(@PathVariable Long id) {
        try {
            Contrato contrato = contratoService.getContrato(id);
            if (contrato != null) {
                return ResponseEntity.ok(contrato);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado para o ID informado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter o contrato");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarContratos() {
        try {
            List<Contrato> contratos = contratoService.listarContratos();
            if (contratos.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else
                return ResponseEntity.ok(contratos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar os contratos");
        }
    }
}