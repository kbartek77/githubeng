package com.example.githubapi.servicetest;

import com.example.githubapi.client.GitHubClient;
import com.example.githubapi.mapper.GitHubMapper;
import com.example.githubapi.model.AppDto.BranchApp;
import com.example.githubapi.model.AppDto.RepoApp;
import com.example.githubapi.model.Entity.GitHubRepo;
import com.example.githubapi.model.GitHubDto.BranchGitHub;
import com.example.githubapi.model.GitHubDto.RepoGitHub;
import com.example.githubapi.service.GitHubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubServiceTest {
    @Mock
    private GitHubClient gitHubClient;
    @Mock
    private GitHubMapper gitHubMapper;
    @InjectMocks
    private GitHubService gitHubService;

    @Test
    void showUserRepos_dataExist_getRepos() {
        String owner = "kbartek";
        String repo = "xxxx";

        RepoGitHub repository = new RepoGitHub(repo, "yyyy");
        List<BranchGitHub> branches = Collections.singletonList(new BranchGitHub("Master", repo));
        List<RepoGitHub> userRepos = Collections.singletonList(new RepoGitHub("fafadf", "fadfafaf"));

        when(gitHubClient.getUserOneRepo(eq(owner), eq(repo))).thenReturn(repository);
        when(gitHubClient.getAllBranches(eq(owner), eq(repo))).thenReturn(branches);
        when(gitHubClient.showUserRepos(eq(owner))).thenReturn(userRepos);

        RepoApp repoApp = new RepoApp();
        when(gitHubMapper.toRepoApp(any())).thenReturn(repoApp);
        when(gitHubMapper.toRepoAppList(anyList())).thenReturn(Collections.singletonList(repoApp));
        BranchApp branchApp = new BranchApp();
        when(gitHubMapper.toBranchAppList(anyList())).thenReturn(Collections.singletonList(branchApp));

        GitHubRepo expectedGitHubRepo = GitHubRepo.builder()
                .repos(Collections.singletonList(repoApp))
                .details(repoApp)
                .branches(Collections.singletonList(branchApp))
                .build();

        GitHubRepo result = gitHubService.showReposDetails(owner, repo);

        Assertions.assertEquals(expectedGitHubRepo, result);
    }
}
