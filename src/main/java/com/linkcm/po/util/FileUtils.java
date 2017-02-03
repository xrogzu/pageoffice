package com.linkcm.po.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/3 15:15
 * @description :
 */
public abstract class FileUtils {

    public static void forceParentExists(String path) throws IOException {
        forceParentExists(Paths.get(path));
    }

    public static void forceParentExists(Path path) throws IOException {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

}
