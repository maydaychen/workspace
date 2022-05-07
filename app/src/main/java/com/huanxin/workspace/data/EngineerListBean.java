package com.huanxin.workspace.data;

import java.util.List;

public class EngineerListBean {
    /**
     * code
     */
    private Integer code;
    /**
     * data
     */
    private List<DataBean> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sort
         */
        private Integer sort;
        /**
         * createBy
         */
        private String createBy;
        /**
         * createTime
         */
        private String createTime;
        /**
         * params
         */
        private ParamsBean params;
        /**
         * userId
         */
        private String userId;
        /**
         * deptId
         */
        private String deptId;
        /**
         * postId
         */
        private String postId;
        /**
         * userCode
         */
        private String userCode;
        /**
         * userName
         */
        private String userName;
        /**
         * userType
         */
        private String userType;
        /**
         * nickName
         */
        private String nickName;
        /**
         * email
         */
        private String email;
        /**
         * phone
         */
        private String phone;
        /**
         * sex
         */
        private String sex;
        /**
         * avatar
         */
        private String avatar;
        /**
         * profile
         */
        private String profile;
        /**
         * password
         */
        private String password;
        /**
         * status
         */
        private String status;
        /**
         * loginIp
         */
        private String loginIp;
        /**
         * dept
         */
        private DeptBean dept;
        /**
         * post
         */
        private PostBean post;
        /**
         * roles
         */
        private List<?> roles;
        /**
         * priority
         */
        private Integer priority;
        /**
         * admin
         */
        private Boolean admin;

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public DeptBean getDept() {
            return dept;
        }

        public void setDept(DeptBean dept) {
            this.dept = dept;
        }

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Boolean getAdmin() {
            return admin;
        }

        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        public static class ParamsBean {
        }

        public static class DeptBean {
            /**
             * params
             */
            private ParamsBean params;
            /**
             * deptId
             */
            private String deptId;
            /**
             * phone
             */
            private String phone;
            /**
             * email
             */
            private String email;

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public static class ParamsBean {
            }
        }

        public static class PostBean {
            /**
             * params
             */
            private ParamsBean params;
            /**
             * postId
             */
            private String postId;
            /**
             * deptId
             */
            private String deptId;
            /**
             * flag
             */
            private Boolean flag;

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public String getPostId() {
                return postId;
            }

            public void setPostId(String postId) {
                this.postId = postId;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public Boolean getFlag() {
                return flag;
            }

            public void setFlag(Boolean flag) {
                this.flag = flag;
            }

            public static class ParamsBean {
            }
        }
    }
}
