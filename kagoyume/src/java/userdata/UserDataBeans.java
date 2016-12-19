package userdata;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBeans。DTOと違い画面表示系への結びつきが強い
 * DTOへの変換メソッド、入力チェックリストを出力するメソッドも準備されている←ちょっと仕事しすぎかも
 *
 * @author hayashi-s
 */
public class UserDataBeans implements Serializable {

    private String name;
    private String pass;
    private String mail;
    private String address;

    public UserDataBeans() {
        this.name = "";
        this.pass = "";
        this.mail = "";
        this.address = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if (name.trim().length() == 0) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.trim().length() == 0) {
            this.pass = "";
        } else {
            this.pass = pass;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail.trim().length() == 0) {
            this.mail = "";
        } else {
            this.mail = mail;
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.trim().length() == 0) {
            this.address = "";
        } else {
            this.address = address;
        }
    }

    public ArrayList<String> chkproperties() {
        ArrayList<String> chkList = new ArrayList<String>();
        if (this.name.equals("")) {
            chkList.add("name");
        }
        if (this.pass.equals("")) {
            chkList.add("pass");
        }
        if (this.mail.equals("")) {
            chkList.add("mail");
        }
        if (this.address.equals("")) {
            chkList.add("address");
        }
        return chkList;
    }

    public void UD2DTOMapping(UserDataDTO udd) {
        udd.setName(this.name);
        udd.setPass(this.pass);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
    }

}


