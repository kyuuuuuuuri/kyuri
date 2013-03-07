package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Blockid}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/05 13:26:11")
public class BlockidNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
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
     * blockUseridのプロパティ名を返します。
     * 
     * @return blockUseridのプロパティ名
     */
    public static PropertyName<Integer> blockUserid() {
        return new PropertyName<Integer>("blockUserid");
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
     * blockのプロパティ名を返します。
     * 
     * @return blockのプロパティ名
     */
    public static _TuserNames block() {
        return new _TuserNames("block");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _BlockidNames extends PropertyName<Blockid> {

        /**
         * インスタンスを構築します。
         */
        public _BlockidNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BlockidNames(final String name) {
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
        public _BlockidNames(final PropertyName<?> parent, final String name) {
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
         * useridのプロパティ名を返します。
         *
         * @return useridのプロパティ名
         */
        public PropertyName<Integer> userid() {
            return new PropertyName<Integer>(this, "userid");
        }

        /**
         * blockUseridのプロパティ名を返します。
         *
         * @return blockUseridのプロパティ名
         */
        public PropertyName<Integer> blockUserid() {
            return new PropertyName<Integer>(this, "blockUserid");
        }

        /**
         * tuserのプロパティ名を返します。
         * 
         * @return tuserのプロパティ名
         */
        public _TuserNames tuser() {
            return new _TuserNames(this, "tuser");
        }

        /**
         * blockのプロパティ名を返します。
         * 
         * @return blockのプロパティ名
         */
        public _TuserNames block() {
            return new _TuserNames(this, "block");
        }
    }
}