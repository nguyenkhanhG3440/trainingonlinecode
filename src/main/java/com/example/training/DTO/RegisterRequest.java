package com.example.training.DTO;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Long roleId; // nếu có bảng Role liên kết
}
