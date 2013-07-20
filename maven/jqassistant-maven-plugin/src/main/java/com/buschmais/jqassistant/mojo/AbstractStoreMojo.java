package com.buschmais.jqassistant.mojo;

import com.buschmais.jqassistant.store.api.Store;
import org.apache.maven.plugin.AbstractMojoExecutionException;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

public abstract class AbstractStoreMojo extends org.apache.maven.plugin.AbstractMojo {

    public static final String DEFAULT_STORE_DIRECTORY = "jqassistant";

    protected static interface StoreOperation<T, E extends AbstractMojoExecutionException> {
        public T run(Store store) throws E;
    }

    /**
     * The artifactId.
     *
     * @parameter expression="${project.artifactId}"
     * @readonly
     */
    protected String artifactId;


    /**
     * The project directory.
     *
     * @parameter expression="${basedir}"
     * @readonly
     */
    protected File basedir;

    /**
     * The build directory.
     *
     * @parameter expression="${project.build.directory}"
     * @readonly
     */
    protected File buildDirectory;

    /**
     * The classes directory.
     *
     * @parameter expression="${project.build.outputDirectory}"
     * @readonly
     */
    protected File classesDirectory;

    /**
     * The classes directory.
     *
     * @parameter expression="${project.build.testOutputDirectory}"
     * @readonly
     */
    protected File testClassesDirectory;

    /**
     * The build directory.
     *
     * @parameter expression="${jqassistant.store.directory}"
     * @readonly
     */
    protected File storeDirectory;

    /**
     * @component
     */
    protected StoreProvider storeProvider;

    protected <T, E extends AbstractMojoExecutionException> T executeInTransaction(StoreOperation<T, E> operation) throws E {
        final Store store = getStore();
        store.beginTransaction();
        try {
            return operation.run(store);
        } finally {
            store.endTransaction();
        }
    }

    protected <T, E extends AbstractMojoExecutionException> T execute(StoreOperation<T, E> operation) throws E {
        return operation.run(getStore());
    }

    private Store getStore() {
        File databaseDirectory;
        if (storeDirectory != null) {
            databaseDirectory = storeDirectory;
        } else {
            databaseDirectory = new File(buildDirectory, DEFAULT_STORE_DIRECTORY);
        }
        return storeProvider.getStore(databaseDirectory);
    }

}