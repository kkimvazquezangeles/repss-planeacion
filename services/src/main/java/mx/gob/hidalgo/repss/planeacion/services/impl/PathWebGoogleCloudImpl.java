package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.OrigenEstadistica;
import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kkimvazquezangeles on 16/01/16.
 */
@Service
@Profile("googlestorage")
public class PathWebGoogleCloudImpl implements PathWebService {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "lupa.service.google.pathWeb";

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, FileOrigin fileOrigin) {
        String pathBase = PathPhoto.JUGADOR_BASE.getPath();
        String pathDefault = PathPhoto.JUGADOR_DEFAULT.getPath();
        if (fileOrigin == FileOrigin.DIR_ADMIN) {
            pathBase = PathPhoto.ARBITRO_BASE.getPath();
            pathDefault = PathPhoto.ARBITRO_DEFAULT.getPath();
        }

        if (path == null || path.isEmpty() || path.equals("novalid")) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME)
                + PathPhoto.PHOTO_BASE.getPath() + pathBase + path;
        return pathFull;
    }
}
