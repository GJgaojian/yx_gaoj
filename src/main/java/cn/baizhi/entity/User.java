package cn.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private String id;
    private String username;
    private String phone;
    private String head_img;
    private String brief;
    private String wechat;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private Integer status;
    private Double score;

//    public String getCreate_date() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(create_date);
//        return format;
//    }
}
