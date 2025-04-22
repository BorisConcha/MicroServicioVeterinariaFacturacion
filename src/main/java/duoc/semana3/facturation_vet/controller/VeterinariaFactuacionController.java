package duoc.semana3.facturation_vet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;
import duoc.semana3.facturation_vet.service.VeterinariaFacturacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class VeterinariaFactuacionController {
    @Autowired
    private VeterinariaFacturacionService facturacionService;

    @GetMapping
    public List<VeterinariaFacturacion> getAllFacturas() {
        return facturacionService.getAllFacturas();
    }
    

    @GetMapping("/{id}")
    public Optional<VeterinariaFacturacion> getFacturasbyId(@PathVariable Long id) {
        return facturacionService.getFacturasbyId(id);
    }

    @PostMapping
    public VeterinariaFacturacion createFactura(@RequestBody VeterinariaFacturacion eventos) {
        return facturacionService.createFactura(eventos);
    }

    @PutMapping("/{id}")
    public VeterinariaFacturacion updateFactura(@PathVariable Long id, @RequestBody VeterinariaFacturacion eventos) {
        
        return facturacionService.updateFactura(id, eventos);
    }
    
    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Long id){

        facturacionService.deleteFactura(id);
    }
    
}
