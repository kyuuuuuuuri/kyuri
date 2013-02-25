package root.entity;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.name.PropertyName;

import root.entity.MurmurNames._MurmurNames;

/**
 * {@link Reptwitid}のプロパティ名の集合です。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/02/21 19:55:01")
public class ReptwitidNames {

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
     * aftermurmuridのプロパティ名を返します。
     *
     * @return aftermurmuridのプロパティ名
     */
    public static PropertyName<Integer> aftermurmurid() {
        return new PropertyName<Integer>("aftermurmurid");
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
    public static class _ReptwitidNames extends PropertyName<Reptwitid> {

        /**
         * インスタンスを構築します。
         */
        public _ReptwitidNames() {
        }

        /**
         * インスタンスを構築します。
         *
         * @param name
         *            名前
         */
        public _ReptwitidNames(final String name) {
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
        public _ReptwitidNames(final PropertyName<?> parent, final String name) {
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
         * aftermurmuridのプロパティ名を返します。
         *
         * @return aftermurmuridのプロパティ名
         */
        public PropertyName<Integer> aftermurmurid() {
            return new PropertyName<Integer>(this, "aftermurmurid");
        }


        /**
         * murmurのプロパティ名を返します。
         *
         * @return murmurのプロパティ名
         */
        public _MurmurNames tuser() {
            return new _MurmurNames(this, "murmur");
        }

    }
}