package matheus.cavalari.custodiaContratoConsignado.application;

import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import matheus.cavalari.custodiaContratoConsignado.dto.ContratoInputDTO;

import java.util.List;

public interface IContratoService {
    Contrato getContrato(Long id);
    Contrato armazenarContrato(ContratoInputDTO contrato);
    List<Contrato> listarContratos();
}
