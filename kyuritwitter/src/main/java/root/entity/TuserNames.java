package root.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import root.entity.FavoliteNames._FavoliteNames;
import root.entity.FollowNames._FollowNames;
import root.entity.InListUserNames._InListUserNames;
import root.entity.ListFollowNames._ListFollowNames;
import root.entity.MurmurNames._MurmurNames;
import root.entity.RetweetsNames._RetweetsNames;
import root.entity.TlistNames._TlistNames;

/**
 * {@link Tuser}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2013/03/05 13:26:11")
public class TuserNames {

    /**
     * useridのプロパティ名を返します。
     * 
     * @return useridのプロパティ名
     */
    public static PropertyName<Integer> userid() {
        return new PropertyName<Integer>("userid");
    }

    /**
     * usernameのプロパティ名を返します。
     * 
     * @return usernameのプロパティ名
     */
    public static PropertyName<String> username() {
        return new PropertyName<String>("username");
    }

    /**
     * usernickのプロパティ名を返します。
     * 
     * @return usernickのプロパティ名
     */
    public static PropertyName<String> usernick() {
        return new PropertyName<String>("usernick");
    }

    /**
     * passWordのプロパティ名を返します。
     * 
     * @return passWordのプロパティ名
     */
    public static PropertyName<String> passWord() {
        return new PropertyName<String>("passWord");
    }

    /**
     * mailadのプロパティ名を返します。
     * 
     * @return mailadのプロパティ名
     */
    public static PropertyName<String> mailad() {
        return new PropertyName<String>("mailad");
    }

    /**
     * skeyのプロパティ名を返します。
     * 
     * @return skeyのプロパティ名
     */
    public static PropertyName<Integer> skey() {
        return new PropertyName<Integer>("skey");
    }

    /**
     * postNumのプロパティ名を返します。
     * 
     * @return postNumのプロパティ名
     */
    public static PropertyName<Integer> postNum() {
        return new PropertyName<Integer>("postNum");
    }

    /**
     * followのプロパティ名を返します。
     * 
     * @return followのプロパティ名
     */
    public static PropertyName<Integer> follow() {
        return new PropertyName<Integer>("follow");
    }

    /**
     * followedのプロパティ名を返します。
     * 
     * @return followedのプロパティ名
     */
    public static PropertyName<Integer> followed() {
        return new PropertyName<Integer>("followed");
    }

    /**
     * newMurのプロパティ名を返します。
     * 
     * @return newMurのプロパティ名
     */
    public static PropertyName<String> newMur() {
        return new PropertyName<String>("newMur");
    }

    /**
     * newMurDのプロパティ名を返します。
     * 
     * @return newMurDのプロパティ名
     */
    public static PropertyName<Timestamp> newMurD() {
        return new PropertyName<Timestamp>("newMurD");
    }

    /**
     * profileimgのプロパティ名を返します。
     * 
     * @return profileimgのプロパティ名
     */
    public static PropertyName<byte[]> profileimg() {
        return new PropertyName<byte[]>("profileimg");
    }

    /**
     * secretquestionのプロパティ名を返します。
     * 
     * @return secretquestionのプロパティ名
     */
    public static PropertyName<String> secretquestion() {
        return new PropertyName<String>("secretquestion");
    }

    /**
     * secretanswerのプロパティ名を返します。
     * 
     * @return secretanswerのプロパティ名
     */
    public static PropertyName<String> secretanswer() {
        return new PropertyName<String>("secretanswer");
    }

    /**
     * followListのプロパティ名を返します。
     * 
     * @return followListのプロパティ名
     */
    public static _FollowNames followList() {
        return new _FollowNames("followList");
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
     * murmurListのプロパティ名を返します。
     * 
     * @return murmurListのプロパティ名
     */
    public static _MurmurNames murmurList() {
        return new _MurmurNames("murmurList");
    }

    /**
     * tlistListのプロパティ名を返します。
     * 
     * @return tlistListのプロパティ名
     */
    public static _TlistNames tlistList() {
        return new _TlistNames("tlistList");
    }

    /**
     * ffollowListのプロパティ名を返します。
     * 
     * @return ffollowListのプロパティ名
     */
    public static _FollowNames ffollowList() {
        return new _FollowNames("ffollowList");
    }

    /**
     * favoliteListのプロパティ名を返します。
     * 
     * @return favoliteListのプロパティ名
     */
    public static _FavoliteNames favoliteList() {
        return new _FavoliteNames("favoliteList");
    }

    /**
     * retweetsListのプロパティ名を返します。
     * 
     * @return retweetsListのプロパティ名
     */
    public static _RetweetsNames retweetsList() {
        return new _RetweetsNames("retweetsList");
    }

    /**
     * reMurmurListのプロパティ名を返します。
     * 
     * @return reMurmurListのプロパティ名
     */
    public static _MurmurNames reMurmurList() {
        return new _MurmurNames("reMurmurList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TuserNames extends PropertyName<Tuser> {

        /**
         * インスタンスを構築します。
         */
        public _TuserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TuserNames(final String name) {
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
        public _TuserNames(final PropertyName<?> parent, final String name) {
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
         * usernameのプロパティ名を返します。
         *
         * @return usernameのプロパティ名
         */
        public PropertyName<String> username() {
            return new PropertyName<String>(this, "username");
        }

        /**
         * usernickのプロパティ名を返します。
         *
         * @return usernickのプロパティ名
         */
        public PropertyName<String> usernick() {
            return new PropertyName<String>(this, "usernick");
        }

        /**
         * passWordのプロパティ名を返します。
         *
         * @return passWordのプロパティ名
         */
        public PropertyName<String> passWord() {
            return new PropertyName<String>(this, "passWord");
        }

        /**
         * mailadのプロパティ名を返します。
         *
         * @return mailadのプロパティ名
         */
        public PropertyName<String> mailad() {
            return new PropertyName<String>(this, "mailad");
        }

        /**
         * skeyのプロパティ名を返します。
         *
         * @return skeyのプロパティ名
         */
        public PropertyName<Integer> skey() {
            return new PropertyName<Integer>(this, "skey");
        }

        /**
         * postNumのプロパティ名を返します。
         *
         * @return postNumのプロパティ名
         */
        public PropertyName<Integer> postNum() {
            return new PropertyName<Integer>(this, "postNum");
        }

        /**
         * followのプロパティ名を返します。
         *
         * @return followのプロパティ名
         */
        public PropertyName<Integer> follow() {
            return new PropertyName<Integer>(this, "follow");
        }

        /**
         * followedのプロパティ名を返します。
         *
         * @return followedのプロパティ名
         */
        public PropertyName<Integer> followed() {
            return new PropertyName<Integer>(this, "followed");
        }

        /**
         * newMurのプロパティ名を返します。
         *
         * @return newMurのプロパティ名
         */
        public PropertyName<String> newMur() {
            return new PropertyName<String>(this, "newMur");
        }

        /**
         * newMurDのプロパティ名を返します。
         *
         * @return newMurDのプロパティ名
         */
        public PropertyName<Timestamp> newMurD() {
            return new PropertyName<Timestamp>(this, "newMurD");
        }

        /**
         * profileimgのプロパティ名を返します。
         *
         * @return profileimgのプロパティ名
         */
        public PropertyName<byte[]> profileimg() {
            return new PropertyName<byte[]>(this, "profileimg");
        }

        /**
         * secretquestionのプロパティ名を返します。
         *
         * @return secretquestionのプロパティ名
         */
        public PropertyName<String> secretquestion() {
            return new PropertyName<String>(this, "secretquestion");
        }

        /**
         * secretanswerのプロパティ名を返します。
         *
         * @return secretanswerのプロパティ名
         */
        public PropertyName<String> secretanswer() {
            return new PropertyName<String>(this, "secretanswer");
        }

        /**
         * followListのプロパティ名を返します。
         * 
         * @return followListのプロパティ名
         */
        public _FollowNames followList() {
            return new _FollowNames(this, "followList");
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
         * murmurListのプロパティ名を返します。
         * 
         * @return murmurListのプロパティ名
         */
        public _MurmurNames murmurList() {
            return new _MurmurNames(this, "murmurList");
        }

        /**
         * tlistListのプロパティ名を返します。
         * 
         * @return tlistListのプロパティ名
         */
        public _TlistNames tlistList() {
            return new _TlistNames(this, "tlistList");
        }

        /**
         * ffollowListのプロパティ名を返します。
         * 
         * @return ffollowListのプロパティ名
         */
        public _FollowNames ffollowList() {
            return new _FollowNames(this, "ffollowList");
        }

        /**
         * favoliteListのプロパティ名を返します。
         * 
         * @return favoliteListのプロパティ名
         */
        public _FavoliteNames favoliteList() {
            return new _FavoliteNames(this, "favoliteList");
        }

        /**
         * retweetsListのプロパティ名を返します。
         * 
         * @return retweetsListのプロパティ名
         */
        public _RetweetsNames retweetsList() {
            return new _RetweetsNames(this, "retweetsList");
        }

        /**
         * reMurmurListのプロパティ名を返します。
         * 
         * @return reMurmurListのプロパティ名
         */
        public _MurmurNames reMurmurList() {
            return new _MurmurNames(this, "reMurmurList");
        }
    }
}