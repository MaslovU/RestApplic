package maslov.aptitos.controller;

import maslov.aptitos.domain.Division;
import maslov.aptitos.services.DivisionsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("division")
public class DivisionsController {

    private final DivisionsService divisionsService;

    public DivisionsController(DivisionsService divisionsService) {
        this.divisionsService = divisionsService;
    }

    @GetMapping
    public List list() {
        return divisionsService.allDivisions();
    }

    @GetMapping("{id}")
    public Optional<Division> getOneDivision(@PathVariable Long id) {
        return divisionsService.oneDivision(id);
    }

    @PostMapping
    public Division createDiv(@RequestBody Division divisions) {
        return divisionsService.createNewDivision(divisions);
    }

    @PutMapping("{id}")
    public Division update(
            @PathVariable("{id}") Division divisionsFromDB,
            @RequestBody Division divisions) {
        return divisionsService.updateDivision(divisions, divisionsFromDB);
    }

    @DeleteMapping("{id}")
    public void del(@PathVariable Long id) {
        divisionsService.deleteDivision(id);
    }
}