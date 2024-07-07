package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;
    @PostMapping()
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico ){
        System.out.println("Se recibió la información");
        System.out.println(datosRegistroMedico);
        repository.save( new Medico(datosRegistroMedico));

    }
    @GetMapping()
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size=1) Pageable pageable){
        return repository.findByActivoTrue(pageable).map(DatosListadoMedico::new);

    }
     @PutMapping
    @Transactional
     public void atualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
         var medico = repository.getReferenceById(datos.id());
         medico.atualizarInformacion(datos);
     }
//     @DeleteMapping("/{id}")
//     @Transactional
//    public void eliminarMedicoDeLaBD(@PathVariable Long id){
//        Medico medico = repository.getReferenceById(id);
//        repository.delete(medico);
//     }

    @DeleteMapping("/{id}")
    @Transactional
    public void desactivarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.inactivar();

    }


}
