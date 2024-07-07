package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.Direccion;

public record DatosActualizacionMedico(@NotNull Long id,
                                       String nombre,
                                       String telefono,
                                       Direccion direccion) {
}
