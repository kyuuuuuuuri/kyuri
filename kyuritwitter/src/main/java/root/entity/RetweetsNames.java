package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.MurmurNames._MurmurNames;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Retweets}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/05 13:26:11")
public class RetweetsNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * murmuridのプロパティ名を返します。
     * 
     * @return murmuridのプロパティ名
     */
    public static PropertyName<Integer> murmurid() {
        return new PropertyName<Integer>("murmurid");
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
     * tuserのプロパティ名を返します。
     * 
     * @return tuserのプロパティ名
     */
    public static _TuserNames tuser() {
        return new _TuserNames("tuser");
    }

    /**
     * murmurのプロパティ名を返します。
     * 
     * @return murmurのプロパティ名
     */
    public static _MurmurNames murmur() {
        return new _MurmurNames("murmur");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _RetweetsNames extends PropertyName<Retweets> {

        /**
         * インスタンスを構築します。
         */
        public _RetweetsNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _RetweetsNames(final String name) {
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
        public _RetweetsNames(final PropertyName<?> parent, final String name) {
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
         * murmuridのプロパティ名を返します。
         *
         * @return murmuridのプロパティ名
         */
        public PropertyName<Integer> murmurid() {
            return new PropertyName<Integer>(this, "murmurid");
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
         * tuserのプロパティ名を返します。
         * 
         * @return tuserのプロパティ名
         */
        public _TuserNames tuser() {
            return new _TuserNames(this, "tuser");
        }

        /**
         * murmurのプロパティ名を返します。
         * 
         * @return murmurのプロパティ名
         */
        public _MurmurNames murmur() {
            return new _MurmurNames(this, "murmur");
        }
    }
}