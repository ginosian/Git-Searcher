package com.gitsearcher.service;

import com.gitsearcher.entity.ContributorStat;
import com.gitsearcher.misc.ContributionHonor;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;

import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */
public interface AnalyticsService {

    Map<ContributionHonor, ContributorStat> calculateStats(Map<User, List<RepositoryCommit>> commits);
}
