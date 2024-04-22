package matheus.cavalari.custodiaContratoConsignado.application;

import matheus.cavalari.custodiaContratoConsignado.adapter.out.ContratoRepository;
import matheus.cavalari.custodiaContratoConsignado.application.exception.SimulacaoNaoEncontradaException;
import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import matheus.cavalari.custodiaContratoConsignado.domain.Simulacao;
import matheus.cavalari.custodiaContratoConsignado.dto.ContratoInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContratoService implements IContratoService {

    private final ContratoRepository contratoRepository;
    private final RestTemplate restTemplate;

    @Value("${simulacao.service.url}")
    private String simulacaoServiceUrl;


    @Autowired
    public ContratoService(ContratoRepository contratoRepository, RestTemplate restTemplate) {
        this.contratoRepository = contratoRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Contrato getContrato(Long id) {
        return contratoRepository.findById(id).orElse(null);
    }

    @Override
    public Contrato  armazenarContrato(ContratoInputDTO contratoInputDTO) {
        ResponseEntity<Simulacao> response = restTemplate.getForEntity(simulacaoServiceUrl + "/simulacao/" + contratoInputDTO.getIdSimulacao(), Simulacao.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Simulacao simulacao = response.getBody();

            Contrato contrato = Contrato.builder()
                    .dataContrato(contratoInputDTO.getDataContrato())
                    .idSimulacao(contratoInputDTO.getIdSimulacao())
                    .build();

            contratoRepository.save(contrato);
            return contrato;
        } else {
            throw new SimulacaoNaoEncontradaException("Simulação não encontrada");
        }
    }

    @Override
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }
}