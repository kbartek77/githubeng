package com.example.githubapi.service;

import com.example.githubapi.Exceptions.UserNotFoundExceptions;
import com.example.githubapi.client.GitHubClient;
import com.example.githubapi.mapper.GitHubMapper;
import com.example.githubapi.model.AppDto.BranchApp;
import com.example.githubapi.model.AppDto.RepoApp;
import com.example.githubapi.model.Entity.GitHubRepo;
import com.example.githubapi.model.GitHubDto.BranchGitHub;
import com.example.githubapi.model.GitHubDto.RepoGitHub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GitHubService {
    private final GitHubClient gitHubClient;
    private final GitHubMapper gitHubMapper;

    public GitHubRepo showReposDetails(String owner, String repo) {
        RepoGitHub repository = gitHubClient.getUserOneRepo(owner, repo);
        List<BranchGitHub> branches = gitHubClient.getAllBranches(owner, repo);
        GitHubRepo gitHubRepo = GitHubRepo.builder()
                .details(gitHubMapper.toRepoApp(repository))
                .branches(gitHubMapper.toBranchAppList(branches))
                .build();
        return gitHubRepo;
    }
}
