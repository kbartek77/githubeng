package com.example.githubapi.Controller;

import com.example.githubapi.model.Entity.GitHubRepo;
import com.example.githubapi.service.GitHubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class GitHubController {
    private final GitHubService gitHubService;
    @Operation(summary = "Get repo data from user", tags = "Repo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GitHubRepo.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "406", description = "Not Acceptable type", content = @Content)
    })
    @GetMapping("/repos/{owner}/{repo}")
    public ResponseEntity<GitHubRepo> getReposDetails(@PathVariable String owner, @PathVariable String repo) {
        GitHubRepo gitHubRepo = gitHubService.showReposDetails(owner, repo);
        return ResponseEntity.ok(gitHubRepo);
    }
}
