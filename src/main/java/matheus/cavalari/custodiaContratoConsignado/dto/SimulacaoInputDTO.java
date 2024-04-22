package matheus.cavalari.custodiaContratoConsignado.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulacaoInputDTO {
    private String cpfCliente;
    private BigDecimal valorSolicitado;
    private int quantidadeParcelas;
}
