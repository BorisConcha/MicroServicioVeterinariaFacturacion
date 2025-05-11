package duoc.semana3.facturation_vet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    public void createFacturaTest() {

        VeterinariaFacturacion facturaRequest = new VeterinariaFacturacion();
        facturaRequest.setFechaFactura("15/10/2021");
        facturaRequest.setNombreVeterinario("Dr.Doe");
        facturaRequest.setNombreMascota("Zeus");
        facturaRequest.setEdadMascota(2);
        facturaRequest.setGeneroMascota("Masculino");
        facturaRequest.setTipoServicio("Vacuna");
        facturaRequest.setDescripcionServicio("Se le coloco una vacuna de prueba");
        
        VeterinariaFacturacion facturaCreada = new VeterinariaFacturacion();
        facturaCreada.setIdFacturacion(1L);
        facturaCreada.setFechaFactura("15/10/2021");
        facturaCreada.setNombreVeterinario("Dr.Doe");
        facturaCreada.setNombreMascota("Zeus");
        facturaCreada.setEdadMascota(2);
        facturaCreada.setGeneroMascota("Masculino");
        facturaCreada.setTipoServicio("Vacuna");
        facturaCreada.setDescripcionServicio("Se le coloco una vacuna de prueba");
        
        when(veterinariaFacturacionService.createFactura(any(VeterinariaFacturacion.class))).thenReturn(facturaCreada);
        
        EntityModel<VeterinariaFacturacion> response = veterinariaFactuacionController.createFactura(facturaRequest);
        
        verify(veterinariaFacturacionService).createFactura(any(VeterinariaFacturacion.class));
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        
        VeterinariaFacturacion resultado = response.getContent();
        assertEquals(1L, resultado.getIdFacturacion());
        assertEquals("15/10/2021", resultado.getFechaFactura());
        assertEquals("Dr.Doe", resultado.getNombreVeterinario());
        assertEquals("Zeus", resultado.getNombreMascota());
        assertEquals(2, resultado.getEdadMascota());
        assertEquals("Masculino", resultado.getGeneroMascota());
        assertEquals("Vacuna", resultado.getTipoServicio());
        assertEquals("Se le coloco una vacuna de prueba", resultado.getDescripcionServicio());

        
        assertTrue(response.hasLinks());
        assertNotNull(response.getLink("self").orElse(null));
        assertNotNull(response.getLink("all-facturas").orElse(null));
    }

    @Test
    public void updateFacturaTest() {

        VeterinariaFacturacion facturaActualizada = new VeterinariaFacturacion();
        facturaActualizada.setIdFacturacion(1L);
        facturaActualizada.setFechaFactura("01/01/2025");
        facturaActualizada.setNombreVeterinario("Dr.John");
        facturaActualizada.setNombreMascota("Cachito");
        facturaActualizada.setEdadMascota(1);
        facturaActualizada.setGeneroMascota("Femenino");
        facturaActualizada.setTipoServicio("Vacuna");
        facturaActualizada.setDescripcionServicio("Se le coloco una vacuna de prueba");
        
        VeterinariaFacturacion facturaOriginal = new VeterinariaFacturacion();
        facturaOriginal.setIdFacturacion(1L);
        facturaOriginal.setFechaFactura("15/10/2021");
        facturaOriginal.setNombreVeterinario("Dr.Doe");
        facturaOriginal.setNombreMascota("Zeus");
        facturaOriginal.setEdadMascota(2);
        facturaOriginal.setGeneroMascota("Masculino");
        facturaOriginal.setTipoServicio("Vacuna");
        facturaOriginal.setDescripcionServicio("Se le coloco una vacuna de prueba");
        
        when(veterinariaFacturacionService.getFacturasbyId(1L)).thenReturn(Optional.of(facturaOriginal));
        
        when(veterinariaFacturacionService.updateFactura(eq(1L), any(VeterinariaFacturacion.class))).thenReturn(facturaActualizada);
        
        EntityModel<VeterinariaFacturacion> response = veterinariaFactuacionController.updateFactura(1L, facturaActualizada);
        
        verify(veterinariaFacturacionService).updateFactura(eq(1L), any(VeterinariaFacturacion.class));
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        
        VeterinariaFacturacion resultado = response.getContent();
        assertEquals(1L, resultado.getIdFacturacion());
        assertEquals("01/01/2025", resultado.getFechaFactura());
        assertEquals("Dr.John", resultado.getNombreVeterinario());
        assertEquals("Cachito", resultado.getNombreMascota());
        assertEquals(1, resultado.getEdadMascota());
        assertEquals("Femenino", resultado.getGeneroMascota());
        assertEquals("Vacuna", resultado.getTipoServicio());
        assertEquals("Se le coloco una vacuna de prueba", resultado.getDescripcionServicio());
        
        assertTrue(response.hasLinks());
        assertNotNull(response.getLink("self").orElse(null));
        assertNotNull(response.getLink("all-facturas").orElse(null));

    }


}
