package com.bees.controllers;

import com.bees.domains.Marca;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.services.MarcaService;
import com.bees.services.VersionAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // Antes do response, converte o objeto de dominio em um DTO
        return ResponseEntity.ok().body(new MarcaDTO(body));
    }
}
