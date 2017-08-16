package org.nibiru.persistence.gwt.app;

import com.gwtmobile.persistence.client.Persistence;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.config.AppName;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class GwtMobileDatabaseBootstrap implements Bootstrap {
    private final String appName;

    @Inject
    public GwtMobileDatabaseBootstrap(@AppName String appName) {
        this.appName = checkNotNull(appName);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        Deferred<Void, Exception> deferred = Deferred.defer();

        Persistence.connect(appName, appName + " database", 5 * 1024 * 1024);

        Persistence.schemaSync(() -> {
            deferred.resolve(null);
        });
        return deferred.promise();
    }
}
