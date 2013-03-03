package root.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * Murmurエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2013/02/28 3:52:01")
public class Murmur implements Serializable {

    private static final long serialVersionUID = 1L;

    /** useridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userid;

    /** murmuridプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer murmurid;

    /** murmurプロパティ */
    @Column(length = 180, nullable = true, unique = false)
    public String murmur;

    /** dateTimeプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp dateTime;

    /** gpslatitudeプロパティ */
    @Column(precision = 7, scale = 4, nullable = true, unique = false)
    public Double gpslatitude;

    /** gpslongitudeプロパティ */
    @Column(precision = 7, scale = 4, nullable = true, unique = false)
    public Double gpslongitude;

    /** gpslocationプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String gpslocation;

    /** beforeidプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer beforeid;

    /** afteridflagプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer afteridflag;

    /** imageurlプロパティ */
    @Column(length = 60, nullable = true, unique = false)
    public String imageurl;

    /** favoritenumプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer favoritenum;

    /** retwitflagプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer retwitflag;

    /** retweetuserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer retweetuser;

    /** tuser関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    public Tuser tuser;

    @ManyToOne
    @JoinColumn(name = "retweetuser", referencedColumnName = "userID", nullable = true)
    public Tuser retuser;

    /** favolite関連プロパティ */
    @OneToMany(mappedBy = "murmur")
    public List<Favolite> favolite;

    @OneToMany(mappedBy = "reMurmurFavo")
    public List<Favolite> favoliteReVar;

    /** retweets関連プロパティ */
    @OneToMany(mappedBy = "murmur")
    public List<Retweets> retweets;

    /* --------------   自己結合   --------------  */

    /** 自己結合beforeプロパティ*/
    @ManyToOne
    @JoinColumn(name = "beforeid", referencedColumnName = "murmurid")
    public Murmur beforeParent;

    @OneToMany(mappedBy="beforeParent")
    public List<Murmur> murmurList;

}