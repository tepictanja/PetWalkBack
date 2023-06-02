package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.exceptions.ForbiddenException;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import com.example.PetWalkback.models.enums.Role;
import com.example.PetWalkback.models.enums.UserStatus;
import com.example.PetWalkback.models.requests.*;
import com.example.PetWalkback.repositories.KorisnikEntityRepository;
import com.example.PetWalkback.services.KorisnikService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
@Transactional
public class KorisnikServiceImpl extends CrudJpaService<KorisnikEntity, Integer> implements KorisnikService{

    private final KorisnikEntityRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final String PHOTO_PATH = System.getProperty("user.dir") + "/src/main/resources/photos";
    @Value("${authorization.default.username:}")
    private String defaultUsername;
    @Value("${authorization.default.first-name:}")
    private String defaultFirstName;
    @Value("${authorization.default.last-name:}")
    private String defaultLastName;
    @Value("${authorization.default.password:}")
    private String defaultPassword;
    @Value("${authorization.default.email:}")
    private String defaultEmail;
    @Value("${authorization.default.phone-number:}")
    private String defaultPhoneNumber;

    public KorisnikServiceImpl(KorisnikEntityRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        super(repository, modelMapper, KorisnikEntity.class);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        if (repository.count() == 0) {
            KorisnikEntity userEntity = new KorisnikEntity();
            userEntity.setUsername(defaultUsername);
            userEntity.setPassword(passwordEncoder.encode(defaultPassword));
            userEntity.setEmail(defaultEmail);
            userEntity.setFirstName(defaultFirstName);
            userEntity.setLastName(defaultLastName);
            userEntity.setPhoneNumber(defaultPhoneNumber);
            userEntity.setStatus(KorisnikEntity.Status.ACTIVE);
            userEntity.setRole(Role.ADMIN);
            repository.saveAndFlush(userEntity);
        }
    }

    public void signUp(SignUpRequest request) {
        if (repository.existsByUsername(request.getUsername()))
            throw new ConflictException();
        KorisnikEntity entity = getModelMapper().map(request, KorisnikEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(KorisnikEntity.Status.ACTIVE);
        entity.setRole(Role.CUVAR);
        insert(entity, KorisnikEntity.class);
    }

    public Korisnik update(Integer id, UserUpdateRequest user) {
        if (repository.existsByUsernameAndIdNot(user.getUsername(), id))
            throw new ConflictException();
        KorisnikEntity entity = findEntityById(id);
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setPhoto(user.getPhoto());
        entity.setDescription(user.getDescription());
        return update(id, entity, Korisnik.class);
    }

    @Override
    public Korisnik updatePassword(Integer id, PasswordUpdateRequest request, String username){
        if (repository.existsByUsernameAndIdNot(username, id))
            throw new ConflictException();
        KorisnikEntity entity = findEntityById(id);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        return update(id, entity, Korisnik.class);
    }

    @Override
    public void changeStatus(Integer userId, ChangeStatusRequest request) {
        if (UserStatus.REQUESTED.equals(request.getStatus()))
            throw new ForbiddenException();
        KorisnikEntity entity = findEntityById(userId);
        entity.setStatus(getModelMapper().map(request.getStatus(), KorisnikEntity.Status.class));
        repository.saveAndFlush(entity);
    }

    @Override
    public void changeRole(Integer userId, ChangeRoleRequest request) {
        KorisnikEntity entity = findEntityById(userId);
        entity.setRole(request.getRole());
        repository.saveAndFlush(entity);
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
