package com.bees.controllers;

import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.dtos.CarroDTO;
import com.bees.domains.dtos.CarroNewDTO;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.services.CarroService;
import com.bees.services.VersionAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    /** GET **/
    @ApiOperation(value="Busca registro por id")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CarroDTO> buscarId(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @PathVariable Integer id) {

        Carro body = carroService.buscarId(
                VersionAPI.version(versionHeader, versionParam), id);

        return ResponseEntity.ok().body(new CarroDTO(body));
    }

    @ApiOperation(value="Busca todos os registros")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CarroDTO>> buscarTodas(
            @RequestHeader(name = "code-status", defaultValue = "0", required = false) Integer codeStatus,
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam) {

        List<Carro> body = carroService.buscarTodas(codeStatus,
                VersionAPI.version(versionHeader, versionParam));

        List<CarroDTO> bodyDTO = body
                .stream()
                .map(carro -> new CarroDTO(carro))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(bodyDTO);
    }

    @ApiOperation(value="Busca todos os registros com paginação")
    @GetMapping(value = "/page",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<CarroDTO>> buscarPaginado(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "placa") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Carro> body = carroService.buscarTodasPaginado(
                VersionAPI.version(versionHeader, versionParam),
                page, linesPerPage, orderBy, direction);

        Page<CarroDTO> bodyDTO = body
                .map(carro -> new CarroDTO(carro));

        return ResponseEntity.ok().body(bodyDTO);
    }

    /** POST **/
    @ApiOperation(value="Grava registros pelo contexto do DTO")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> guardar(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestBody CarroNewDTO entityDTO) {

        // Convertendo de DTO para objeto
        Carro entity = carroService.fromDTO(
                VersionAPI.version(versionHeader, versionParam),
                entityDTO);

        entity = carroService.guardar(
                VersionAPI.version(versionHeader, versionParam),
                entity);

        /* Preparando para devolver a URI se o status for 201 */
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
