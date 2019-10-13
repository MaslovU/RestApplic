package maslov.aptitos.controller;

import maslov.aptitos.domain.Divisions;
import maslov.aptitos.repo.DivisionsRepo;
import maslov.aptitos.services.DivisionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("division")
public class DivisionsController {
    private DivisionsService divisionsService;

    public DivisionsController (DivisionsRepo divisionsRepo) {
        divisionsService = new DivisionsService(divisionsRepo);
    }

    @GetMapping
    public List list() {
        return divisionsService.allDivisions();
    }

    @GetMapping("{id}")
    public Optional<Divisions> getOneDivision(@PathVariable Long id) {
        return divisionsService.oneDivision(id);
    }

    @PostMapping
    public Divisions createDiv(@RequestBody Divisions divisions){
        return divisionsService.createNewDivision(divisions);
    }

    @PutMapping("{id}")
    public Divisions update (
            @PathVariable("{id}") Divisions divisionsFromDB,
            @RequestBody Divisions divisions) {
        return divisionsService.updateDivision(divisions, divisionsFromDB);
    }

    @DeleteMapping("{id}")
    public void del(@PathVariable Long id){
        divisionsService.deleteDivision(id);
    }
}