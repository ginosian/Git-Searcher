package com.gitsearcher.git.service.impl;

import com.gitsearcher.git.GitClient;
import com.gitsearcher.service.impl.GitServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */
@RunWith(MockitoJUnitRunner.class)
public class GitServiceImplTest {

    @Mock
    private GitClient client;

    @InjectMocks
    private GitServiceImpl gitService;

    @Test
    public void searchRepository() {
    }


}
