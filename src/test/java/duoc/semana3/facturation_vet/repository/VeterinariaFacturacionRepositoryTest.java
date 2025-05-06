package duoc.semana3.facturation_vet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VeterinariaFacturacionRepositoryTest {
    
    @Autowired
    private VeterinariaFacturacionRepository veterinariaFacturacionRepository;

    @Test
    public void createFacturaTest() {

        VeterinariaFacturacion facturacion = new VeterinariaFacturacion();

        facturacion.setFechaFactura("01/02/2025");
        facturacion.setNombreVeterinario("Dr.Prueba");
        facturacion.setNombreMascota("Leia");
        facturacion.setEdadMascota(4);
        facturacion.setGeneroMascota("Femenino");
        facturacion.setTipoServicio("Consulta");
        facturacion.setDescripcionServicio("Se le hizo un chequeo fisico completo");

        VeterinariaFacturacion resultado = veterinariaFacturacionRepository.save(facturacion);

        assertNotNull(resultado.getIdFacturacion());

        assertEquals("Dr.Prueba", resultado.getNombreVeterinario());

    }
}
