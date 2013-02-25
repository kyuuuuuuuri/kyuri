package root.service;

import java.util.List;
import javax.annotation.Generated;
import root.entity.Reptwitid;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.ReptwitidNames.*;

/**
 * {@link Reptwitid}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2013/02/21 19:54:46")
public class ReptwitidService extends AbstractService<Reptwitid> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Reptwitid findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Reptwitid> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}