package duoc.semana3.facturation_vet;

public class VeterinariaFacturacion {
    private int id_factura;
    private String fecha_factura;
    private String nombre_veterinario;
    private String nombre_mascota;
    private int edad_mascota;
    private String genero_mascota;
    private String tipo_servicio;
    private String descripcion_servicio;

    public VeterinariaFacturacion(int id_factura, String fecha_factura, String nombre_veterinario, String nombre_mascota, int edad_mascota, String genero_mascota, String tipo_servicio, String descripcion_servicio){
        this.id_factura             =   id_factura;
        this.fecha_factura          =   fecha_factura;
        this.nombre_veterinario     =   nombre_veterinario;
        this.nombre_mascota         =   nombre_mascota;
        this.edad_mascota           =   edad_mascota;
        this.genero_mascota         =   genero_mascota;
        this.tipo_servicio          =   tipo_servicio;
        this.descripcion_servicio   =   descripcion_servicio;
    }

    public int getIdFactura(){
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
}
