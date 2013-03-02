package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.InListUserNames._InListUserNames;
import root.entity.ListFollowNames._ListFollowNames;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Tlist}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/02 19:05:42")
public class TlistNames {

    /**
     * listidのプロパティ名を返します。
     * 
     * @return listidのプロパティ名
     */
    public static PropertyName<Integer> listid() {
        return new PropertyName<Integer>("listid");
    }

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
    }

    /**
     * listnameのプロパティ名を返します。
     * 
     * @return listnameのプロパティ名
     */
    public static PropertyName<String> listname() {
        return new PropertyName<String>("listname");
    }

    /**
     * listdescのプロパティ名を返します。
     * 
     * @return listdescのプロパティ名
     */
    public static PropertyName<String> listdesc() {
        return new PropertyName<String>("listdesc");
    }

    /**
     * secretFlagのプロパティ名を返します。
     * 
     * @return secretFlagのプロパティ名
     */
    public static PropertyName<Integer> secretFlag() {
        return new PropertyName<Integer>("secretFlag");
    }

    /**
     * inListUserListのプロパティ名を返します。
     * 
     * @return inListUserListのプロパティ名
     */
    public static _InListUserNames inListUserList() {
        return new _InListUserNames("inListUserList");
    }

    /**
     * listFollowListのプロパティ名を返します。
     * 
     * @return listFollowListのプロパティ名
     */
    public static _ListFollowNames listFollowList() {
        return new _ListFollowNames("listFollowList");
    }

    /**
     * tuserのプロパティ名を返します。
     * 
     * @return tuserのプロパティ名
     */
    public static _TuserNames tuser() {
        return new _TuserNames("tuser");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TlistNames extends PropertyName<Tlist> {

        /**
         * インスタンスを構築します。
         */
        public _TlistNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TlistNames(final String name) {
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
        public _TlistNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * useridのプロパティ名を返します。
         *
         * @return useridのプロパティ名
         */
        public PropertyName<Integer> userid() {
            return new PropertyName<Integer>(this, "userid");
        }

        /**
         * listnameのプロパティ名を返します。
         *
         * @return listnameのプロパティ名
         */
        public PropertyName<String> listname() {
            return new PropertyName<String>(this, "listname");
        }

        /**
         * listdescのプロパティ名を返します。
         *
         * @return listdescのプロパティ名
         */
        public PropertyName<String> listdesc() {
            return new PropertyName<String>(this, "listdesc");
        }

        /**
         * secretFlagのプロパティ名を返します。
         *
         * @return secretFlagのプロパティ名
         */
        public PropertyName<Integer> secretFlag() {
            return new PropertyName<Integer>(this, "secretFlag");
        }

        /**
         * inListUserListのプロパティ名を返します。
         * 
         * @return inListUserListのプロパティ名
         */
        public _InListUserNames inListUserList() {
            return new _InListUserNames(this, "inListUserList");
        }

        /**
         * listFollowListのプロパティ名を返します。
         * 
         * @return listFollowListのプロパティ名
         */
        public _ListFollowNames listFollowList() {
            return new _ListFollowNames(this, "listFollowList");
        }

        /**
         * tuserのプロパティ名を返します。
         * 
         * @return tuserのプロパティ名
         */
        public _TuserNames tuser() {
            return new _TuserNames(this, "tuser");
        }
    }
}