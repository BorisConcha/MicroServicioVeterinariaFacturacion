package duoc.semana3.facturation_vet.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;
import duoc.semana3.facturation_vet.service.VeterinariaFacturacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    private static final Logger logger = LoggerFactory.getLogger(VeterinariaFactuacionController.class);

    @GetMapping
    public CollectionModel<EntityModel<VeterinariaFacturacion>> getAllFacturas() {

        List<VeterinariaFacturacion> facturas = facturacionService.getAllFacturas();
        
        List<EntityModel<VeterinariaFacturacion>> facturasResourse = facturas.stream()
            .map(factura -> EntityModel.of(factura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturasbyId(factura.getIdFacturacion())).withSelfRel()
                ))
            .collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas());
        CollectionModel<EntityModel<VeterinariaFacturacion>> data = CollectionModel.of(facturasResourse, linkTo.withRel("facturas"));

        return data;
    }
    

    @GetMapping("/{id}")
    public EntityModel<VeterinariaFacturacion> getFacturasbyId(@PathVariable Long id) {
        logger.info("Obteniendo la factura con el id: ", id);
        Optional<VeterinariaFacturacion> facturas = facturacionService.getFacturasbyId(id);
        
        if (facturas.isPresent()){
            return EntityModel.of(facturas.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturasbyId(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));

        } else {

            throw new FacturacionNotFoundException("La factura con el id: " +id+ " no existe en el sistema");
        }
    }


    @PostMapping
    public EntityModel<VeterinariaFacturacion> createFactura(@RequestBody VeterinariaFacturacion facturacion) {

        VeterinariaFacturacion crearFactura = facturacionService.createFactura(facturacion);

        return EntityModel.of(crearFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturasbyId(crearFactura.getIdFacturacion())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
    }

    @PutMapping("/{id}")
    public EntityModel<VeterinariaFacturacion> updateFactura(@PathVariable Long id, @RequestBody VeterinariaFacturacion facturacion) {
        
        VeterinariaFacturacion updateFactura = facturacionService.updateFactura(id, facturacion);
        return EntityModel.of(updateFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturasbyId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
    }
    
    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Long id){

        facturacionService.deleteFactura(id);
    }
    
}
