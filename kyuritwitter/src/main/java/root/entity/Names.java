package root.entity;

import javax.annotation.Generated;
import root.entity.FavoliteNames._FavoliteNames;
import root.entity.FollowNames._FollowNames;
import root.entity.InListUserNames._InListUserNames;
import root.entity.ListFollowNames._ListFollowNames;
import root.entity.MurmurNames._MurmurNames;
import root.entity.RetweetsNames._RetweetsNames;
import root.entity.TlistNames._TlistNames;
import root.entity.TuserNames._TuserNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2013/03/03 13:56:58")
public class Names {

    /**
     * {@link Favolite}の名前クラスを返します。
     * 
     * @return Favoliteの名前クラス
     */
    public static _FavoliteNames favolite() {
        return new _FavoliteNames();
    }

    /**
     * {@link Follow}の名前クラスを返します。
     * 
     * @return Followの名前クラス
     */
    public static _FollowNames follow() {
        return new _FollowNames();
    }

    /**
     * {@link InListUser}の名前クラスを返します。
     * 
     * @return InListUserの名前クラス
     */
    public static _InListUserNames inListUser() {
        return new _InListUserNames();
    }

    /**
     * {@link ListFollow}の名前クラスを返します。
     * 
     * @return ListFollowの名前クラス
     */
    public static _ListFollowNames listFollow() {
        return new _ListFollowNames();
    }

    /**
     * {@link Murmur}の名前クラスを返します。
     * 
     * @return Murmurの名前クラス
     */
    public static _MurmurNames murmur() {
        return new _MurmurNames();
    }

    /**
     * {@link Retweets}の名前クラスを返します。
     * 
     * @return Retweetsの名前クラス
     */
    public static _RetweetsNames retweets() {
        return new _RetweetsNames();
    }

    /**
     * {@link Tlist}の名前クラスを返します。
     * 
     * @return Tlistの名前クラス
     */
    public static _TlistNames tlist() {
        return new _TlistNames();
    }

    /**
     * {@link Tuser}の名前クラスを返します。
     * 
     * @return Tuserの名前クラス
     */
    public static _TuserNames tuser() {
        return new _TuserNames();
    }
}