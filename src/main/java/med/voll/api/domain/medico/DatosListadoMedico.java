package med.voll.api.domain.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email, Boolean activo) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail(), medico.getActivo());     }
}

