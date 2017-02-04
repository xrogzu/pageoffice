package com.linkcm.po.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:24
 * @description :
 */
public class Doc implements Serializable {

    private String id;
    private String docName;
    private String docEnName;
    private String docPath;
    private boolean editable;

    public Doc() {
    }

    public Doc(String docName, String docPath) {
        this.docName = docName;
        this.docPath = docPath;
    }

    public Doc(String docName, String docEnName, String docPath) {
        this.docName = docName;
        this.docEnName = docEnName;
        this.docPath = docPath;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id='" + id + '\'' +
                ", docName='" + docName + '\'' +
                ", docEnName='" + docEnName + '\'' +
                ", docPath='" + docPath + '\'' +
                ", editable=" + editable +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Doc doc = (Doc) object;
        return editable == doc.editable &&
                Objects.equals(id, doc.id) &&
                Objects.equals(docName, doc.docName) &&
                Objects.equals(docEnName, doc.docEnName) &&
                Objects.equals(docPath, doc.docPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docName, docEnName, docPath, editable);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocEnName() {
        return docEnName;
    }

    public void setDocEnName(String docEnName) {
        this.docEnName = docEnName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
