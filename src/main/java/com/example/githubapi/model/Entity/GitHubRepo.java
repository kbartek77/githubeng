package com.example.githubapi.model.Entity;

import com.example.githubapi.model.AppDto.BranchApp;
import com.example.githubapi.model.AppDto.RepoApp;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GitHubRepo {
    private List<RepoApp> repos;
    private RepoApp details;
    private List<BranchApp> branches;
}
