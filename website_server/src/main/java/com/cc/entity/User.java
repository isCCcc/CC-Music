package com.cc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 
     */
    private String uname;

    /**
     * 
     */
    private String upassword;

    /**
     * 
     */
    private String uemail;

    /**
     * 
     */
    private String usex;

    /**
     * 
     */
    private String uphone;

    /**
     * 
     */
    private String upic;

    /**
     * 
     */
    private String uinfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUname() == null ? other.getUname() == null : this.getUname().equals(other.getUname()))
            && (this.getUpassword() == null ? other.getUpassword() == null : this.getUpassword().equals(other.getUpassword()))
            && (this.getUemail() == null ? other.getUemail() == null : this.getUemail().equals(other.getUemail()))
            && (this.getUsex() == null ? other.getUsex() == null : this.getUsex().equals(other.getUsex()))
            && (this.getUphone() == null ? other.getUphone() == null : this.getUphone().equals(other.getUphone()))
            && (this.getUpic() == null ? other.getUpic() == null : this.getUpic().equals(other.getUpic()))
            && (this.getUinfo() == null ? other.getUinfo() == null : this.getUinfo().equals(other.getUinfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUname() == null) ? 0 : getUname().hashCode());
        result = prime * result + ((getUpassword() == null) ? 0 : getUpassword().hashCode());
        result = prime * result + ((getUemail() == null) ? 0 : getUemail().hashCode());
        result = prime * result + ((getUsex() == null) ? 0 : getUsex().hashCode());
        result = prime * result + ((getUphone() == null) ? 0 : getUphone().hashCode());
        result = prime * result + ((getUpic() == null) ? 0 : getUpic().hashCode());
        result = prime * result + ((getUinfo() == null) ? 0 : getUinfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", uname=").append(uname);
        sb.append(", upassword=").append(upassword);
        sb.append(", uemail=").append(uemail);
        sb.append(", usex=").append(usex);
        sb.append(", uphone=").append(uphone);
        sb.append(", upic=").append(upic);
        sb.append(", uinfo=").append(uinfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}