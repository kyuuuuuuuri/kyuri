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
 * Reptwitidエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2013/02/21 19:54:37")
public class Reptwitid implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** murmuridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer murmurid;

    /** aftermurmuridプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer aftermurmurid;

    /** murmur関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "murmurid", referencedColumnName = "murmurid")
    public Murmur murmur;
}