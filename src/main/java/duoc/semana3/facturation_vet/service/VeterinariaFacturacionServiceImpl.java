package duoc.semana3.facturation_vet.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duoc.semana3.facturation_vet.model.VeterinariaFacturacion;
import duoc.semana3.facturation_vet.repository.VeterinariaFacturacionRepository;

@Service
public class VeterinariaFacturacionServiceImpl implements VeterinariaFacturacionService{
    @Autowired
    private VeterinariaFacturacionRepository facturacionRepository;

    private static final Logger logger = LoggerFactory.getLogger(VeterinariaFacturacionServiceImpl.class);

    @Override
    public List<VeterinariaFacturacion> getAllFacturas(){
        logger.info("Buscando las facturas del sistema");
        try{

            List<VeterinariaFacturacion> facturas = facturacionRepository.findAll();
            logger.info("Facturas encontradas con exito:",facturas);
            return facturas;

        }catch (Exception e){
            logger.info("Error al buscar las facturas en el sistema:",e);
            throw e;
        }
    }

    @Override
    public Optional<VeterinariaFacturacion> getFacturasbyId(Long id){
        logger.info("Buscando la factura en el sistema con el id:",id);
        try{

            Optional<VeterinariaFacturacion> factura = facturacionRepository.findById(id);
            logger.info("Factura encontrada:",factura);
            return factura;

        }catch (Exception e){
            logger.info("Error al buscar la factura:",e);
            throw e;
        }
    }

    @Override
    public VeterinariaFacturacion createFactura(VeterinariaFacturacion facturacion){
        logger.info("Creando una nueva factura en el sistema:",facturacion);
        try{

            VeterinariaFacturacion nueva_factura = facturacionRepository.save(facturacion);
            logger.info("Factura creada correctamente en el sistema:",nueva_factura);
            return nueva_factura;

        }catch (Exception e){
            logger.info("Error al crear una nueva factura en el sistema:",e);
            throw e;
        }
    }

    @Override
    public VeterinariaFacturacion updateFactura(Long id,VeterinariaFacturacion facturacion){
        logger.info("Buscando la factura a modificar en el sistema");
        if(facturacionRepository.existsById(id)){

            facturacion.setIdFacturacion(id);
            VeterinariaFacturacion factura = facturacionRepository.save(facturacion);
            logger.info("Factura modificada correctamente en el sistema:",factura);
            return factura;

        }else{
            logger.info("La factura a modificar con el id: "+id+" no existe en el sistema");
            return null;
        }
    }

    @Override
    public void deleteFactura(Long id){
        logger.info("Borrando la factura del sistema con el id: ",id);
        try{

            facturacionRepository.deleteById(id);
            logger.info("Factura eliminada correctamente en el sistema");

        }catch (Exception e){
            logger.info("Error al eliminar la factura en el sistema",e);
            throw e;
        }
    }
}
