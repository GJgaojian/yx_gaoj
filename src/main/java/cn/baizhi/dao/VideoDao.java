package cn.baizhi.dao;

import cn.baizhi.entity.Video;

import java.util.List;

public interface VideoDao {
    List<Video> findAll();

    void add(Video video);
}
