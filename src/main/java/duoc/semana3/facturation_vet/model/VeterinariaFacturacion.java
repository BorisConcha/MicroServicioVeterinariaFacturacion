package duoc.semana3.facturation_vet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "facturacionveterinaria")
public class VeterinariaFacturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facturacion_seq")
    @SequenceGenerator(name = "facturacion_seq", sequenceName = "facturacion_seq", allocationSize = 1)
    @Column(name = "id_factura")
    private Long id_factura;

    @NotNull
    @Size(max=12)
    @Column(name = "fecha_factura")
    private String fecha_factura;

    @NotNull
    @Column(name = "nombre_veterinario")
    private String nombre_veterinario;
    
    @Column(name = "nombre_mascota")
    private String nombre_mascota;

    @Pattern(regexp = "^[0-9,$]*$")
    @Column(name = "edad_mascota")
    private int edad_mascota;

    @NotNull
    @Column(name = "genero_mascota")
    private String genero_mascota;

    @NotNull
    @Column(name = "tipo_servicio")
    private String tipo_servicio;

    @Column(name = "descripcion_servicio")
    private String descripcion_servicio;

    public Long getIdFacturacion(){
        return id_factura;
    }

    public String getFechaFactura(){
        return fecha_factura;
    }

    public String getNombreVeterinario(){
        return nombre_veterinario;
    }

    public String getNombreMascota(){
        return nombre_mascota;
    }

    public int getEdadMascota(){
        return edad_mascota;
    }

    public String getGeneroMascota(){
        return genero_mascota;
    }

    public String getTipoServicio(){
        return tipo_servicio;
    }

    public String getDescripcionServicio(){
        return descripcion_servicio;
    }

    public void setIdFacturacion(Long id_factura){ 
        this.id_factura = id_factura;
    }

    public void setFechaFactura(String fecha_factura){ 
        this.fecha_factura = fecha_factura;
    }

    public void setNombreVeterinario(String nombre_veterinario){ 
        this.nombre_veterinario = nombre_veterinario;
    }

    public void setNombreMascota(String nombre_mascota){ 
        this.nombre_mascota = nombre_mascota;
    }

    public void setEdadMascota(int edad_mascota){ 
        this.edad_mascota = edad_mascota;
    }

    public void setGeneroMascota(String genero_mascota){ 
        this.genero_mascota = genero_mascota;
    }

    public void setTipoServicio(String tipo_servicio){ 
        this.tipo_servicio = tipo_servicio;
    }

    public void setDescripcionServicio(String descripcion_servicio){ 
        this.descripcion_servicio = descripcion_servicio;
    }
}
