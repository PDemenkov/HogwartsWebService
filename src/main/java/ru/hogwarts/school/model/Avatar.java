package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filepath;
    private String mediaType;
    private long filSize;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;

    public  Avatar() {
    }

    public Avatar(long id, String filepath, String mediaType, long filSize, byte[] data, Student student) {
        this.id = id;
        this.filepath = filepath;
        this.mediaType = mediaType;
        this.filSize = filSize;
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id == avatar.id && filSize == avatar.filSize && Objects.equals(filepath, avatar.filepath) && Objects.equals(mediaType, avatar.mediaType) && Arrays.equals(data, avatar.data) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filepath, mediaType, filSize, student);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filepath='" + filepath + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", filSize=" + filSize +
                ", data=" + Arrays.toString(data) +
                ", student=" + student +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getFilSize() {
        return filSize;
    }

    public void setFilSize(long filSize) {
        this.filSize = filSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
