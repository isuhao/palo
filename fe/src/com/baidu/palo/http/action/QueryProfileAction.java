// Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.http.action;

import com.baidu.palo.common.util.ProfileManager;
import com.baidu.palo.http.ActionController;
import com.baidu.palo.http.BaseRequest;
import com.baidu.palo.http.BaseResponse;
import com.baidu.palo.http.IllegalArgException;

import com.google.common.base.Strings;

import io.netty.handler.codec.http.HttpMethod;

public class QueryProfileAction extends WebBaseAction {

    public QueryProfileAction(ActionController controller) {
        super(controller);
    }

    public static void registerAction (ActionController controller) throws IllegalArgException {
        controller.registerHandler(HttpMethod.GET, "/query_profile", new QueryProfileAction(controller));
    }

    public void executeGet(BaseRequest request, BaseResponse response) {
        getPageHeader(request, response.getContent());
        
        String queryId = request.getSingleParameter("query_id");
        if (Strings.isNullOrEmpty(queryId)) {
            response.appendContent("");
            response.appendContent("<p class=\"text-error\"> Must specify a query_id[]</p>");
        }
        
        String queryProfileStr = ProfileManager.getInstance().getProfile(queryId);
        appendQueryPrifile(response.getContent(), queryProfileStr);
        
        getPageFooter(response.getContent());
        
        writeResponse(request, response);
    }
    
    private void appendQueryPrifile(StringBuilder buffer, String queryProfileStr) {
        buffer.append("<pre>");
        buffer.append(queryProfileStr);
        buffer.append("</pre>");
    }

}
