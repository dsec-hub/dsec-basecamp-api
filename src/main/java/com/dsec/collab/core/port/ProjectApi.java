package com.dsec.collab.core.port;

import com.dsec.collab.core.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectApi {

    Project getProject(UUID projectId);
    Project createProject(UUID userId, long githubRepositoryId, String title, String description);
    Project updateProject(UUID userId, UUID projectId, String title, String description);
    void deleteProject(UUID userId, UUID projectId);

    Project setProjectFeatured(UUID projectId);
    Project setProjectCommunity(UUID projectId);

    Page<Project> getUserProjects(Pageable pageable);
    Page<Project> getCommunityProjects(Pageable pageable);
    Page<Project> getFeaturedProjects(Pageable pageable);

}
