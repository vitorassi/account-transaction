package com.anderson.application.adapter.http.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IController {

    private static final String CONTEXT_VERSION_ONE = "/v1";

    public static final String CONTEXT_ACCOUNT = CONTEXT_VERSION_ONE + "/account";

    public static final String CONTEXT_TRANSACTION = CONTEXT_VERSION_ONE + "/transaction";

}
