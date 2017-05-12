package NiUnaMas.Controller;

import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
import NiUnaMas.Daos.LocationDao;
import NiUnaMas.Daos.UserDao;
import NiUnaMas.Models.*;
import NiUnaMas.Varios.Uris;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Robert on 12/05/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.ID+Uris.AUDIO)
public class AuidoController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public SuccessfulAction uploadFile(@RequestParam String id,  @RequestParam("file") MultipartFile file) {
        try{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else{
                String directory = "C:\\Users\\Robert\\Desktop\\joder\\backend\\UsersFiles\\"+user.getDni()+"-"+user.getName()+"-"+user.getPhone();
                String fileDirectory = directory +"\\"+ file.getOriginalFilename();
                new File(directory).mkdirs();

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(fileDirectory)));
                stream.write(bytes);
                stream.close();
                return new SuccessfulAction("200", "Sent successfuly.");
            }
        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }catch (Exception e){
            throw new UserDoesNotExistException(e.toString());
        }
    }

    @Autowired
    private UserDao userDao;
}
