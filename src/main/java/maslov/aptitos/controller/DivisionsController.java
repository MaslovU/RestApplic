package maslov.aptitos.controller;

import maslov.aptitos.domain.Divisions;
import maslov.aptitos.repo.DivisionsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("division")
public class DivisionsController {

    private int counter = 1;
    private final DivisionsRepo divisionsRepo;

    @Autowired
    public DivisionsController(DivisionsRepo divisionsRepo) {
        this.divisionsRepo = divisionsRepo;
    }

    @GetMapping
    public List<Divisions> list() {
        return divisionsRepo.findAll();
    }

    @GetMapping("{id}")
    public Divisions getOneDivision(@PathVariable("id") Divisions divisions) {
        return divisions;
    }

    @PostMapping
    public Divisions createDiv(@RequestBody Divisions divisions){
        divisions.setId((long) counter++);
        return divisionsRepo.save(divisions);
    }

    @PutMapping("{id}")
    public Divisions update (
            @PathVariable("{id}") Divisions divisionsFromDB,
            @RequestBody Divisions divisions
    ){
        BeanUtils.copyProperties(divisions, divisionsFromDB, "id");
        return divisionsRepo.save(divisionsFromDB);
    }

    @DeleteMapping("{id}")
    public void del(
            @PathVariable("id") Divisions divisions
    ){
        divisionsRepo.delete(divisions);
    }
}