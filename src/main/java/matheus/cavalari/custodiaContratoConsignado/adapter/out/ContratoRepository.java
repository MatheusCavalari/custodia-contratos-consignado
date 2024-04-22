package matheus.cavalari.custodiaContratoConsignado.adapter.out;

import matheus.cavalari.custodiaContratoConsignado.domain.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findById(Long id);
}


