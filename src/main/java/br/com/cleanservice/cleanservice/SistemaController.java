package br.com.cleanservice.cleanservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SistemaController {

    @Autowired
    private OrdemServicoRepository repositorio;

    @PostMapping("/os")
    public OrdemServico criar(@RequestBody OrdemServico os) {
        return repositorio.save(os);
    }

    @GetMapping("/os")
    public List<OrdemServico> listar() {
        return repositorio.findAll();
    }
}