package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.TlistNames._TlistNames;
import root.entity.TuserNames._TuserNames;

/**
 * {@link InListUser}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/05 13:26:11")
public class InListUserNames {

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
     * listUseridのプロパティ名を返します。
     * 
     * @return listUseridのプロパティ名
     */
    public static PropertyName<Integer> listUserid() {
        return new PropertyName<Integer>("listUserid");
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
     * listのプロパティ名を返します。
     * 
     * @return listのプロパティ名
     */
    public static _TuserNames list() {
        return new _TuserNames("list");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _InListUserNames extends PropertyName<InListUser> {

        /**
         * インスタンスを構築します。
         */
        public _InListUserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _InListUserNames(final String name) {
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
        public _InListUserNames(final PropertyName<?> parent, final String name) {
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
         * listUseridのプロパティ名を返します。
         *
         * @return listUseridのプロパティ名
         */
        public PropertyName<Integer> listUserid() {
            return new PropertyName<Integer>(this, "listUserid");
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
         * listのプロパティ名を返します。
         * 
         * @return listのプロパティ名
         */
        public _TuserNames list() {
            return new _TuserNames(this, "list");
        }
    }
}