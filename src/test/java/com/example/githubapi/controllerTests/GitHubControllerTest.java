package com.example.githubapi.controllerTests;
import com.example.githubapi.model.AppDto.BranchApp;

import com.example.githubapi.model.AppDto.RepoApp;
import com.example.githubapi.model.Entity.GitHubRepo;
import com.example.githubapi.service.GitHubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class GitHubControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private GitHubService gitHubService;
    @Test
    void getRepoDetail_dataExist_infoReturned() throws Exception {
        String owner = "kbartek77";
        String repo = "Medical";

        RepoApp repoApp = new RepoApp();
        RepoApp details = new RepoApp();
        BranchApp branchApp = new BranchApp();

        GitHubRepo gitHubRepo = GitHubRepo.builder()
                .repos(Collections.singletonList(repoApp))
                .details(details)
                .branches(Collections.singletonList(branchApp))
                .build();

        when(gitHubService.showReposDetails(eq(owner), eq(repo))).thenReturn(gitHubRepo);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/repos/{owner}/{repo}", owner, repo)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.repo[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.details").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.branches[0]").exists());

        verify(gitHubService, times(1)).showReposDetails(eq(owner), eq(repo));
    }
}
