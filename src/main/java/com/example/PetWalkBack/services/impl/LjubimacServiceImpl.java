package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.LjubimacEntity;
import com.example.PetWalkback.repositories.LjubimacEntityRepository;
import com.example.PetWalkback.services.LjubimacService;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
@Transactional
public class LjubimacServiceImpl extends CrudJpaService<LjubimacEntity, Integer> implements LjubimacService {

    private final LjubimacEntityRepository repository;
    private final ModelMapper modelMapper;
    private final String PHOTO_PATH = System.getProperty("user.dir") + "/src/main/resources/petPhotos";
    public LjubimacServiceImpl(ModelMapper modelMapper, LjubimacEntityRepository repository){
        super(repository, modelMapper, LjubimacEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }

    @Override
    public <T, U> T update(Integer integer, U object, Class<T> resultDtoClass) {
        if (repository.existsByImeAndIdNot(getModelMapper().map(object, getEntityClass()).getIme(), integer))
            throw new ConflictException();
        return super.update(integer, object, resultDtoClass);
    }

    @Override
    public void saveImage(byte[] data, String imageName){
        File photos = new File(PHOTO_PATH);
        File[] files=photos.listFiles();
        for(int i =0; i<files.length; i++){
            if(files[i].getName().equals(imageName))
                throw new ConflictException();
        }
        String path = PHOTO_PATH + "/" + imageName;
        try{
            File newFile = new File(path);
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(data);
            fos.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<byte[]> getImage(String imageName){
        File photos = new File(PHOTO_PATH);
        File[] files=photos.listFiles();
        for(int i =0; i<files.length; i++){
            if(files[i].getName().equals(imageName)) {
                String path = PHOTO_PATH + "/" + imageName;
                File file =  new File(path);
                byte[] base64encodedData = new byte[0];

                try {
                    base64encodedData = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                                file.getName() + "\"")
                        .body(base64encodedData);
            }
        }
        return null;
    }
}
