package com.example.githubapi.model.GitHubDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepoGitHub {
    private final String name;
    @JsonProperty("full_name")
    private final String fullName;
}
