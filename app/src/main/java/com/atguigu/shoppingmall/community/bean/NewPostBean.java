package com.atguigu.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 李金桐 on 2017/3/4.
 * QQ: 474297694
 * 功能: xxxx
 */

public class NewPostBean {

    /**
     * code : 200
     * msg : 璇锋眰鎴愬姛
     * result : [{"add_time":"1478935514","avatar":"/img/user_icon.png","comment_list":["浜�,鏈変换浣曡揣鐗╄川閲忛棶棰樿鍙婃椂鑱旂郴鎴戝摝","浜诧紝蹇潵鎶㈣喘鍚�"],"comments":"0","figure":"/ugc/post/img/201611/14789355052991819.jpeg","is_essence":"0","is_hot":"0","is_like":"0","is_top":"0","likes":"0","post_id":"2646","saying":"[灏氱璋疯喘鐗╄妭]鎶芥垜鎶芥垜鎶芥垜","user_id":"261219","username":"涓嬩竴涓槑澶╀付楂樺Э鎬佹椿鐫\u20ac"},{"add_time":"1478926966","avatar":"/ugc/user/avatar/14602584403004045.png","comment_list":["浜�,鏈変换浣曡揣鐗╄川閲忛棶棰樿鍙婃椂鑱旂郴鎴戝摝","浜诧紝蹇潵鎶㈣喘鍚�"],"comments":"0","figure":"/ugc/post/img/201611/14789268972077411.jpeg","is_essence":"0","is_hot":"0","is_like":"0","is_top":"0","likes":"0","post_id":"2645","saying":"鏈変竴寮�350-100鐨勪紭鎯犲埜锛屼絾鏄劅瑙夋病鏈変粈涔堟兂涔扮殑浜嗭紝姣曠珶鍚冨湡","user_id":"13956","username":"绀垮埃SAMA"},{"add_time":"1478924129","avatar":"/img/user_icon.png","comment_list":["浜�,鏈変换浣曡揣鐗╄川閲忛棶棰樿鍙婃椂鑱旂郴鎴戝摝","浜诧紝蹇潵鎶㈣喘鍚�"],"comments":"0","figure":"/ugc/post/img/201611/14789239838413293.jpeg","is_essence":"0","is_hot":"0","is_like":"0","is_top":"0","likes":"0","post_id":"2644","saying":"灏氱璋疯喘鐗╄妭銆傦紙锞夆垁锝\u20ac锛壪冧綘娌￠挶浜嗐\u20ac傜劧鑰屼綔涓轰竴涓┓閾讹紝骞舵病鏈塵oney銆傛眰灏忎粨鎶芥垜锛屾妸鎴戠殑鐖�(*麓銆傦絸*)浼犺揪鍒板惂","user_id":"527824","username":"闇�"},{"add_time":"1478921801","avatar":"/ugc/user/avatar/14664121254125921.jpeg","comment_list":[],"comments":"0","figure":"/ugc/post/img/201611/1478921704069437.jpeg","is_essence":"0","is_hot":"0","is_like":"0","is_top":"0","likes":"0","post_id":"2643","saying":"锛诲皻纭呰胺璐墿鑺傦冀鍓佹墜鍏氭潵琛ユ檼鍗曪紒(喙�>貍<喙戯級鐪嬬湅鑳戒笉鑳芥娊鍒帮紙锝∶� 鈭\u20ac 贸锝★級","user_id":"257648","username":"鏈�"},{"add_time":"1478921533","avatar":"/img/user_icon.png","comment_list":[],"comments":"0","figure":"/ugc/post/img/201611/14789215131699196.jpeg","is_essence":"0","is_hot":"0","is_like":"0","is_top":"0","likes":"1","post_id":"2642","saying":"姹傚ソ杩怮uQ","user_id":"55746","username":"PaleCrow"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * add_time : 1478935514
         * avatar : /img/user_icon.png
         * comment_list : ["浜�,鏈変换浣曡揣鐗╄川閲忛棶棰樿鍙婃椂鑱旂郴鎴戝摝","浜诧紝蹇潵鎶㈣喘鍚�"]
         * comments : 0
         * figure : /ugc/post/img/201611/14789355052991819.jpeg
         * is_essence : 0
         * is_hot : 0
         * is_like : 0
         * is_top : 0
         * likes : 0
         * post_id : 2646
         * saying : [灏氱璋疯喘鐗╄妭]鎶芥垜鎶芥垜鎶芥垜
         * user_id : 261219
         * username : 涓嬩竴涓槑澶╀付楂樺Э鎬佹椿鐫€
         */

        private String add_time;
        private String avatar;
        private String comments;
        private String figure;
        private String is_essence;
        private String is_hot;
        private String is_like;
        private String is_top;
        private String likes;
        private String post_id;
        private String saying;
        private String user_id;
        private String username;
        private List<String> comment_list;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getIs_essence() {
            return is_essence;
        }

        public void setIs_essence(String is_essence) {
            this.is_essence = is_essence;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getSaying() {
            return saying;
        }

        public void setSaying(String saying) {
            this.saying = saying;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<String> comment_list) {
            this.comment_list = comment_list;
        }
    }
}
