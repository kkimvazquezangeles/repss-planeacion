package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.OrigenEstadistica;
import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by kkimvazquezangeles on 16/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class PathWebLocalImpl implements PathWebService {

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

        if (path == null || path.isEmpty()) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + pathBase + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return pathDefault;
        }
        return PathPhoto.PHOTO_BASE.getPath() + pathBase + path;
    }

    @Override
    public String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica) {
        String pathDefault = PathPhoto.EQUIPO_DEFAULT.getPath();
        if (origenEstadistica != null) {
            pathDefault = origenEstadistica == OrigenEstadistica.VISITA ?
                    PathPhoto.EQUIPO_DEFAULT_VISITA.getPath() : PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath();
        }
        if (path == null || path.isEmpty()) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + PathPhoto.EQUIPO_BASE.getPath() + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return pathDefault;
        }
        return PathPhoto.PHOTO_BASE.getPath() + PathPhoto.EQUIPO_BASE.getPath() + path;
    }
}
