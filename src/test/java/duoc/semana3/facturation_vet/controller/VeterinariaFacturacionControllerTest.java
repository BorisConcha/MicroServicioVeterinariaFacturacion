package duoc.semana3.facturation_vet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;
import duoc.semana3.facturation_vet.service.VeterinariaFacturacionService;
import duoc.semana3.facturation_vet.service.VeterinariaFacturacionServiceImpl;

@WebMvcTest(VeterinariaFactuacionController.class)
public class VeterinariaFacturacionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VeterinariaFacturacionService veterinariaFacturacionService;

    @MockBean
    private VeterinariaFacturacionServiceImpl veterinariaFacturacionServiceImplMock;

    @InjectMocks
    private VeterinariaFactuacionController veterinariaFactuacionController;

    @Test
    public void obtenerFacturasTest() throws Exception{

        VeterinariaFacturacion facturacion1 = new VeterinariaFacturacion();
        facturacion1.setIdFacturacion(1L);
        facturacion1.setFechaFactura("10/06/2023");
        facturacion1.setNombreVeterinario("Dr.Prueba");
        facturacion1.setNombreMascota("Pruebita");
        facturacion1.setEdadMascota(1);
        facturacion1.setGeneroMascota("Masculino");
        facturacion1.setTipoServicio("Vacuna");
        facturacion1.setDescripcionServicio("Se le coloco una vacuna de prueba");

        VeterinariaFacturacion facturacion2 = new VeterinariaFacturacion();
        facturacion2.setIdFacturacion(2L);
        facturacion2.setFechaFactura("04/09/2022");
        facturacion2.setNombreVeterinario("Dr.Doe");
        facturacion2.setNombreMascota("Cachito");
        facturacion2.setEdadMascota(3);
        facturacion2.setGeneroMascota("Masculino");
        facturacion2.setTipoServicio("Chequeo");
        facturacion2.setDescripcionServicio("Se le hizo un chequeo general al animal");

        List<VeterinariaFacturacion> facturaciones = Arrays.asList(facturacion1, facturacion2);
        when(veterinariaFacturacionServiceImplMock.getAllFacturas()).thenReturn(facturaciones);

        mockMvc.perform(MockMvcRequestBuilders.get("/facturas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturasList[0].NombreVeterinario", Matchers.is("Dr.Prueba")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturasList[1].NombreVeterinario", Matchers.is("Dr.Doe")));

    }

    @Test
    public void getFacturasbyIdTest() {

        VeterinariaFacturacion facturacion = new VeterinariaFacturacion();
        facturacion.setIdFacturacion(1L);
        facturacion.setFechaFactura("15/10/2021");
        facturacion.setNombreVeterinario("Dr.Doe");
        facturacion.setNombreMascota("Zeus");
        facturacion.setEdadMascota(2);
        facturacion.setGeneroMascota("Masculino");
        facturacion.setTipoServicio("Vacuna");
        facturacion.setDescripcionServicio("Se le coloco una vacuna de prueba");
        
        when(veterinariaFacturacionService.getFacturasbyId(1L)).thenReturn(Optional.of(facturacion));
        
        EntityModel<VeterinariaFacturacion> response = veterinariaFactuacionController.getFacturasbyId(1L);
        
        verify(veterinariaFacturacionService).getFacturasbyId(1L);

        assertNotNull(response);
        assertNotNull(response.getContent());
        assertEquals(1L, response.getContent().getIdFacturacion());
        assertEquals("Dr.Doe", response.getContent().getNombreVeterinario());
        assertEquals("Zeus", response.getContent().getNombreMascota());
        
        assertTrue(response.hasLinks());
        assertTrue(response.getLinks().hasLink("self"));

    }
}
