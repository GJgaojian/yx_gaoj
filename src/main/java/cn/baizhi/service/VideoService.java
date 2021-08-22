package cn.baizhi.service;

import cn.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    List<Video> findAll();

    void add(MultipartFile file,Video video);
}
