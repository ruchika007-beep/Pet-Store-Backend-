package com.petStore.Controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.petStore.Entity.Pet;
import com.petStore.Service.PetService;




@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "http://localhost:5173")
public class PetController {

    @Autowired
    private PetService service;

    // Upload Folder Path
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    // =========================
    // ADD PET WITH IMAGE UPLOAD
    // =========================
    @PostMapping("/add")
    public Pet addPet(

            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("age") int age,
            @RequestParam("image") MultipartFile image

    ) throws IOException {

        // Create uploads folder if not exists
        File folder = new File(UPLOAD_DIR);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Original File Name
        String fileName =
                System.currentTimeMillis()
                + "_"
                + image.getOriginalFilename();

        // Full File Path
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Save File
        Files.copy(
        	    image.getInputStream(),
        	    filePath,
        	    java.nio.file.StandardCopyOption.REPLACE_EXISTING
        	);

        // Save Pet Data
        Pet pet = new Pet();

        pet.setName(name);
        pet.setType(type);
        pet.setAge(age);

        // Save image name in DB
        pet.setImageName(fileName);

        return service.savePet(pet);
    }

    // =========================
    // GET ALL PETS
    // =========================
    @GetMapping("/all")
    public List<Pet> getAllPets() {

        return service.getAllPets();
    }

    // =========================
    // DOWNLOAD IMAGE
    // =========================
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadImage(
            @PathVariable String fileName
    ) throws IOException {

        // File Path
        Path path = Paths.get(UPLOAD_DIR).resolve(fileName);

        // Convert to Resource
        Resource resource = new UrlResource(path.toUri());

        // Check File Exists
        if (!resource.exists()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\""
                )

                .body(resource);
    }

    // =========================
    // VIEW IMAGE IN BROWSER
    // =========================
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewImage(
            @PathVariable String fileName
    ) throws IOException {

        Path path = Paths.get(UPLOAD_DIR).resolve(fileName);

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(resource);
    }
}