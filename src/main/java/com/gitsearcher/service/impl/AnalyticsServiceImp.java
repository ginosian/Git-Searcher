package com.gitsearcher.service.impl;

import com.gitsearcher.entity.ContributorStat;
import com.gitsearcher.misc.ContributionHonor;
import com.gitsearcher.service.AnalyticsService;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */

@Service
public class AnalyticsServiceImp implements AnalyticsService{

    @Override
    public Map<ContributionHonor, ContributorStat> calculateStats(Map<User, List<RepositoryCommit>> commits) {
        return null;
    }
}
