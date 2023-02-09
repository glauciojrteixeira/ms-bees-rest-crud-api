package com.bees.controllers;

import com.bees.domains.Marca;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.services.MarcaService;
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
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    /** GET **/
    @ApiOperation(value="Busca registro por id")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MarcaDTO> buscarId(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @PathVariable Integer id) {

        Marca body = marcaService.buscarId(
                VersionAPI.version(versionHeader, versionParam), id);

        return ResponseEntity.ok().body(new MarcaDTO(body));
    }

    @ApiOperation(value="Busca todos os registros")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MarcaDTO>> buscarTodas(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam) {

        List<Marca> body = marcaService.buscarTodas(
                VersionAPI.version(versionHeader, versionParam));

        List<MarcaDTO> bodyDTO = body
                .stream()
                .map(categoria -> new MarcaDTO(categoria))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(bodyDTO);
    }

    @ApiOperation(value="Busca todos os registros com paginação")
    @GetMapping(value = "/page",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<MarcaDTO>> buscarPaginado(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nomeMarca") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Marca> body = marcaService.buscarTodasPaginado(
                VersionAPI.version(versionHeader, versionParam),
                page, linesPerPage, orderBy, direction);

        Page<MarcaDTO> bodyDTO = body
                .map(marca -> new MarcaDTO(marca));

        return ResponseEntity.ok().body(bodyDTO);
    }

}
