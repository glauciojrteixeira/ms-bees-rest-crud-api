package com.bees.services;

import com.bees.services.exceptions.ClasseUtilitariaException;
import com.bees.services.exceptions.VersionAPIException;

public class VersionAPI {

    private VersionAPI() {
        throw new ClasseUtilitariaException("Classe utilitaria " + "[ "
                + VersionAPI.class.getSimpleName() + " ]");
    }

    public static String version(String versionHeader, String versionParam) {
        if (versionHeader.equals("0") && versionParam.equals("0")) {
            // O cliente não esta especificando a versão. Será aplicado a ultima versão como default
            return "0";
        } else {
            if (!versionHeader.equals("0") && !versionParam.equals("0")) {
                if (versionHeader.equals(versionParam)) {
                    return versionHeader;
                } else {
                    throw new VersionAPIException("Versão da API informada no Headers e no Params são diferentes!");
                }
            } else {
                if (versionHeader.equals("0")) {
                    return versionParam;
                } else {
                    return versionHeader;
                }
            }
        }
    }
}
