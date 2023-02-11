package com.bees.controllers;

import com.bees.domains.Marca;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.services.MarcaService;
import com.bees.services.VersionAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    /** POST **/
    @ApiOperation(value="Grava registros pelo contexto do DTO")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> guardar(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestBody MarcaDTO entityDTO) {

        // Convertendo de DTO para objeto
        Marca entity = marcaService.fromDTO(
                VersionAPI.version(versionHeader, versionParam),
                entityDTO);

        entity = marcaService.guardar(
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

    /** PUT **/
    @ApiOperation(value="Atualiza registro por id")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> atualizar(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @RequestBody MarcaDTO entityDTO, @PathVariable Integer id) {

        // Convertendo de DTO para objeto
        Marca entity = marcaService.fromDTO(VersionAPI.version(versionHeader, versionParam), entityDTO);

        entity.setId(id);
        marcaService.atualizar(VersionAPI.version(versionHeader, versionParam), entity);

        return ResponseEntity.noContent().build();
    }

    /** DELETE **/
    @ApiOperation(value="Remove registro por id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possivel excluir uma MARCA que possui MODELO(S)!"),
            @ApiResponse(code = 404, message = "Código inexistente!")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerId(
            @RequestHeader(name = "api-version", defaultValue = "0", required = false) String versionHeader,
            @RequestParam(value = "version", defaultValue = "0", required = false) String versionParam,
            @PathVariable Integer id) {

        marcaService.remover(
                VersionAPI.version(versionHeader, versionParam), id);

        return ResponseEntity.noContent().build();
    }
}
