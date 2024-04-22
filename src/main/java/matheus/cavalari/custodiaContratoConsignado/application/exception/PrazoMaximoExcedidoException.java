package matheus.cavalari.custodiaContratoConsignado.application.exception;

public class PrazoMaximoExcedidoException extends RuntimeException {
    public PrazoMaximoExcedidoException(String message) {
        super(message);
    }
}
