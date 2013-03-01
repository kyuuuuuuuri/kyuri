package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.TlistNames._TlistNames;
import root.entity.TuserNames._TuserNames;

/**
 * {@link ListFollow}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/02/28 3:52:23")
public class ListFollowNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * listidのプロパティ名を返します。
     * 
     * @return listidのプロパティ名
     */
    public static PropertyName<Integer> listid() {
        return new PropertyName<Integer>("listid");
    }

    /**
     * followThisUseridのプロパティ名を返します。
     * 
     * @return followThisUseridのプロパティ名
     */
    public static PropertyName<Integer> followThisUserid() {
        return new PropertyName<Integer>("followThisUserid");
    }

    /**
     * tlistのプロパティ名を返します。
     * 
     * @return tlistのプロパティ名
     */
    public static _TlistNames tlist() {
        return new _TlistNames("tlist");
    }

    /**
     * followThisのプロパティ名を返します。
     * 
     * @return followThisのプロパティ名
     */
    public static _TuserNames followThis() {
        return new _TuserNames("followThis");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _ListFollowNames extends PropertyName<ListFollow> {

        /**
         * インスタンスを構築します。
         */
        public _ListFollowNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ListFollowNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _ListFollowNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Integer> id() {
            return new PropertyName<Integer>(this, "id");
        }

        /**
         * listidのプロパティ名を返します。
         *
         * @return listidのプロパティ名
         */
        public PropertyName<Integer> listid() {
            return new PropertyName<Integer>(this, "listid");
        }

        /**
         * followThisUseridのプロパティ名を返します。
         *
         * @return followThisUseridのプロパティ名
         */
        public PropertyName<Integer> followThisUserid() {
            return new PropertyName<Integer>(this, "followThisUserid");
        }

        /**
         * tlistのプロパティ名を返します。
         * 
         * @return tlistのプロパティ名
         */
        public _TlistNames tlist() {
            return new _TlistNames(this, "tlist");
        }

        /**
         * followThisのプロパティ名を返します。
         * 
         * @return followThisのプロパティ名
         */
        public _TuserNames followThis() {
            return new _TuserNames(this, "followThis");
        }
    }
}