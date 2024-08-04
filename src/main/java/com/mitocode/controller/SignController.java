package com.mitocode.controller;

import com.mitocode.dto.SignDTO;
import com.mitocode.model.Sign;
import com.mitocode.service.ISignService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/signs")
@RequiredArgsConstructor
public class SignController {
    private final ISignService service;

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<SignDTO>> findAll() {
        List<SignDTO> list = mapperUtil.mapList(service.findAll(), SignDTO.class);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignDTO> findById(@PathVariable("id") Integer id) {
        Sign obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, SignDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SignDTO dto) {
        Sign obj = service.save(mapperUtil.map(dto, Sign.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdSign()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SignDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SignDTO dto) {
        dto.setIdSign(id);
        Sign obj = service.update(id, mapperUtil.map(dto, Sign.class));

        return ResponseEntity.ok(mapperUtil.map(obj, SignDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
