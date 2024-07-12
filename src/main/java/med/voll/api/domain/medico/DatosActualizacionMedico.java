package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.Direccion;

public record DatosActualizacionMedico(@NotNull Long id,
                                       String nombre,
                                       String telefono,
                                       Direccion direccion) {
}
