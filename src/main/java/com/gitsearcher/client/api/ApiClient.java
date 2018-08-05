package com.gitsearcher.client.api;

import com.gitsearcher.client.InfoResource;
import com.gitsearcher.client.SearchResource;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public interface ApiClient {

    InfoResource info();

    SearchResource search();
}
