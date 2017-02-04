package com.linkcm.po.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 11:57
 * @description :
 */
public abstract class DocEditor {

    private static final Map<String, String> docEditorMap = new ConcurrentHashMap<>();
    public static final Lock LOCK = new ReentrantLock();

    public static String getEditor(String docPath) {
        return docEditorMap.get(docPath);
    }

    public static String setDocEditor(String docPath, String editor) {
        return docEditorMap.put(docPath, editor);
    }

    public static String markDocEditingStatus(String docPath, String editor) {
        return setDocEditor(docPath, editor);
    }

    public static String removeDocEditingStatus(String docPath) {
        return docEditorMap.remove(docPath);
    }

    public static List<String> getEditingDoc(String editor) {
        List<String> editingDocList = new ArrayList<>();
        for (Map.Entry<String, String> entry : docEditorMap.entrySet()) {
            if (StringUtils.equals(entry.getValue(), editor)) {
                editingDocList.add(entry.getKey());
            }
        }
        return editingDocList;
    }

    public static List<String> removeEditingDoc(String editor) {
        List<String> editingDocList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = docEditorMap.entrySet().iterator();
        Map.Entry<String, String> entry;
        String doc;
        while (iterator.hasNext()) {
            entry = iterator.next();
            doc = entry.getValue();
            if (StringUtils.equals(editor, doc)) {
                editingDocList.add(doc);
                iterator.remove();
            }
        }
        return editingDocList;
    }

    public static Lock getLock() {
        return LOCK;
    }

}
