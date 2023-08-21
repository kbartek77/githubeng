package com.example.githubapi.client;

import com.example.githubapi.Config.FeignConfig;
import com.example.githubapi.model.GitHubDto.BranchGitHub;
import com.example.githubapi.model.GitHubDto.RepoGitHub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repository-details", url = "${api.github.url}", configuration = FeignConfig.class)
public interface GitHubClient {
    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<BranchGitHub> getAllBranches(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}")
    RepoGitHub getUserOneRepo(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<RepoGitHub> showUserRepos(@PathVariable String username);
}

