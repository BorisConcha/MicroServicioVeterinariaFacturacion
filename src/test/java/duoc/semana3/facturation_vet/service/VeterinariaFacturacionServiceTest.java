package duoc.semana3.facturation_vet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;
import duoc.semana3.facturation_vet.repository.VeterinariaFacturacionRepository;

@ExtendWith(MockitoExtension.class)
public class VeterinariaFacturacionServiceTest {
    
    @InjectMocks
    private VeterinariaFacturacionServiceImpl veterinariaFacturacionServiceImplMock;

    @Mock
    VeterinariaFacturacionRepository veterinariaFacturacionRepository;

    @Test
    public void createFacturaTest() {

        VeterinariaFacturacion facturacion = new VeterinariaFacturacion();

        facturacion.setFechaFactura("24/03/2021");
        facturacion.setNombreVeterinario("Dr.Prueba");
        facturacion.setNombreMascota("Cerbero");
        facturacion.setEdadMascota(1);
        facturacion.setGeneroMascota("Masculino");
        facturacion.setTipoServicio("Desparasitación");
        facturacion.setDescripcionServicio("Se le hizo un control de parásitos internos y externos");

        when(veterinariaFacturacionRepository.save(any())).thenReturn(facturacion);

        VeterinariaFacturacion resultado = veterinariaFacturacionServiceImplMock.createFactura(facturacion);

        assertEquals("Dr.Prueba", resultado.getNombreVeterinario());

    }
}
