package root.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.FavoliteNames._FavoliteNames;
import root.entity.MurmurNames._MurmurNames;
import root.entity.TuserNames._TuserNames;

/**
 * {@link Murmur}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/02 19:05:42")
public class MurmurNames {

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
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
     * murmurのプロパティ名を返します。
     * 
     * @return murmurのプロパティ名
     */
    public static PropertyName<String> murmur() {
        return new PropertyName<String>("murmur");
    }

    /**
     * dateTimeのプロパティ名を返します。
     * 
     * @return dateTimeのプロパティ名
     */
    public static PropertyName<Timestamp> dateTime() {
        return new PropertyName<Timestamp>("dateTime");
    }

    /**
     * gpslatitudeのプロパティ名を返します。
     * 
     * @return gpslatitudeのプロパティ名
     */
    public static PropertyName<Double> gpslatitude() {
        return new PropertyName<Double>("gpslatitude");
    }

    /**
     * gpslongitudeのプロパティ名を返します。
     * 
     * @return gpslongitudeのプロパティ名
     */
    public static PropertyName<Double> gpslongitude() {
        return new PropertyName<Double>("gpslongitude");
    }

    /**
     * gpslocationのプロパティ名を返します。
     * 
     * @return gpslocationのプロパティ名
     */
    public static PropertyName<String> gpslocation() {
        return new PropertyName<String>("gpslocation");
    }

    /**
     * beforeidのプロパティ名を返します。
     * 
     * @return beforeidのプロパティ名
     */
    public static PropertyName<Integer> beforeid() {
        return new PropertyName<Integer>("beforeid");
    }

    /**
     * afteridflagのプロパティ名を返します。
     * 
     * @return afteridflagのプロパティ名
     */
    public static PropertyName<Integer> afteridflag() {
        return new PropertyName<Integer>("afteridflag");
    }

    /**
     * imageurlのプロパティ名を返します。
     * 
     * @return imageurlのプロパティ名
     */
    public static PropertyName<String> imageurl() {
        return new PropertyName<String>("imageurl");
    }

    /**
     * favoritenumのプロパティ名を返します。
     * 
     * @return favoritenumのプロパティ名
     */
    public static PropertyName<Integer> favoritenum() {
        return new PropertyName<Integer>("favoritenum");
    }

    /**
     * retwitflagのプロパティ名を返します。
     * 
     * @return retwitflagのプロパティ名
     */
    public static PropertyName<Integer> retwitflag() {
        return new PropertyName<Integer>("retwitflag");
    }

    /**
     * beRetwitednumのプロパティ名を返します。
     * 
     * @return beRetwitednumのプロパティ名
     */
    public static PropertyName<Integer> beRetwitednum() {
        return new PropertyName<Integer>("beRetwitednum");
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
     * favoliteのプロパティ名を返します。
     * 
     * @return favoliteのプロパティ名
     */
    public static _FavoliteNames favolite() {
        return new _FavoliteNames("favolite");
    }

    /**
     * beforeParentのプロパティ名を返します。
     * 
     * @return beforeParentのプロパティ名
     */
    public static _MurmurNames beforeParent() {
        return new _MurmurNames("beforeParent");
    }

    /**
     * murmurListのプロパティ名を返します。
     * 
     * @return murmurListのプロパティ名
     */
    public static _MurmurNames murmurList() {
        return new _MurmurNames("murmurList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MurmurNames extends PropertyName<Murmur> {

        /**
         * インスタンスを構築します。
         */
        public _MurmurNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MurmurNames(final String name) {
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
        public _MurmurNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * murmuridのプロパティ名を返します。
         *
         * @return murmuridのプロパティ名
         */
        public PropertyName<Integer> murmurid() {
            return new PropertyName<Integer>(this, "murmurid");
        }

        /**
         * murmurのプロパティ名を返します。
         *
         * @return murmurのプロパティ名
         */
        public PropertyName<String> murmur() {
            return new PropertyName<String>(this, "murmur");
        }

        /**
         * dateTimeのプロパティ名を返します。
         *
         * @return dateTimeのプロパティ名
         */
        public PropertyName<Timestamp> dateTime() {
            return new PropertyName<Timestamp>(this, "dateTime");
        }

        /**
         * gpslatitudeのプロパティ名を返します。
         *
         * @return gpslatitudeのプロパティ名
         */
        public PropertyName<Double> gpslatitude() {
            return new PropertyName<Double>(this, "gpslatitude");
        }

        /**
         * gpslongitudeのプロパティ名を返します。
         *
         * @return gpslongitudeのプロパティ名
         */
        public PropertyName<Double> gpslongitude() {
            return new PropertyName<Double>(this, "gpslongitude");
        }

        /**
         * gpslocationのプロパティ名を返します。
         *
         * @return gpslocationのプロパティ名
         */
        public PropertyName<String> gpslocation() {
            return new PropertyName<String>(this, "gpslocation");
        }

        /**
         * beforeidのプロパティ名を返します。
         *
         * @return beforeidのプロパティ名
         */
        public PropertyName<Integer> beforeid() {
            return new PropertyName<Integer>(this, "beforeid");
        }

        /**
         * afteridflagのプロパティ名を返します。
         *
         * @return afteridflagのプロパティ名
         */
        public PropertyName<Integer> afteridflag() {
            return new PropertyName<Integer>(this, "afteridflag");
        }

        /**
         * imageurlのプロパティ名を返します。
         *
         * @return imageurlのプロパティ名
         */
        public PropertyName<String> imageurl() {
            return new PropertyName<String>(this, "imageurl");
        }

        /**
         * favoritenumのプロパティ名を返します。
         *
         * @return favoritenumのプロパティ名
         */
        public PropertyName<Integer> favoritenum() {
            return new PropertyName<Integer>(this, "favoritenum");
        }

        /**
         * retwitflagのプロパティ名を返します。
         *
         * @return retwitflagのプロパティ名
         */
        public PropertyName<Integer> retwitflag() {
            return new PropertyName<Integer>(this, "retwitflag");
        }

        /**
         * beRetwitednumのプロパティ名を返します。
         *
         * @return beRetwitednumのプロパティ名
         */
        public PropertyName<Integer> beRetwitednum() {
            return new PropertyName<Integer>(this, "beRetwitednum");
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
         * favoliteのプロパティ名を返します。
         * 
         * @return favoliteのプロパティ名
         */
        public _FavoliteNames favolite() {
            return new _FavoliteNames(this, "favolite");
        }

        /**
         * beforeParentのプロパティ名を返します。
         * 
         * @return beforeParentのプロパティ名
         */
        public _MurmurNames beforeParent() {
            return new _MurmurNames(this, "beforeParent");
        }

        /**
         * murmurListのプロパティ名を返します。
         * 
         * @return murmurListのプロパティ名
         */
        public _MurmurNames murmurList() {
            return new _MurmurNames(this, "murmurList");
        }
    }
}