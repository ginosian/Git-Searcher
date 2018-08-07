package com.gitsearcher.service.component;

import com.gitsearcher.entity.Contributor;
import com.gitsearcher.entity.Repository;
import com.gitsearcher.entity.RepositoryCommit;
import com.gitsearcher.entity.SearchResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/7/18<br/>
 */

@Component
public class GitModelConverter {

    public Repository convert(final org.eclipse.egit.github.core.SearchRepository searchRepository){
        final Repository repository = new Repository();
        repository.setGitId(searchRepository.getId());
        repository.setGeneratedGitId(searchRepository.generateId());
        repository.setDescription(searchRepository.getDescription());
        repository.setName(searchRepository.getName());
        repository.setUrl(searchRepository.getUrl());
        return repository;
    }

    public Repository convert(final org.eclipse.egit.github.core.Repository gitRepository){
        final Repository repository = new Repository();
        repository.setGitId(String.valueOf(gitRepository.getId()));
        repository.setGeneratedGitId(gitRepository.generateId());
        repository.setDescription(gitRepository.getDescription());
        repository.setName(gitRepository.getName());
        repository.setUrl(gitRepository.getUrl());
        return repository;
    }

    public Contributor convert (final org.eclipse.egit.github.core.Contributor gitContributor){
        final Contributor contributor = new Contributor();
        contributor.setGitId(gitContributor.getId());
        contributor.setName(gitContributor.getName());
        contributor.setUrl(gitContributor.getUrl());
        return contributor;
    }

    public RepositoryCommit convert(final org.eclipse.egit.github.core.RepositoryCommit gitRepositoryCommit){
        final RepositoryCommit repositoryCommit = new RepositoryCommit();
        repositoryCommit.setSha(gitRepositoryCommit.getSha());
        repositoryCommit.setUrl(gitRepositoryCommit.getUrl());
        final org.eclipse.egit.github.core.CommitStats stats = gitRepositoryCommit.getStats();
        if(stats != null){
        repositoryCommit.setAdditions(stats.getAdditions());
        repositoryCommit.setDeletions(stats.getDeletions());
        repositoryCommit.setTotal(stats.getTotal());
        }
        final org.eclipse.egit.github.core.User gitCommitter = gitRepositoryCommit.getCommitter();
        if(gitCommitter != null){
            repositoryCommit.setContributor(convert(gitCommitter));
        }
        return repositoryCommit;
    }

    public Contributor convert(org.eclipse.egit.github.core.User gitContributor){
        final Contributor contributor = new Contributor();
        contributor.setGitId(gitContributor.getId());
        contributor.setName(gitContributor.getName());
        contributor.setUrl(gitContributor.getUrl());
        return contributor;
    }

    public SearchResult convert(final Repository repository, final List<Contributor> contributors, final List<RepositoryCommit> commits){
        final SearchResult searchResult = new SearchResult();
        searchResult.setRepository(repository);
        searchResult.setContributors(contributors);
        searchResult.setCommits(commits);
        return searchResult;
    }

    public List<Contributor> convertContributorList(final List<org.eclipse.egit.github.core.Contributor> gitContributors){
        return gitContributors.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<RepositoryCommit> convertCommitList(final List<org.eclipse.egit.github.core.RepositoryCommit> gitCommits){
        return gitCommits.stream().map(this::convert).collect(Collectors.toList());
    }
}
