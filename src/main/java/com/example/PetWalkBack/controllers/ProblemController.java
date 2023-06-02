package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Izvjestaj;
import com.example.PetWalkback.models.dto.Problem;
import com.example.PetWalkback.models.requests.ProblemRequest;
import com.example.PetWalkback.services.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/problemi")
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    @GetMapping
    List<Izvjestaj> findAll() {
        return problemService.findAll(Izvjestaj.class);
    }

    @GetMapping("/{id}")
    public Izvjestaj findById(@PathVariable Integer id) throws NotFoundException {
        return problemService.findById(id, Izvjestaj.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        problemService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Problem insert(@RequestBody ProblemRequest problemRequest) throws NotFoundException {
        return problemService.insert(problemRequest, Problem.class);
    }

    @PutMapping("/{id}")
    public Problem update(@PathVariable Integer id, @Valid @RequestBody ProblemRequest problemRequest) throws NotFoundException {
        return problemService.update(id, problemRequest, Problem.class);
    }
}
