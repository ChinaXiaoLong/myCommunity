package com.lg.community.entity;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private String avatarUrl;
}
