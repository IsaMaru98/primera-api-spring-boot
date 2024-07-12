package med.voll.api.domain.medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private DatosEspecialidad especialidad;
    private Direccion direccion;
    private Boolean activo;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.activo = true;
    }

    public void atualizarInformacion(DatosActualizacionMedico datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null)
            this.telefono = datos.telefono();

        if (datos.direccion() != null){
            this.direccion = direccion.actualizarDatos(datos.direccion());
        }

    }
    public void inactivar(){
        this.activo = false;

    }
}
