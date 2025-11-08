package repository;

import java.io.File;

/**
 * Base cho các repo CSV.
 * TODO:
 *  - giữ File data
 */
public abstract class baseRepository {
    protected final File file;
    protected baseRepository(File f){ this.file = f; }
}
