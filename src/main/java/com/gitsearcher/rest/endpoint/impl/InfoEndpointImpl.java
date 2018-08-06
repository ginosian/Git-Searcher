package com.gitsearcher.rest.endpoint.impl;

import com.gitsearcher.rest.endpoint.InfoEndpoint;
import com.gitsearcher.rest.endpoint.dto.InfoDto;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class InfoEndpointImpl implements InfoEndpoint{

    @Override
    public InfoDto info() {
        final InfoDto infoDto = new InfoDto();
        infoDto.setInfo("" +
                "1. You are able to search for a public project (also called repository) hosted on the\n" +
                " GitHub platform.\n" +
                "2. You can choose one of the projects returned by the search.\n" +
                "3. Application would show three basic types of analytics for the selected project:\n" +
                " • List of committers (users) for this project.\n" +
                " • Based on the 100 latest commits, the impact of each user on the project (based on\n" +
                " number of commits)\n" +
                " • Based on the 100 latest commits, the projection of commits on a timeline.\n" +
                "4. This result is bookmark-able for later direct access.");
        return infoDto;
    }
}
