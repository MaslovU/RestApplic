package maslov.aptitos.controller;

import maslov.aptitos.domain.Telephone;
import maslov.aptitos.services.TelephonesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("telephone")
public class TelephonesController {

    private final TelephonesService telephonesService;

    public TelephonesController(TelephonesService telephonesService) {
        this.telephonesService = telephonesService;
    }

    @GetMapping
    public List<Telephone> getAll() {
        return telephonesService.findAllTelephones();
    }

    @GetMapping("{id}")
    public Optional<Telephone> getTelephone(@PathVariable Long id) {
        return telephonesService.getOneTelephone(id);
    }

    @PostMapping
    public Telephone createTel(@RequestBody Telephone telephones) {
        return telephonesService.createTelephone(telephones);
    }

    @PutMapping("{id}")
    public Telephone changeTel(
            @PathVariable("id") Telephone telephonesFromDB,
            @RequestBody Telephone telephones) {
        return telephonesService.changeTelephones(telephones, telephonesFromDB);
    }

    @DeleteMapping("{id}")
    public void delTel(@PathVariable Long id) {
        telephonesService.deleteTelephone(id);
    }
}