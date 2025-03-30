package duoc.semana3.facturation_vet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class VeterinariaFactuacionController {
    private List<VeterinariaFacturacion> facturaciones = new ArrayList<>();

    public VeterinariaFactuacionController(){
        facturaciones.add(new VeterinariaFacturacion(1, "05/01/2020", "Dr. Martínez", "Zeus", 2, "Masculino", "Vacunación", "Se le suministró al animal una vacuna antirrábica"));
        facturaciones.add(new VeterinariaFacturacion(2, "14/03/2021", "Dr. Rodríguez", "Luna", 1, "Femenino", "Cirugía", "Se esterilizó el animal, se le recetó desinflamatorio y antibiótico"));
        facturaciones.add(new VeterinariaFacturacion(3, "27/10/2024", "Dr. Gómez", "Duke", 3, "Masculino", "Desparasitación", "Se aplicó al perro antiparasitario oral y externo"));
        facturaciones.add(new VeterinariaFacturacion(4, "18/08/2024", "Dr. Sánchez", "Leo", 5, "Masculino", "Cirugía", "Se realizó extracción de tumor benigno, se le recetó dieta blanda además de reposo de al menos una semana además de chequeos recurrentes por parte del dueño para ver la cicatrización de las heridas"));
        facturaciones.add(new VeterinariaFacturacion(5, "10/02/2025", "Dra. López", "Nala", 1, "Femenino", "Revisión General", "Se le hizo un chequeo completo, en el cual se demostró un estado de salud óptimo en la pequeña Nala"));
    }

    @GetMapping("/facturas")
    public List<VeterinariaFacturacion> getVeterinariaFactuacion(){
        return facturaciones;
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<?> getVeterinariaFactuacionbyId(@PathVariable int id){
        Map<Integer, VeterinariaFacturacion> mapFacturaciones = new HashMap<>();
    
        for (VeterinariaFacturacion facturacion : facturaciones) {
            mapFacturaciones.put(facturacion.getIdFactura(), facturacion);
        }

        VeterinariaFacturacion resultado = mapFacturaciones.get(id);
    
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: La factura con el id numero " + id + " no existe en el sistema");
        }
    
        return ResponseEntity.ok(resultado);

    }
    
}
