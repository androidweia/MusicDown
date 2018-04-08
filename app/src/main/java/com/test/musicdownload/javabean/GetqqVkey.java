package com.test.musicdownload.javabean;

import java.util.List;

public class GetqqVkey {

    /**
     * code : 0
     * cid : 205361747
     * userip : 118.190.20.240
     * data : {"expiration":80400,"items":[{"subcode":0,"songmid":"001J5QJL1pRQYB","filename":"C400001J5QJL1pRQYB.m4a","vkey":"E25A28295B731ADBE2B6DECC6934BE1C01BDCF21A9549B3D0AF9551E6B14E29DCC3EAF88D79FAFDDA1DC1D47CC06B5B1911508541E3A002B"}]}
     */

    private int code;
    private int cid;
    private String userip;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * expiration : 80400
         * items : [{"subcode":0,"songmid":"001J5QJL1pRQYB","filename":"C400001J5QJL1pRQYB.m4a","vkey":"E25A28295B731ADBE2B6DECC6934BE1C01BDCF21A9549B3D0AF9551E6B14E29DCC3EAF88D79FAFDDA1DC1D47CC06B5B1911508541E3A002B"}]
         */

        private int expiration;
        private List<ItemsBean> items;

        public int getExpiration() {
            return expiration;
        }

        public void setExpiration(int expiration) {
            this.expiration = expiration;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * subcode : 0
             * songmid : 001J5QJL1pRQYB
             * filename : C400001J5QJL1pRQYB.m4a
             * vkey : E25A28295B731ADBE2B6DECC6934BE1C01BDCF21A9549B3D0AF9551E6B14E29DCC3EAF88D79FAFDDA1DC1D47CC06B5B1911508541E3A002B
             */

            private int subcode;
            private String songmid;
            private String filename;
            private String vkey;

            public int getSubcode() {
                return subcode;
            }

            public void setSubcode(int subcode) {
                this.subcode = subcode;
            }

            public String getSongmid() {
                return songmid;
            }

            public void setSongmid(String songmid) {
                this.songmid = songmid;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getVkey() {
                return vkey;
            }

            public void setVkey(String vkey) {
                this.vkey = vkey;
            }
        }
    }
}
