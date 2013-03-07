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
 * Blockidエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2013/03/05 13:25:50")
public class Blockid implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** useridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userid;

    /** blockUseridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer blockUserid;

    /** tuser関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID")
    public Tuser tuser;

    /** block関連プロパティ */
    @ManyToOne
    public Tuser block;
}