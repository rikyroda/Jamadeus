/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.InputStream;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import static org.primefaces.component.tieredmenu.TieredMenu.PropertyKeys.my;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ruben_000
 */
@ManagedBean
public class DownloadManager {

    private static final Logger logger = Logger.getLogger("web.downloadManager");
    private StreamedContent file;

    public DownloadManager() {
        try {
            InputStream stream
                    = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/2_ATSE_XP.pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", "2_ATSE_XP.pdf");
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Can't download the file!",
                    logger);
        }
    }

    public StreamedContent getFile() {
        return file;
    }
}
