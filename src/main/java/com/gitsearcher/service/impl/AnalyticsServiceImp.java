package com.gitsearcher.service.impl;

import com.gitsearcher.entity.Contributor;
import com.gitsearcher.entity.ContributorStat;
import com.gitsearcher.entity.RepositoryCommit;
import com.gitsearcher.entity.SearchResult;
import com.gitsearcher.misc.ContributionHonor;
import com.gitsearcher.repository.SearchResultRepository;
import com.gitsearcher.service.AnalyticsService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.util.Assert.notEmpty;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */

@Service
public class AnalyticsServiceImp implements AnalyticsService{

    @Autowired
    private SearchResultRepository searchResultRepository;

    @Override
    public SearchResult calculateStats(final SearchResult searchResult) {
        final List<RepositoryCommit> repositoryCommits = searchResult.getCommits();
        notEmpty(repositoryCommits, "repositoryCommits can not be empty");
        final Map<Contributor, List<RepositoryCommit>> commitsMap = recalculate(repositoryCommits);
        final List<ContributorStat> contributorStats = calculateContributorStats(commitsMap);
        searchResult.setContributorStats(contributorStats);
        searchResult.setStats(calculateHonors(contributorStats));
        final SearchResult persistedSearchResult = searchResultRepository.save(searchResult);
        return persistedSearchResult;
    }

    private Map<Contributor, List<RepositoryCommit>> recalculate(List<RepositoryCommit> repositoryCommits) {
        final Map<Contributor, List<RepositoryCommit>> userCommitsMap = new HashMap<>();
        repositoryCommits.forEach(repositoryCommit -> {
            final Contributor commiter = repositoryCommit.getContributor();
            final List<RepositoryCommit> value = userCommitsMap.get(commiter);
            if (value == null) {
                userCommitsMap.put(commiter, Lists.newArrayList(repositoryCommit));
            } else {
                value.add(repositoryCommit);
            }
        });

        return userCommitsMap;
    }

    private List<ContributorStat> calculateContributorStats(final Map<Contributor, List<RepositoryCommit>> commitsMap){
        final List<ContributorStat> contributorStats = new ArrayList<>();
        commitsMap.forEach((contributor, repositoryCommits) -> {
            final ContributorStat contributorStat = new ContributorStat();
            repositoryCommits.forEach(repositoryCommit -> {
                final Integer additions = repositoryCommit.getAdditions();
                final Integer delitions = repositoryCommit.getDeletions();
                final Integer total = repositoryCommit.getTotal();
                contributorStat.addAddition(additions == null ? 0 : additions);
                contributorStat.addDelition(delitions == null ? 0 : delitions);
                contributorStat.addAddition(total == null ? 0 : total);
            });
            contributorStat.setContributor(contributor);
            contributorStats.add(contributorStat);
        });
        return contributorStats;
    }


    private Map<ContributionHonor, ContributorStat> calculateHonors(final List<ContributorStat> contributorStats){
        ContributorStat mage = new ContributorStat();
        ContributorStat gameChanger = new ContributorStat();
        ContributorStat oracle = new ContributorStat();
        int bestTotal = 0;
        int bestDelitionsMoreThenAdditions = 0;
        int bestAdditionsMoreThenDelitions = 0;

        for (ContributorStat contributorStat : contributorStats) {
            int additions = contributorStat.getAdditions();
            int deletions = contributorStat.getDeletions();
            int total = contributorStat.getTotal();
            int delitionsMore = (deletions-additions) < 0 ? 0 : (deletions-additions);
            int additionsMore = (additions-deletions) < 0 ? 0 : (additions-deletions);

            gameChanger = contributorStat;
            oracle = contributorStat;
            if (total > bestTotal){
                mage = contributorStat;
            }
            if(delitionsMore > bestDelitionsMoreThenAdditions){
                gameChanger = contributorStat;
            }
            if(additionsMore > bestAdditionsMoreThenDelitions){
                oracle = contributorStat;
            }
        }

        Map<ContributionHonor, ContributorStat> honorContributorStatMap = new EnumMap<>(ContributionHonor.class);
        honorContributorStatMap.put(ContributionHonor.MAGE, mage);
        honorContributorStatMap.put(ContributionHonor.GAME_CHANGER, gameChanger);
        honorContributorStatMap.put(ContributionHonor.NEW_FEATURES_ORACLE, oracle);
        return honorContributorStatMap;
    }

}
