package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;
    @PostMapping()
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Se recibió la información");
        System.out.println(datosRegistroMedico);
        Medico medico = repository.save(new Medico(datosRegistroMedico));
        DatosDireccion datosDireccion = new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(),
                medico.getDireccion().getComplemento());
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad(),datosDireccion, medico.getActivo());
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }
    @GetMapping()
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size=2) Pageable pageable){
        return ResponseEntity.ok(repository.findByActivoTrue(pageable).map(DatosListadoMedico::new));

    }
     @PutMapping
    @Transactional
     public ResponseEntity atualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
         var medico = repository.getReferenceById(datos.id());
         medico.atualizarInformacion(datos);
         DatosDireccion datosDireccion = new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento());
         DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad(),datosDireccion,medico.getActivo());
         return  ResponseEntity.ok(datosRespuestaMedico);
    }

//     @DeleteMapping("/{id}")
//     @Transactional
//    public void eliminarMedicoDeLaBD(@PathVariable Long id){
//        Medico medico = repository.getReferenceById(id);
//        repository.delete(medico);
//     }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.inactivar();
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        DatosDireccion datosDireccion = new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),medico.getDireccion().getComplemento());
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad(),datosDireccion,medico.getActivo());
        return ResponseEntity.ok(datosRespuestaMedico);

    }


}
