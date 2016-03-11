package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.OrigenEstadistica;

/**
 * Created by kkimvazquezangeles on 16/01/16.
 */
public interface PathWebService {

    String PROPERTY_STATIC_FILE_PHOTO = "lupa.web.pathPhoto";

    String getValidPathWebFoto(String path, OriginPhoto originPhoto);

    String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica);
}
