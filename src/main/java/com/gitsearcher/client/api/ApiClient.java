package com.gitsearcher.client.api;

import com.gitsearcher.client.InfoResource;
import com.gitsearcher.client.SearchResource;

import java.io.Closeable;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public interface ApiClient  extends Closeable {

    InfoResource info();

    SearchResource search();
}
