package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.result.internal.OutputsImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.AvatarServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarServiceImpl AvatarServiceImpl) {
        this.avatarService = AvatarServiceImpl;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload an avatar", tags = "avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/avatarList")
    @Operation(summary = "list of avatars",tags = "avatar")
    ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatar = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatar);
    }

    @GetMapping(value = "/{id}/avatar/preview")
    @Operation(summary = "Preview an avatar", tags = "avatar")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    @Operation(summary = "Download an avatar", tags = "avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilepath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
             response.setStatus(200);
             response.setContentType(avatar.getMediaType());
             response.setContentLength((int) avatar.getFilSize());
             is.transferTo(os);
        }
    }
}
