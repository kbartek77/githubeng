package com.example.githubapi.mapper;

import com.example.githubapi.model.AppDto.BranchApp;
import com.example.githubapi.model.AppDto.RepoApp;
import com.example.githubapi.model.GitHubDto.BranchGitHub;
import com.example.githubapi.model.GitHubDto.RepoGitHub;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitHubMapper {
    BranchApp toBranchApp (BranchGitHub branchGitHub);
    List<BranchApp> toBranchAppList(List<BranchGitHub> branches);
    RepoApp toRepoApp(RepoGitHub repo);
    List<RepoApp> toRepoAppList (List<RepoGitHub> repos);
}
