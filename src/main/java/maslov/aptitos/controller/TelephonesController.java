package maslov.aptitos.controller;

import maslov.aptitos.domain.Telephones;
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
    public List<Telephones> getAll() {
        return telephonesService.findAllTelephones();
    }

    @GetMapping("{id}")
    public Optional getTelephone(@PathVariable Long id) {
        return telephonesService.getOneTelephone(id);
    }

    @PostMapping
    public Telephones createTel(@RequestBody Telephones telephones) {
        return telephonesService.createTelephone(telephones);
    }

    @PutMapping("{id}")
    public Telephones changeTel(
            @PathVariable("id") Telephones telephonesFromDB,
            @RequestBody Telephones telephones) {
        return telephonesService.changeTelephones(telephones, telephonesFromDB);
    }

    @DeleteMapping("{id}")
    public void delTel(@PathVariable Long id) {
        telephonesService.deleteTelephone(id);
    }
}