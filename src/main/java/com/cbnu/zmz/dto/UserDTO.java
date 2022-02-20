package com.cbnu.zmz.dto;

import com.cbnu.zmz.entity.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String token;

    private String user_id;

    private String user_addr;

    private Date user_birth;

    private String user_mail;

    private String user_name;

    private String user_pw;

    private String user_new_pw;

    private String user_text;

    private Set<UserAuthority> roleSet = new HashSet<>();

    public void addMemberRole(UserAuthority userAuthority){
        roleSet.add(userAuthority);
    }

    private String picture_id;

    private String picture_name;

    private String picture_path;

    private String friend_id;

    private int board_like_count;

    private int board_comment_count;
}
