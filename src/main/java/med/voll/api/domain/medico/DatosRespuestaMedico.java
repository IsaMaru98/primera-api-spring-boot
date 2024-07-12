package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        DatosEspecialidad especialidad,
        DatosDireccion direccion,
        Boolean activo) {

}
