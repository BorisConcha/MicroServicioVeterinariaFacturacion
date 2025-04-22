package duoc.semana3.facturation_vet.service;

import java.util.List;
import java.util.Optional;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;

public interface VeterinariaFacturacionService {
    List<VeterinariaFacturacion> getAllFacturas();
    Optional<VeterinariaFacturacion> getFacturasbyId(Long id);
    VeterinariaFacturacion createFactura(VeterinariaFacturacion facturacion);
    VeterinariaFacturacion updateFactura(Long id, VeterinariaFacturacion facturacion);
    void deleteFactura(Long id);
}
