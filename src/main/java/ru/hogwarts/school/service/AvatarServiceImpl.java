package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repo.AvatarRepo;
import ru.hogwarts.school.repo.StudentRepo;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepo avatarRepo;
    private final StudentRepo studentRepo;

    @Value("${path.to.avatars.folder}")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepo avatarRepo, StudentRepo studentRepo) {
        this.avatarRepo = avatarRepo;
        this.studentRepo = studentRepo;
    }

    private final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepo.getById(studentId);
        Path filePath = Path.of(avatarDir, student + "." + getExtension(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilepath(filePath.toString());
        avatar.setFilSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(generateDataForDB(filePath));
        avatarRepo.save(avatar);
    }

    private byte[] generateDataForDB(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    @Override
    public Avatar findAvatar(Long studentID) {
        logger.info("Was invoked method to find Avatar");
        Avatar avatarAva = avatarRepo.findByStudentId(studentID).orElse(new Avatar());
        logger.info("Students avatar with {} found", studentID);
        return avatarAva;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method to get all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        List<Avatar> listAV = avatarRepo.findAll(pageRequest).getContent();
        logger.info("Avatars with pageNumber as {} and pageSize as {}", pageNumber, pageSize);
        return listAV;
    }

}
