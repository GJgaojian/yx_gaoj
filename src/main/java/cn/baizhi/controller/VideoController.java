package cn.baizhi.controller;

import cn.baizhi.entity.Video;
import cn.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService vs;

    @RequestMapping("findAll")
    public List<Video> findAll(){
        return vs.findAll();
    }
    @RequestMapping("add")
    public void add(MultipartFile video,String title,String brief){
//        System.out.println(title);
//        System.out.println(brief);
//        System.out.println(video);
        vs.add(video,new Video(null,title,brief,null,null,null,null,null,null));
    }

}
