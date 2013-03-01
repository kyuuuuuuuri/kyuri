package root.entity;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Tlistエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2013/02/28 3:52:01")
public class Tlist implements Serializable {

    private static final long serialVersionUID = 1L;

    /** listidプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer listid;

    /** useridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userid;

    /** listnameプロパティ */
    @Column(length = 64, nullable = false, unique = false)
    public String listname;

    /** listdescプロパティ */
    @Column(length = 128, nullable = true, unique = false)
    public String listdesc;

    /** secretFlagプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer secretFlag;

    /** inListUserList関連プロパティ */
    @OneToMany(mappedBy = "tlist")
    public List<InListUser> inListUserList;

    /** listFollowList関連プロパティ */
    @OneToMany(mappedBy = "tlist")
    public List<ListFollow> listFollowList;

    /** tuser関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID")
    public Tuser tuser;
}