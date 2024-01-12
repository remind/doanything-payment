package cn.doanything.basic.infrastructure.repository;

import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/12
 */
public class RepositoryTest extends BaseTestBootStarter {

    @Autowired
    private StorageObjectRepository storageObjectRepository;

    private static final String id = "9996535e07258a7bbfd8b132435c5962";

    @Test
    public void testLock() {
        StorageObject storageObject = storageObjectRepository.lock(id);
        Assert.assertNotNull(storageObject);
    }

    @Test
    public void testStore() {
        StorageObject storageObject = storageObjectRepository.load(id);
        storageObject.setDigestHash(getUUID());
        storageObject = storageObjectRepository.store(storageObject);
        Assert.assertNotNull(storageObject);
    }

    @Test
    public void testReStore() {
        StorageObject storageObject = storageObjectRepository.load(id);
        storageObject = storageObjectRepository.reStore(storageObject);
        Assert.assertNotNull(storageObject);
    }
}
