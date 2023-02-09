package com.bees.controllers;

import com.bees.domains.Modelo;
import com.bees.domains.dtos.ModeloDTO;
import com.bees.services.ModeloService;
import com.bees.services.VersionAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    /** GET **/
    @ApiOperation(value="Busca registro por id")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ModeloDTO> buscarId(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @PathVariable Integer id) {

        Modelo body = modeloService.buscarId(
                VersionAPI.version(versionHeader, versionParam), id);

        return ResponseEntity.ok().body(new ModeloDTO(body));
    }

    @ApiOperation(value="Busca todos os registros")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ModeloDTO>> buscarTodas(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam) {

        List<Modelo> body = modeloService.buscarTodas(
                VersionAPI.version(versionHeader, versionParam));

        List<ModeloDTO> bodyDTO = body
                .stream()
                .map(modelo -> new ModeloDTO(modelo))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(bodyDTO);
    }

    @ApiOperation(value="Busca todos os registros com paginação")
    @GetMapping(value = "/page",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<ModeloDTO>> buscarPaginado(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nomeModelo") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Modelo> body = modeloService.buscarTodasPaginado(
                VersionAPI.version(versionHeader, versionParam),
                page, linesPerPage, orderBy, direction);

        Page<ModeloDTO> bodyDTO = body
                .map(modelo -> new ModeloDTO(modelo));

        return ResponseEntity.ok().body(bodyDTO);
    }
}
