package matheus.cavalari.custodiaContratoConsignado.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Matheus",
                        email = "matheuscav@hotmail.com"
                ),
                description = "O microserviço Custódia de Contratos Consignado gerencia e armazena os contratos de empréstimos consignados, garantindo a integridade e disponibilidade das informações.",
                title = "Custódia de Contratos Consignado",
                version = "1.0"
        )
)
public class OpenApiConfig {
}
