/*
 * Copyright (c) 2017 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liulishuo.okdownload.core.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public interface DownloadConnection {

    int NO_RESPONSE_CODE = 0;

    void addHeader(String name, String value);

    /**
     * Invokes the request immediately, and blocks until the response can be processed or is in
     * error.
     */
    Connected execute() throws IOException;

    void release();

    Map<String, List<String>> getRequestProperties();

    String getRequestProperty(String key);

    interface Connected {
        int getResponseCode() throws IOException;

        InputStream getInputStream() throws IOException;


        /**
         * Returns an unmodifiable Map of the header fields. The Map keys are Strings that represent
         * the response-header field names. Each Map value is an unmodifiable List of Strings that
         * represents the corresponding field values.
         * <p>
         * The capacity of this method is similar to the {@link URLConnection#getHeaderFields()}
         *
         * @return a Map of header fields
         */
        Map<String, List<String>> getResponseHeaderFields();

        /**
         * Returns the value of the named header field, which would be the response-header field.
         * <p>
         * If called on a connection that sets the same header multiple times
         * with possibly different values, only the last value is returned.
         *
         * @param name the name of a header field.
         * @return the value of the named header field, or <code>null</code>
         * if there is no such field in the header.
         */
        String getResponseHeaderField(String name);
    }

    interface Factory {
        DownloadConnection create(String url) throws IOException;
    }
}
