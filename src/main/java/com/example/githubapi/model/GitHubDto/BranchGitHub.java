package com.example.githubapi.model.GitHubDto;

import lombok.Data;
import lombok.Getter;

@Data
public class BranchGitHub {
    private final String name;
    private final String repositoryName;
}
