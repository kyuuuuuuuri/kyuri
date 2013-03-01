package root.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * ListFollowエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2013/02/28 3:52:01")
public class ListFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** listidプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer listid;

    /** followThisUseridプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer followThisUserid;

    /** tlist関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "listid", referencedColumnName = "listid")
    public Tlist tlist;

    /** followThis関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "follow_this_userid", referencedColumnName = "userID")
    public Tuser followThis;
}