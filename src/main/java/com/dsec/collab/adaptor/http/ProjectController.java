package com.dsec.collab.adaptor.http;

import com.dsec.collab.core.exception.GithubAuthenticationException;
import com.dsec.collab.core.exception.GithubRepositoryIdUsedException;
import com.dsec.collab.core.port.ProjectApi;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectApi projectApi;

    public ProjectController(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @PostMapping("/")
    public ResponseEntity<?> createProject(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody CreateProjectRequestDTO body
    ) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("sub"));
        try {
            projectApi.createProject(
                    userId,
                    body.githubRepositoryId(),
                    body.title(),
                    body.description()
            );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (GithubAuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        } catch (GithubRepositoryIdUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(
            @RequestParam(name="page", defaultValue="0") int pageNum,
            @RequestParam(name="page_size", defaultValue="50") int pageSize
    ) {
        Page<ProjectDTO> page = projectApi.getAllProjects(pageNum, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
